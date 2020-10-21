package io.javaclasses.mathCalculator;

/**
 * This is exception that appears if expression are incorrect,
 */
public class IncorrectMathExpressionException extends Exception {
    private final int errorPosition;

    IncorrectMathExpressionException(String message, int position) {
        super(message);
        this.errorPosition = position;
    }

    public int errorPosition() {
        return errorPosition;
    }
}
