package io.javaclasses.runtime;

/**
 * This is exception that appears if statement are incorrect doe to syntax error.
 */
public class MonkeyException extends RuntimeException {

    private static final long serialVersionUID = -7819103504187683995L;

    public MonkeyException(String message) {
        super(message);
    }
}
