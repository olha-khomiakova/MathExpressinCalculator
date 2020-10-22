package io.javaclasses.mathCalculator;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Test for API MathCalculator
 */
public class MathCalculatorTest {
    private final MathCalculator mathCalculator = new MathCalculator();

    @ParameterizedTest
    @CsvSource({"123,123", "0,0", "67.89,67.89", "-98.098,-98.098"})
    void testEvaluationOfNumber_Positive(String input, double expected)
            throws IncorrectMathExpressionException {
        double result = mathCalculator.evaluate(input);
        assertWithMessage("Evaluation of number is failed.")
                .that(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"123g", "0y", "67.89gfhk", "-98.098tgyvf"})
    void testEvaluationOfNumber_Negative(String input) {
        IncorrectMathExpressionException e =
                assertThrows(IncorrectMathExpressionException.class, () ->
                        mathCalculator.evaluate(input));
        assertThat(e).hasMessageThat().contains("Incorrectly entered mathematical " +
                "expression in position " + e.errorPosition() + ".");
    }

    @ParameterizedTest
    @CsvSource({"7+2.25/2.25, 8", "1+4/2.5*5-2.5+1*-2, 4.5", "5.01-7*8/2-11.01,-34"})
    void testEvaluationOfExpression_Positive(String input, Double expected)
            throws IncorrectMathExpressionException {
        double result = mathCalculator.evaluate(input);
        assertWithMessage("Calculation of mathematical expression is failed.")
                .that(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"7++2.25/2.25", "1+4/2.5*", "*8/2-11"})
    void testEvaluationOfExpression_Negative(String input) {
        IncorrectMathExpressionException e =
                assertThrows(IncorrectMathExpressionException.class, () ->
                        mathCalculator.evaluate(input));
        assertThat(e).hasMessageThat().contains("Incorrectly entered mathematical " +
                "expression in position " + e.errorPosition() + ".");
    }

    @ParameterizedTest
    @CsvSource({"2*(5+7*(4+1))+20, 100", "(2+1)*-2, -6,", "4*(0.1*100)+12, 52"})
    void testEvaluationOfExpressionWithBrackets_Positive(String input, double expected)
            throws IncorrectMathExpressionException {
        double result = mathCalculator.evaluate(input);
        assertWithMessage("Calculation of mathematical expression is failed.")
                .that(result).isEqualTo(expected);
    }

    @Test
    void testEvaluationOfExpressionWithFunction_Positive()
            throws IncorrectMathExpressionException {
        double result = mathCalculator.evaluate("min(5,2)+6");
        assertWithMessage("Calculation of mathematical expression is failed.")
                .that(result).isEqualTo(8);
        result = mathCalculator.evaluate("max(3,10.1)+2");
        assertWithMessage("Calculation of mathematical expression is failed.")
                .that(result).isEqualTo(12.1);
    }

    @Test
    void testEvaluationOfExpressionWithFunctionInvalidNumberOfParameters_Negative() {
        IncorrectMathExpressionException e =
                assertThrows(IncorrectMathExpressionException.class, () ->
                        mathCalculator.evaluate("min(2,3,5)"));
        assertThat(e).hasMessageThat().contains("Wrong number of function parameters! " +
                "min function can have 2 to 2 parameters.");
    }

    @Test
    void testEvaluationOfExpressionWithFunctionInvalidFunctionName_Negative() {
        IncorrectMathExpressionException e =
                assertThrows(IncorrectMathExpressionException.class, () ->
                        mathCalculator.evaluate("mox(2,3,5)"));
        assertThat(e).hasMessageThat().contains("The entered function is not processed in this calculator");
    }
}
