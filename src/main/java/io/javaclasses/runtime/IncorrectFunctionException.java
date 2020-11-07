package io.javaclasses.runtime;

/**
 * This is exception that appears if required function or procedure does not exist or the number of
 * function parameters are incorrect.
 */
public class IncorrectFunctionException extends IncorrectStatementException {

    private static final long serialVersionUID = -2070075147355454277L;

    IncorrectFunctionException(String message) {
        super(message);
    }

}
