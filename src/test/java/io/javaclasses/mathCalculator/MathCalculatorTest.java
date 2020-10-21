package io.javaclasses.mathCalculator;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Test for API MathCalculator
 */
public class MathCalculatorTest {
    private final MathCalculator mathCalculator = new MathCalculator();

    @ParameterizedTest
    @CsvSource({"123,123" , "0,0" , "67.89,67.89" , "-98.098,-98.098"})
    void testEvaluationOfNumber(double expected, String input)
            throws IncorrectMathExpressionException {
        double result = mathCalculator.evaluate(input);
        assertEquals(expected, result, "Evaluation of number is failed.");
    }

    @Test
    void testEvaluationOfExpression()
            throws IncorrectMathExpressionException {
        double result = mathCalculator.evaluate("5.99+7*8.5/2-11.01");
        assertEquals(5.99+7*8.5/2-11.01, result, "Evaluation of expression is failed");
    }

    @Test
    void testEvaluationOfExpressionWithBrackets()
            throws IncorrectMathExpressionException {
        double result = mathCalculator.evaluate("50.1*(5+(10.6/2))");
        assertEquals(50.1*(5+(10.6/2)), result, "Evaluation of expression is failed");
    }

    @Test
    void testIncorrectMathematicalExpression()
            throws IncorrectMathExpressionException {

                assertThrows(IncorrectMathExpressionException.class,() -> mathCalculator.evaluate("5.3++64"));
        //assertEqualsO( "Incorrectly entered mathematical expression in position 4.",
          //      "Incorrectly entered mathematical expression in position "+e.position()+"." );
    }
}
