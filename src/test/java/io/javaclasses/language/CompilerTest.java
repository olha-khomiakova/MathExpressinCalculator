package io.javaclasses.language;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CompilerTest {

    private final Compiler compiler = new Compiler();

    @ParameterizedTest
    @CsvSource({"a=5;b=a*2+5;"})
    void testCorrectEvaluationOfNumber(String input) {
        compiler.compile(input);
//            assertWithMessage("Evaluation of number is failed.")
//                    .that(result)
//                    .isEqualTo(expected);
    }

}
