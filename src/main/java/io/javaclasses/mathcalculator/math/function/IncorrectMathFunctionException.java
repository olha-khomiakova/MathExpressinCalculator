package io.javaclasses.mathcalculator.math.function;

/**
 * This is exception that appears if required function does not exist or the number of
 * function parameters are incorrect.
 */
public class IncorrectMathFunctionException extends RuntimeException {

    private static final long serialVersionUID = -2070075147355454277L;

    IncorrectMathFunctionException(String message) {
        super(message);
    }
}
