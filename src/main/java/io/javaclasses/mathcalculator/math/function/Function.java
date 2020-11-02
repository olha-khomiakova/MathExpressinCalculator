package io.javaclasses.mathcalculator.math.function;

import java.util.List;

/**
 * This is general representing of function that stores information about it
 * and have abstract method calculate() that should be implemented by a descendant of this class.
 */
public abstract class Function {

    private final int minimumNumberOfParameters;
    private final int maximumNumberOfParameters;
    private final String functionName;

    Function(int minimumNumber, int maximumNumber, String functionName) {
        this.minimumNumberOfParameters = minimumNumber;
        this.maximumNumberOfParameters = maximumNumber;
        this.functionName = functionName;
    }

    public abstract double calculate(List<Double> parameters);

    public void accept(int numberOfParameters) {
        if (numberOfParameters < maximumNumberOfParameters ||
                numberOfParameters > minimumNumberOfParameters) {
            throw new IncorrectMathFunctionException("Wrong number of function parameters! " +
                                                             functionName + " function can have " +
                                                             minimumNumberOfParameters + " to " +
                                                             maximumNumberOfParameters +
                                                             " parameters.");
        }
    }

}
