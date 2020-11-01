package io.javaclasses.mathcalculator.math;

import java.util.List;
import java.util.Optional;

/**
 * This is implementation mathematical function that finds the minimum parameters of the two.
 */
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
    public double calculate(List<Double> parameters) {
        Optional<Double> result = parameters.stream()
                                            .max(Double::compareTo);
        if (result.isPresent()) {
            return result.get();
        }
        return 0;
    }

}
