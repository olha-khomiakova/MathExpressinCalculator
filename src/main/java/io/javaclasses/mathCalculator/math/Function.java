package io.javaclasses.mathCalculator.math;

import io.javaclasses.mathCalculator.IncorrectMathExpressionException;

abstract public class Function {
    public FunctionDataStructure functionDataStructure = new FunctionDataStructure();
    protected final int minimumNumberOfParameters;
    protected final int maximumNumberOfParameters;
    protected final String functionName;

    public Function(int minimumNumberOfParameters, int maximumNumberOfParameters, String functionName) {
        this.minimumNumberOfParameters = minimumNumberOfParameters;
        this.maximumNumberOfParameters = maximumNumberOfParameters;
        this.functionName = functionName;
    }

    protected void accept() throws IncorrectMathExpressionException {
        if (!(this.maximumNumberOfParameters >= functionDataStructure.getFunctionParameters().size()) ||
                !(this.minimumNumberOfParameters <= functionDataStructure.getFunctionParameters().size())) {
            throw new IncorrectMathExpressionException("Wrong number of function parameters! " +
                    functionName + " function can have " + minimumNumberOfParameters + " to " +
                    maximumNumberOfParameters + " parameters.");
        }
    }
    abstract public double calculate() throws IncorrectMathExpressionException;

    public void setFunctionDataStructure(FunctionDataStructure functionDataStructure) {
        this.functionDataStructure = functionDataStructure;
    }
}
