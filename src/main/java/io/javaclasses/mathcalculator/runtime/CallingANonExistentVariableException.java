package io.javaclasses.mathcalculator.runtime;

/**
 * This is exception that appears if called variable does not exist.
 */
 class CallingANonExistentVariableException extends RuntimeException {

    private static final long serialVersionUID = -2664630958058185614L;

    CallingANonExistentVariableException(String message) {
        super(message);
    }

}
