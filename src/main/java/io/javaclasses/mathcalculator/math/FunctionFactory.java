package io.javaclasses.mathcalculator.math;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This is factory that creates the a class of the type {@link Function}.
 */
public class FunctionFactory {

    private final Map<String, Function> functionMap = new HashMap<>();
    private final int minNumberOfParameters;
    private final int maxNumberOfParameters;

    public FunctionFactory() {
        minNumberOfParameters = 2;
        maxNumberOfParameters = 2;
        functionMap.put("min", ((Creator) () -> new MinFunction(minNumberOfParameters,
                                                                maxNumberOfParameters)).create());
        functionMap.put("max", ((Creator) () -> new MaxFunction(minNumberOfParameters,
                                                                maxNumberOfParameters)).create());
    }

    /**
     * This method checks if the function we need exists and returns an object of the needed type or
     * throws an exception.
     *
     * @param name
     *         has information about function name.
     * @return required function
     */
    public Optional<Function> getRequiredFunction(String name) {
        if (!functionMap.containsKey(name)) {
            throw new IncorrectMathFunctionException(
                    "The entered function is not processed in this calculator");
        }
        return Optional.of(functionMap.get(name));
    }

    private interface Creator {

        Function create();
    }
}
