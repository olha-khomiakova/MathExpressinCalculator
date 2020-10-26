package io.javaclasses.mathcalculator.math;

/**
 * This is implementation mathematical function that finds the minimum parameters of the two.
 */
@SuppressWarnings("CyclicClassDependency")
public class MaxFunction extends Function {

    MaxFunction(int minimumNumber, int maximumNumber) {
        super(minimumNumber, maximumNumber, "max");
    }

    /**
     * This API finds the maximum parameters.
     *
     * @return double maximum parameters
     */
    @Override
    public double calculate() {
        this.accept();
        return functionDataStructure().getFunctionParameters()
                                      .stream()
                                      .max(Double::compareTo)
                                      .get();
    }

}
