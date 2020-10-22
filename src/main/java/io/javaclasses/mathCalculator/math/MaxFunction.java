package io.javaclasses.mathCalculator.math;


import io.javaclasses.mathCalculator.IncorrectMathExpressionException;

/**
 * This is implementation mathematical function that finds the minimum parameters of the two.
 */
public class MaxFunction extends Function {

    public MaxFunction(int minimumNumberOfParameters, int maximumNumberOfParameters) {
        super(minimumNumberOfParameters, maximumNumberOfParameters, "max");
    }

    @Override
    public double calculate() throws IncorrectMathExpressionException {
        this.accept();
        return Math.max(functionDataStructure.getFunctionParameters().get(0),
                functionDataStructure.getFunctionParameters().get(1));
    }

}
