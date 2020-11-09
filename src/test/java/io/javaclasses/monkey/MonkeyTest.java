package io.javaclasses.monkey;

import io.javaclasses.runtime.IncorrectStatementException;
import io.javaclasses.runtime.RuntimeEnvironment;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
    void testIncorrectInitialization(String program) {
        assertException(program, "position ");
    }

    private static Stream<Arguments> incorrectInitialization() {
        return Stream.of(
                Arguments.of("a=6n;"),
                Arguments.of("result = 1 2;"),
                Arguments.of("a = a; ")
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

        assertOutputValue(input, expected, "Execution of procedures is broken.");
    }

    private static Stream<Arguments> UnaryOperatorTestCases() {
        return Stream.of(
                Arguments.of("a=1>5; b= !a; print(b);", "true"),
                Arguments.of("a=1<5; b= !a; print(b);", "false"),
                Arguments.of("a=1<5; b= !a; c=!(3>2); print(c);", "false")
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

        assertOutputValue(input, expected, "Incorrect boolean expression.");
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
        IncorrectStatementException ex =
                assertThrows(IncorrectStatementException.class, () ->
                        compiler.interpret(program, environment));
        assertThat(ex).hasMessageThat()
                      .contains(expectedOutputValue);
    }
}
