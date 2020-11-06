package io.javaclasses.mathcalculator.runtime;

import java.util.HashMap;
import java.util.Map;

import static io.javaclasses.mathcalculator.runtime.StringValueReader.readString;

/**
 * This is data structure for store the name of variables and their values and processed it.
 */
class Memory {

    private final Map<String, ValueHolder> memory = new HashMap<>();

    void put(ValueHolder variableName, ValueHolder variableValue) {
        memory.put(readString(variableName), variableValue);
    }

    boolean containsKey(ValueHolder variableName) {
        return memory.containsKey(readString(variableName));
    }

    ValueHolder get(ValueHolder variableName) {
        return memory.get(readString(variableName));
    }

    void replace(String name, ValueHolder variableName) {
        memory.replace(name, variableName);
    }
}
