package io.javaclasses.mathcalculator;

import io.javaclasses.mathcalculator.math.IncorrectMathFunctionException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.concurrent.atomic.AtomicReference;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test for API MathCalculator.
 */
@SuppressWarnings({"ClassIndependentOfModule", "ClassOnlyUsedInOneModule", "ClassWithTooManyTransitiveDependencies"})
class MathCalculatorTest {

    private final MathCalculator mathCalculator = new MathCalculator();

    @ParameterizedTest
    @CsvSource({"123,123", "0,0", "67.89,67.89", "-98.098,-98.098"})
    void testCorrectEvaluationOfNumber(String input, double expected) {
        double result = mathCalculator.evaluate(input);
        assertWithMessage("Evaluation of number is failed.")
                .that(result)
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"123g", "0y", "67.89error", "-98.098error"})
    void testIncorrectEvaluationOfNumber(String input) {
        AtomicReference<Double> result = new AtomicReference<>((double) 0);
        IncorrectMathExpressionException ex =
                assertThrows(IncorrectMathExpressionException.class, () ->
                        result.set(mathCalculator.evaluate(input)));
        assertThat(ex).hasMessageThat()
                      .contains("in position " + ex.errorPosition());
    }

    @ParameterizedTest
    @CsvSource({"7+2.25/2.25, 8", "1+4/2.5*5-2.5+1*-2, 4.5", "5.01-7*8/2-11.01,-34"})
    void testCorrectEvaluationOfExpression(String input, Double expected) {
        double result = mathCalculator.evaluate(input);
        assertWithMessage("Calculation of mathematical expression is failed.")
                .that(result)
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"7++2.25/2.25", "1+4/2.5*", "*8/2-11"})
    void testIncorrectEvaluationOfExpression(String input) {
        AtomicReference<Double> result = new AtomicReference<>((double) 0);
        IncorrectMathExpressionException ex =
                assertThrows(IncorrectMathExpressionException.class, () ->
                        result.set(mathCalculator.evaluate(input)));
        assertThat(ex).hasMessageThat()
                      .contains(" in position " + ex.errorPosition());
    }

    @ParameterizedTest
    @CsvSource({"2*(5+7*(4+1))+20, 100", "(2+1)*-2, -6,", "4*(0.1*100)+12, 52"})
    void testCorrectEvaluationOfExpressionWithBrackets(String input, double expected)
            throws IncorrectMathExpressionException {
        double result = mathCalculator.evaluate(input);
        assertWithMessage("Calculation of mathematical expression with brackets is failed.")
                .that(result)
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"min(5,2):2", "min(5,2)+6:8", "max(3,10.1)+2:12.1"}, delimiter = ':')
    void testCorrectEvaluationOfExpressionWithFunction(String input, double expected) {
        double result = mathCalculator.evaluate(input);
        assertWithMessage("Calculation of mathematical expression with function is failed.")
                .that(result)
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"min(2,3,5):min function can have 2 to 2 parameters.",
            "mox(2,3,5):entered function is not processed"}, delimiter = ':')
    void testIncorrectEvaluationOfExpressionWithFunction(String input, CharSequence expected) {
        AtomicReference<Double> result = new AtomicReference<>((double) 0);
        IncorrectMathFunctionException ex =
                assertThrows(IncorrectMathFunctionException.class, () ->
                        result.set(mathCalculator.evaluate(input)));
        assertThat(ex).hasMessageThat()
                      .contains(expected);
    }

}
