package io.javaclasses.mathCalculator;

/**
 * This is exception that appears if expression are incorrect,
 */
public class IncorrectMathExpressionException extends Exception {
    private int errorPosition;
    public IncorrectMathExpressionException(String message) {
        super(message);
    }
    public IncorrectMathExpressionException(String message, int position) {
        super(message);
        this.errorPosition = position;
    }

    public int errorPosition() {
        return errorPosition;
    }
}
