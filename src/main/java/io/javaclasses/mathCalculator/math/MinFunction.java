package io.javaclasses.mathCalculator.math;


import io.javaclasses.mathCalculator.IncorrectMathExpressionException;

/**
 * This is implementation mathematical function that finds the minimum parameters of the two.
 */
public class MinFunction extends Function {

    public MinFunction(int minimumNumberOfParameters, int maximumNumberOfParameters) {
        super(minimumNumberOfParameters, maximumNumberOfParameters, "min");
    }

    @Override
    public double calculate() throws IncorrectMathExpressionException {
        this.accept();
        return Math.min(functionDataStructure.getFunctionParameters().get(0),
                functionDataStructure.getFunctionParameters().get(1));
    }

}
