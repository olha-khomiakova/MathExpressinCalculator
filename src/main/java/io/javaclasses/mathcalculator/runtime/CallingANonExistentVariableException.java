package io.javaclasses.mathcalculator.runtime;

/**
 * This is exception that appears if expression are incorrect doe to syntax error.
 */
public class CallingANonExistentVariableException extends RuntimeException {

    CallingANonExistentVariableException(String message) {
        super(message);
    }

}
