package io.javaclasses.mathcalculator.math;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This is factory that creates the a class of the type {@link Function}.
 */
@SuppressWarnings({"ClassWithTooManyTransitiveDependencies", "CyclicClassDependency"})
class FunctionFactory {

    private final Map<String, Function> functionMap = new HashMap<>();
    private final int minNumberOfParameters;
    private final int maxNumberOfParameters;

    FunctionFactory() {
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
     * @param funcDataStructure
     *         has information about function name and parameters.
     * @return required function
     */
    Optional<Function> getRequiredFunction(FunctionDataStructure funcDataStructure) {
        String name = funcDataStructure.functionNameBuilder()
                                       .toString();
        if (!functionMap.containsKey(name)) {
            throw new IncorrectMathFunctionException(
                    "The entered function is not processed in this calculator");
        }
        Function function = functionMap.get(funcDataStructure.functionNameBuilder()
                                                             .toString());
        function.setFunctionDataStructure(funcDataStructure);
        return Optional.of(function);
    }

    @SuppressWarnings({"ClassWithTooManyTransitiveDependencies", "CyclicClassDependency"})
    private interface Creator {

        Function create();
    }
}
