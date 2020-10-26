package io.javaclasses.mathCalculator.math;

import io.javaclasses.mathCalculator.IncorrectMathExpressionException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
/**
 * This is factory that creates the a class of the type {@link Function}.
 */
public class FunctionFactory {
    private final Map<String, Function> functionMap = new HashMap<>();

    public FunctionFactory() {
        functionMap.put("min", ((Creator) () -> new MinFunction(2, 2)).create());
        functionMap.put("max", ((Creator) () -> new MaxFunction(2, 2)).create());
    }

    /**
     * This method checks if the function we need exists and returns an object of the needed type or
     * throws an exception.
     * @param functionDataStructure has information about function name and parameters.
     * @return required function
     */
    public Optional<Function> getRequiredFunction(FunctionDataStructure functionDataStructure) {
        String name = functionDataStructure.FunctionNameBuilder().toString();
        if(!functionMap.containsKey(name))
        {
            throw new IncorrectMathExpressionException("The entered function is not processed in this calculator");
        }
        Function function = functionMap.get(functionDataStructure.FunctionNameBuilder().toString());
        function.setFunctionDataStructure(functionDataStructure);
        return Optional.of(function);
    }
   interface Creator{
        Function create();
   }
}
