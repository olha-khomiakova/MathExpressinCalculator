package io.javaclasses.runtime;

/**
 * This is exception that appears if called variable does not exist.
 */
class CallingANonExistentVariableException extends IncorrectStatementException {

    private static final long serialVersionUID = -2664630958058185614L;

    CallingANonExistentVariableException(String message) {
        super(message);
    }

}
