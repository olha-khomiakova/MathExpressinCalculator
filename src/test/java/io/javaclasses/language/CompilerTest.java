package io.javaclasses.language;

import io.javaclasses.mathcalculator.runtime.CallingANonExistentVariableException;
import io.javaclasses.mathcalculator.runtime.RuntimeEnvironment;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.charset.StandardCharsets;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CompilerTest {

    private final Compiler compiler = new Compiler();

    @ParameterizedTest
    @CsvSource(value = " a = 5 ; b = a * 2 + 5 ; print ( b,a) ;:[15.0, 5.0]", delimiter = ':')
    void testCorrectInitialization(String input, String expected) {
        RuntimeEnvironment environment = new RuntimeEnvironment();
        compiler.compile(input, environment);
        String output = new String(environment.output().toByteArray(), StandardCharsets.UTF_8);
        assertWithMessage("Incorrect output.")
                .that(output)
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"a==5;", "a=5"})
    void testIncorrectInitialization(String input) {
        RuntimeEnvironment environment = new RuntimeEnvironment();
        IncorrectStatementException ex =
                assertThrows(IncorrectStatementException.class, () ->
                        compiler.compile(input, environment));
        assertThat(ex).hasMessageThat()
                      .contains("position " + ex.errorPosition());
    }
    @ParameterizedTest
    @CsvSource(value = {" a = 5>6; print(a);:[false]"
            ,"max=3.9543; result = max < min(2+2,3+3)*2; print(result);:[true]"}, delimiter = ':')
    void testCorrectBooleanExpression(String input, String expected) {
        RuntimeEnvironment environment = new RuntimeEnvironment();
        compiler.compile(input, environment);
        String output = new String(environment.output().toByteArray(), StandardCharsets.UTF_8);
        assertWithMessage("Incorrect boolean expression.")
                .that(output)
                .isEqualTo(expected);
    }
//    @ParameterizedTest
//    @CsvSource({"a=5;delete(a);b=a;", "a=6;b=7;r=8;delete(b);y=b;"})
//    void testIncorrectDelete(String input) {
//        RuntimeEnvironment environment = new RuntimeEnvironment();
//        CallingANonExistentVariableException ex =
//                assertThrows(CallingANonExistentVariableException.class, () ->
//                        compiler.compile(input, environment));
//        assertThat(ex).hasMessageThat()
//                      .contains("Cannot resolve symbol");
//    }
}
