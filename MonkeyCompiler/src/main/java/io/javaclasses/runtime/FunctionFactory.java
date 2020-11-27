package io.javaclasses.runtime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This service is factory that contains various of mathematical functions
 * and procedures.
 * Mathematical functions may be : min(), max().
 * Procedure may be : print().
 */
public class
FunctionFactory {

    private final Map<String, Creator> functionMap = new HashMap<>();
    private final String name;

    public FunctionFactory(String name, List<Command> commands) {
        this.name = name;
        functionMap.put("print", () -> new Print(commands));

        functionMap.put("min", () -> new MinFunction(
                commands));
        functionMap.put("max", () -> new MaxFunction(
                commands));
    }

    /**
     * This method checks if the function we need exists and returns an object of the needed type or
     * throws an exception.
     *
     * @return required function
     */
    public Function create() {
        if (!functionMap.containsKey(name)) {
            throw new IncorrectFunctionException(
                    "Cannot resolve \"" + name + "\".");
        }
        Function function = functionMap.get(name)
                                       .create();
        function.accept(function.size());
        return function;
    }

    private interface Creator {

        Function create();
    }
}
