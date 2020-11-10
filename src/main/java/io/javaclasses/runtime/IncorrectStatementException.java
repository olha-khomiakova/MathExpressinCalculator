package io.javaclasses.runtime;

/**
 * This is exception that appears if statement are incorrect doe to syntax error.
 */
public class IncorrectStatementException extends RuntimeException {

    private static final long serialVersionUID = -7819103504187683995L;
    private  int errorPosition;

    public IncorrectStatementException(String message, int position) {
        super(message);
        this.errorPosition = position;
    }

    public IncorrectStatementException(String message) {
        super(message);
    }

    public int errorPosition() {
        return errorPosition;
    }
}