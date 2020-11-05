package io.javaclasses.mathcalculator.runtime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FunctionFactory {

    private final Map<String, Creator> functionMap = new HashMap<>();
    private final String name;

    public FunctionFactory(String name, List<Command> commands) {
        this.name = name;
        functionMap.put("print", () -> new Print(1, 100, commands));
//        functionMap.put("delete", () -> new Delete(1, 1,
//                                                   commands));
        functionMap.put("min", () -> new MinFunction(2, 2,
                                                     commands));
        functionMap.put("max", () -> new MaxFunction(2, 2,
                                                     commands));
    }

    /**
     * This method checks if the function we need exists and returns an object of the needed type or
     * throws an exception.
     *
     * @return required function
     */
    public Optional<Function> create() {
        if (!functionMap.containsKey(name)) {
            throw new IncorrectFunctionException(
                    "Cannot resolve \""+name+"\".");
        }
        return Optional.of(functionMap.get(name).create());
    }

    private interface Creator {
        Function create();
    }
}
