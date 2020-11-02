package io.javaclasses.mathcalculator.runtime;

/**
 * This is exception that appears if expression are incorrect doe to syntax error.
 */
public class CallingANonExistentVariableException extends RuntimeException {

    private static final long serialVersionUID = -2664630958058185614L;

    CallingANonExistentVariableException(String message) {
        super(message);
    }

}
