package io.javaclasses.language;

/**
 * This is exception that appears if statement are incorrect doe to syntax error.
 */
class IncorrectStatementException extends RuntimeException {

    private static final long serialVersionUID = -7819103504187683995L;
    private final int errorPosition;

    IncorrectStatementException(String message, int position) {
        super(message);
        this.errorPosition = position;
    }

    public int errorPosition() {
        return errorPosition;
    }
}
