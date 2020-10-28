package io.javaclasses.mathcalculator.math;

import java.util.List;
import java.util.Optional;

/**
 * This is implementation mathematical function that finds the minimum parameters of the two.
 */
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
    public double calculate(List<Double> parameters) {
        Optional<Double> result = parameters.stream()
                                            .min(Double::compareTo);
        if(result.isPresent())
        {
            return result.get();
        }
        return 0;
    }

}
