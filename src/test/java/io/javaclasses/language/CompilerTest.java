package io.javaclasses.language;

import io.javaclasses.mathcalculator.fsm.impl.CalculatedStateAcceptor;
import io.javaclasses.mathcalculator.runtime.CallingANonExistentVariableException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.PrintStream;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CompilerTest {

    private final Compiler compiler = new Compiler();

    @ParameterizedTest
    @CsvSource("a=5;b=a*2+5;print(b);")
    void testCorrectInitialization(String input) {
        PrintStream output = compiler.compile(input);
        //double result = output.;
//            assertWithMessage("Evaluation of number is failed.")
//                    .that(result)
//                    .isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"a==5;", "a=5"})
    void testIncorrectInitialization(String input) {
        IncorrectStatementException ex =
                assertThrows(IncorrectStatementException.class, () ->
                        compiler.compile(input));
        assertThat(ex).hasMessageThat()
                      .contains("position " + ex.errorPosition());
    }
    @ParameterizedTest
    @CsvSource({"a=5;delete(a);b=a;","a=6;b=7;r=8;delete(b);y=b;"})
    void testIncorrectDelete(String input) {
        CallingANonExistentVariableException ex =
                assertThrows(CallingANonExistentVariableException.class, () ->
                        compiler.compile(input));
        assertThat(ex).hasMessageThat()
                      .contains("Cannot resolve symbol");
    }
}
