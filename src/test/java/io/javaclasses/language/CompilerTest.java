package io.javaclasses.language;

import io.javaclasses.mathcalculator.runtime.RuntimeEnvironment;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This service tests {@link java.lang.Compiler} API.
 */
class CompilerTest {

    private final Compiler compiler = new Compiler();

    @ParameterizedTest
    @MethodSource("initialization")
    void testCorrectInitialization(String input, String expected) {
        RuntimeEnvironment environment = new RuntimeEnvironment();
        compiler.compile(input, environment);
        String output = new String(environment.output().toByteArray(), StandardCharsets.UTF_8);
        assertWithMessage("Incorrect output.")
                .that(output)
                .isEqualTo(expected);
    }
    private static Stream<Arguments> initialization() {
        return Stream.of(
                Arguments.of("a=6; bb = a; a = 7; c= a*bb; print(c);","[42.0]"),
                Arguments.of("result = 5.7; print(result);","[5.7]"),
                Arguments.of("a = 5 ; b = a * 2 + 5 ; print ( b) ;","[15.0]" )

        );
    }
    @ParameterizedTest
    @MethodSource("incorrectInitialization")
    void testIncorrectInitialization(String input) {
        RuntimeEnvironment environment = new RuntimeEnvironment();
        IncorrectStatementException ex =
                assertThrows(IncorrectStatementException.class, () ->
                        compiler.compile(input, environment));
        assertThat(ex).hasMessageThat()
                      .contains("position " + ex.errorPosition());
    }
    private static Stream<Arguments> incorrectInitialization() {
        return Stream.of(
                Arguments.of("a=6n;"),
                Arguments.of("result = 1 2;"),
                Arguments.of("a = a; ")
        );
    }
    @ParameterizedTest
    @MethodSource("procedure")
    void testCorrectProcedure(String input, String expected) {
        RuntimeEnvironment environment = new RuntimeEnvironment();
        compiler.compile(input, environment);
        String output = new String(environment.output().toByteArray(), StandardCharsets.UTF_8);
        assertWithMessage("Incorrect input pf procedure.")
                .that(output)
                .isEqualTo(expected);
    }
    private static Stream<Arguments> procedure() {
        return Stream.of(
                Arguments.of("a=6; print(a);","[6.0]"),
                Arguments.of("a=6; b= 7; print(a,b);","[6.0, 7.0]"),
                Arguments.of("a = 5 ; print (a*max(4,6)) ;","[30.0]" ),
                Arguments.of("a = 5 ; b= 7; c =5*7 ; print(a,b,c);","[5.0, 7.0, 35.0]" )

        );
    }
    @ParameterizedTest
    @MethodSource("incorrectProcedure")
    void testIncorrectProcedure(String input, CharSequence expected) {
        RuntimeEnvironment environment = new RuntimeEnvironment();
        RuntimeException ex =
                assertThrows(RuntimeException.class, () ->
                        compiler.compile(input, environment));
        assertThat(ex).hasMessageThat()
                      .contains(expected);
    }
    private static Stream<Arguments> incorrectProcedure() {
        return Stream.of(
                Arguments.of("a=6; pr(a);","pr"),
                Arguments.of("print();","in position 0"),
                Arguments.of("println(4);","println" )

        );
    }
    @ParameterizedTest
    @MethodSource( "booleanExpression")
    void testCorrectBooleanExpression(String input, String expected) {
        RuntimeEnvironment environment = new RuntimeEnvironment();
        compiler.compile(input, environment);
        String output = new String(environment.output().toByteArray(), StandardCharsets.UTF_8);
        assertWithMessage("Incorrect boolean expression.")
                .that(output)
                .isEqualTo(expected);
    }
    private static Stream<Arguments> booleanExpression() {
        return Stream.of(
                Arguments.of("a = 5==6; print(a);","[false]"),
                Arguments.of("a = 5!=6; print(a);","[true]"),
                Arguments.of("a = 6<=6; print(a);","[true]"),
                Arguments.of("a = 6>=6; print(a);","[true]"),
                Arguments.of("a = 5>6; print(a);","[false]"),
                Arguments.of("result = 12; val = max(result, 13)>result; print(val);","[true]"),
                Arguments.of("max=3.9543; result = max < min(2+2,3+3)*2; print(result,max);","[true, 3.9543]")
        );
    }
}
