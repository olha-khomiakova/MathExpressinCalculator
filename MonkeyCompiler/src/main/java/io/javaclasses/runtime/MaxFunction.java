package io.javaclasses.runtime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static io.javaclasses.runtime.DoubleValueReader.readDouble;

/**
 * This is implementation of {@link Function} that finds the maximum value.
 */
class MaxFunction extends Function {

    MaxFunction(Collection<Command> commands) {
        super(2, 2, "max", commands);
    }

    /**
     * This API finds the maximum value.
     *
     * @return double maximum value
     */
    Optional<Double> apply(Collection<Double> parameters) {
        return parameters.stream()
                         .max(Double::compareTo);

    }

    @Override
    public void execute(RuntimeEnvironment environment) {

        Collection<Double> arguments = new ArrayList<>();

        for (ValueHolder holder : parameters(environment)) {
            arguments.add(readDouble(holder));
        }

        if (apply(arguments).isPresent()) {
            environment.stack()
                       .pushOperand(new DoubleValueHolder(apply(arguments).get()));
        }
    }
}
