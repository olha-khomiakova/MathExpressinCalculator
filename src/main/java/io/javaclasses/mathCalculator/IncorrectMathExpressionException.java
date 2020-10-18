package io.javaclasses.mathCalculator;

/**
 * This is exception that appears if expression are incorrect,
 */
public class IncorrectMathExpressionException extends Exception {
    private final int position;

    IncorrectMathExpressionException(String message, int position) {
        super(message);
        this.position = position;
    }

    public int position() {
        return position;
    }
}
