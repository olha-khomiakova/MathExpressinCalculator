package io.javaclasses.mathCalculator.math;

import io.javaclasses.mathCalculator.IncorrectMathExpressionException;
/**
 * This is general representing of function that stores information about it
 * and have abstract method calculate() that should be implemented by a descendant of this class.
 */
abstract public class Function {
    public FunctionDataStructure functionDataStructure = new FunctionDataStructure();
    private final int minimumNumberOfParameters;
    private final int maximumNumberOfParameters;
    private final String functionName;

    public Function(int minimumNumberOfParameters, int maximumNumberOfParameters, String functionName) {
        this.minimumNumberOfParameters = minimumNumberOfParameters;
        this.maximumNumberOfParameters = maximumNumberOfParameters;
        this.functionName = functionName;
    }

    abstract public double calculate();

    protected void accept() throws IncorrectMathExpressionException {
        if (!(this.maximumNumberOfParameters >= functionDataStructure.getFunctionParameters().size()) ||
                !(this.minimumNumberOfParameters <= functionDataStructure.getFunctionParameters().size())) {
            throw new IncorrectMathExpressionException("Wrong number of function parameters! " +
                    functionName + " function can have " + minimumNumberOfParameters + " to " +
                    maximumNumberOfParameters + " parameters.");
        }
    }

    public void setFunctionDataStructure(FunctionDataStructure functionDataStructure) {
        this.functionDataStructure = functionDataStructure;
    }
}
