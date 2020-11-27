package io.javaclasses.runtime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static io.javaclasses.runtime.DoubleValueReader.readDouble;

/**
 * This is implementation of {@link Function} that finds the minimum value.
 */
class MinFunction extends Function {

    MinFunction(Collection<Command> commands) {
        super(2, 2, "min", commands);

    }

    /**
     * This API finds the minimum value.
     *
     * @return double minimum value
     */
    Optional<Double> apply(Collection<Double> parameters) {
        return parameters.stream()
                         .min(Double::compareTo);

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
