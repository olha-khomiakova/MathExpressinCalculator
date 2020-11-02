package io.javaclasses.mathcalculator.runtime;

/**
 * This is exception that appears if required function does not exist or the number of
 * function parameters are incorrect.
 */
 class IncorrectParametersException extends RuntimeException {

    private static final long serialVersionUID = -2070075147355454277L;

    IncorrectParametersException(String message) {
        super(message);
    }
}
