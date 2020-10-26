package io.javaclasses.mathCalculator.math;


/**
 * This is implementation mathematical function that finds the minimum parameters of the two.
 */
public class MaxFunction extends Function {

    public MaxFunction(int minimumNumberOfParameters, int maximumNumberOfParameters) {
        super(minimumNumberOfParameters, maximumNumberOfParameters, "max");
    }

    /**
     * This API finds the maximum parameters.
     *
     * @return double maximum parameters
     */
    @Override
    public double calculate() {
        this.accept();
        return functionDataStructure.getFunctionParameters().stream().max(Double::compareTo).get();
    }

}
