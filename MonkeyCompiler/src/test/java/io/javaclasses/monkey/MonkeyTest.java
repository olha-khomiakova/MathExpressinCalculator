package io.javaclasses.monkey;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import io.javaclasses.runtime.MonkeyException;
import io.javaclasses.runtime.RuntimeEnvironment;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This service tests {@link Monkey} API.
 */
class MonkeyTest {

    private final Monkey compiler = new Monkey();

    @ParameterizedTest
    @MethodSource("initialization")
    void testCorrectInitialization(String input, String expected) {

        assertOutputValue(input, expected,
                          "Compilation is broken for initialize variable statement.");
    }

    private static Stream<Arguments> initialization() {
        return Stream.of(
                Arguments.of("a=5; print(a);", "5.0"),
                Arguments.of("a=6; bb = a; a = 7; c= a*bb; print(c);", "42.0"),
                Arguments.of("result = 5.7; print(result);", "5.7"),
                Arguments.of("a = 5 ; b = a * 2 + 5 ; print ( b) ;", "15.0")

        );
    }

    @ParameterizedTest
    @MethodSource("incorrectInitialization")
    void testIncorrectInitialization(String program, CharSequence message) {
        assertException(program, message);
    }

    private static Stream<Arguments> incorrectInitialization() {
        return Stream.of(
                Arguments.of("a=6n;", "in position"),
                Arguments.of("result = 1 2;", "in position"),
                Arguments.of("a = a; ", "Cannot resolve")
        );
    }

    @ParameterizedTest
    @MethodSource("executeProcedureTestCases")
    void testProcedureExecution(String input, String expected) {

        assertOutputValue(input, expected, "Execution of procedures is broken.");
    }

    private static Stream<Arguments> executeProcedureTestCases() {
        return Stream.of(
                Arguments.of("print(1,2);", "1.0, 2.0"),
                Arguments.of("a=6; print(a);", "6.0"),
                Arguments.of("a=6; b=7; print(a , b);", "6.0, 7.0"),
                Arguments.of("a = 5 ; print (a*max(4,6)) ;", "30.0"),
                Arguments.of("a = 5 ; b= 7; c =5*7 ; print(a,b,c);", "5.0, 7.0, 35.0")

        );
    }

    @ParameterizedTest
    @MethodSource("UnaryOperatorTestCases")
    void testUnaryOperator(String input, String expected) {

        assertOutputValue(input, expected, "Execution of unary operator is broken.");
    }

    private static Stream<Arguments> UnaryOperatorTestCases() {
        return Stream.of(
                Arguments.of("a=1>5; b= !a; print(b);", "true"),
                Arguments.of("a=1>5; print(!a);", "true"),
                Arguments.of("a=1<5; b= !a; print(b);", "false"),
                Arguments.of("a=1<5; b= !a; c=!(3>2); print(c);", "false")
        );
    }

    @ParameterizedTest
    @MethodSource("UnaryOperatorNegativeTestCases")
    void testUnaryOperatorNegativeTestCases(String program, CharSequence message) {
        assertException(program, message);
    }

    private static Stream<Arguments> UnaryOperatorNegativeTestCases() {
        return Stream.of(
                Arguments.of("a=6+1; b = !a; print(b);", "Boolean"),
                Arguments.of("print(!(1+2));", " in position")
        );
    }

    @ParameterizedTest
    @MethodSource("executeProcedureNegativeTestCases")
    void testProcedureCompilationErrors(String program, CharSequence expectedOutput) {
        assertException(program, expectedOutput.toString());
    }

    private static Stream<Arguments> executeProcedureNegativeTestCases() {
        return Stream.of(
                Arguments.of("a=6; pr(a);", "pr"),
                Arguments.of("print();", "in position 0"),
                Arguments.of("println(4);", "println")

        );
    }

    @ParameterizedTest
    @MethodSource("booleanExpression")
    void testCorrectBooleanExpression(String input, String expected) {

        assertOutputValue(input, expected, "Execution of boolean expression is broken.");
    }

    private static Stream<Arguments> booleanExpression() {
        return Stream.of(
                Arguments.of("a = (5<6)==(7<6); print(a);", "false"),
                Arguments.of("a = 5==6; print(a);", "false"),
                Arguments.of("a = 5!=6; print(a);", "true"),
                Arguments.of("a = 6<=6; print(a);", "true"),
                Arguments.of("a = 6>=6; print(a);", "true"),
                Arguments.of("a = 5>6; print(a);", "false"),
                Arguments.of("result = 12; val = max(result, 13)>result; print(val);", "true"),
                Arguments.of("max=3.9543; result = max < min(2+2,3+3)*2; print(result,max);",
                             "true, 3.9543")
        );
    }

    @ParameterizedTest
    @MethodSource("whileLoopTestCases")
    void testWhileLoop(String input, String expected) {

        assertOutputValue(input, expected, "While loop is broken.");
    }

    private static Stream<Arguments> whileLoopTestCases() {
        return Stream.of(
                Arguments.of("a = 6; b = 7; while(a!=8){ a= b+1;} b=a +1; print(a, b);",
                             "8.0, 9.0"),
                Arguments.of("a = 6; b = 7; while(a!=9){ a= b+1; a= a+1;} b=a +1; print(a, b);",
                             "9.0, 10.0"),
                Arguments.of(readFile("twoWhileTestCase.monkey"), "10.0, 10.0")
        );
    }

    @ParameterizedTest
    @MethodSource("whileLoopNegativeTestCases")
    void testWhileLoopNegativeTestCases(String program, CharSequence expectedOutput) {
        assertException(program, expectedOutput.toString());
    }

    private static Stream<Arguments> whileLoopNegativeTestCases() {
        return Stream.of(
                Arguments.of("a = 6; b = 7; while(a!=8) a =b+1;", "Cannot resolve \"while\"")
        );
    }

    private void assertOutputValue(String program, String expectedOutputValue,
                                   String assertMessage) {

        RuntimeEnvironment environment = new RuntimeEnvironment();
        compiler.interpret(program, environment);

        assertWithMessage(assertMessage)
                .that(environment.outputAsText())
                .isEqualTo(expectedOutputValue);
    }

    private void assertException(String program, CharSequence expectedOutputValue) {

        RuntimeEnvironment environment = new RuntimeEnvironment();
        MonkeyException ex =
                assertThrows(MonkeyException.class, () ->
                        compiler.interpret(program, environment));
        assertThat(ex).hasMessageThat()
                      .contains(expectedOutputValue);
    }

    static String readFile(String fileName) {
        ClassLoader classLoader = Monkey.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        try {
            return CharStreams.toString(new InputStreamReader(Objects.requireNonNull(inputStream),
                                                              Charsets.UTF_8));
        } catch (IOException e) {
            e.initCause(new MonkeyException("Cannot open file : " + fileName).getCause());
            return "";
        }
    }
}
