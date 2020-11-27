package io.javaclasses.runtime;

import java.util.HashMap;
import java.util.Map;

import static io.javaclasses.runtime.StringValueReader.readString;

/**
 * This is data structure for store the name of variables and their values and processed it.
 */
public class Memory {

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

}
