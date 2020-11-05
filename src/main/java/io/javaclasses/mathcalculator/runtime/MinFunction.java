package io.javaclasses.mathcalculator.runtime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.javaclasses.mathcalculator.runtime.DoubleValueReader.readDouble;

/**
 * This is implementation mathematical function that finds the minimum parameters of the two.
 */
public class MinFunction extends Function {

    MinFunction(int minimumNumber, int maximumNumber, List<Command> commands) {
        super(minimumNumber, maximumNumber, "min", commands);

    }

    /**
     * This API finds the minimum parameters.
     *
     * @return double minimum parameters
     */
    @Override
    public double apply(List<Double> parameters) {
        Optional<Double> result = parameters.stream()
                                            .min(Double::compareTo);
        if (result.isPresent()) {
            return result.get();
        }
        return 0;
    }

    @Override
    public void execute(RuntimeEnvironment environment) {
        List<Double> arguments= new ArrayList<>();
        for(ValueHolder holder: parameters(environment))
        {
            arguments.add(readDouble(holder));
        }
        environment.stack().pushOperand(new DoubleValueHolder(apply(arguments)));
    }
}
