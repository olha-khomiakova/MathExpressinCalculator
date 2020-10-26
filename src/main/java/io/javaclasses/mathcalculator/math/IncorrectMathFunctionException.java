package io.javaclasses.mathcalculator.math;

/**
 * This is exception that appears if required function does not exist or the number of
 * function parameters are incorrect.
 */
public class IncorrectMathFunctionException extends RuntimeException {

    private static final long serialVersionUID = 1992761796067994150L;

    IncorrectMathFunctionException(String message) {
        super(message);
    }
}
