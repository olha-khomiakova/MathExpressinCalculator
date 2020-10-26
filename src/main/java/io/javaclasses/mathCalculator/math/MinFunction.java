package io.javaclasses.mathCalculator.math;

/**
 * This is implementation mathematical function that finds the minimum parameters of the two.
 */
public class MinFunction extends Function {

    public MinFunction(int minimumNumberOfParameters, int maximumNumberOfParameters) {
        super(minimumNumberOfParameters, maximumNumberOfParameters, "min");
    }

    /**
     * This API finds the minimum parameters.
     *
     * @return double minimum parameters
     */
    @Override
    public double calculate() {
        this.accept();
        return functionDataStructure.getFunctionParameters().stream().min(Double::compareTo).get();
    }

}
