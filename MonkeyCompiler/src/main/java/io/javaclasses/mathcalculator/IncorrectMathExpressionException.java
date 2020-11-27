package io.javaclasses.mathcalculator;

/**
 * This is exception that appears if expression are incorrect doe to syntax error.
 */
class IncorrectMathExpressionException extends RuntimeException {

    private static final long serialVersionUID = -7819103504187683995L;
    private final int errorPosition;

    IncorrectMathExpressionException(String message, int position) {
        super(message);
        this.errorPosition = position;
    }

    public int errorPosition() {
        return errorPosition;
    }
}
