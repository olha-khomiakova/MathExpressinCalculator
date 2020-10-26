package io.javaclasses.mathcalculator.math;

/**
 * This is implementation mathematical function that finds the minimum parameters of the two.
 */
@SuppressWarnings({"ClassWithTooManyTransitiveDependencies", "CyclicClassDependency"})
public class MinFunction extends Function {

    MinFunction(int minimumNumber, int maximumNumber) {
        super(minimumNumber, maximumNumber, "min");
    }

    /**
     * This API finds the minimum parameters.
     *
     * @return double minimum parameters
     */
    @Override
    public double calculate() {
        this.accept();
        return functionDataStructure().getFunctionParameters()
                                      .stream()
                                      .min(Double::compareTo)
                                      .get();
    }

}
