package io.javaclasses.mathCalculator;


import org.junit.Assert;
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
    @CsvSource({"123hjk,123" , "0,0" , "67.89f,67.89" , "-98.098,-98.098"})
    void testEvaluationOfNumber(String input, String expected)
            throws IncorrectMathExpressionException {
        double result = mathCalculator.evaluate(input);
        assertEquals(Double.parseDouble(expected), result, "Evaluation of number is failed");
    }

    @Test
    void testEvaluationOfExpression()
            throws IncorrectMathExpressionException {
        double result = mathCalculator.evaluate("5.99+7*8/2-11.01");
        assertEquals((double)5.99+7*8/2-11.01, result, "Evaluation of expression is failed");
    }

    @Test
    void testIncorrectMathematicalExpression()
            throws IncorrectMathExpressionException {
        DeadLock deadLock = assertThrows(DeadLock.class,() -> {
            mathCalculator.evaluate("5.3+64*");
        });
        assertEquals(deadLock.getMessage(), "Incorrectly entered mathematical expression.");
    }
}
