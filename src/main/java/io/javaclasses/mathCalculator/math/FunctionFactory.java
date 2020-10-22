package io.javaclasses.mathCalculator.math;

import io.javaclasses.mathCalculator.IncorrectMathExpressionException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FunctionFactory {
    private final Map<String, Function> functionMap = new HashMap<>();

    public FunctionFactory() {
        functionMap.put("min", ((Creator) () -> new MinFunction(2, 2)).create());
        functionMap.put("max", ((Creator) () -> new MaxFunction(2, 2)).create());
    }

    public Optional<Function> getRequiredFunction(FunctionDataStructure functionDataStructure) throws IncorrectMathExpressionException {
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
