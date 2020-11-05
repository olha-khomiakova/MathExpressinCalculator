package io.javaclasses.mathcalculator.runtime;

import java.util.LinkedHashMap;

import static io.javaclasses.mathcalculator.runtime.StringValueReader.readString;

class Memory {

    private final LinkedHashMap<String, ValueHolder> memory = new LinkedHashMap<>();

    void put(ValueHolder variableName, ValueHolder variableValue) {
        memory.put(readString(variableName), variableValue);
    }

    boolean containsKey(ValueHolder variableName) {
        return memory.containsKey(readString(variableName));
    }

    ValueHolder get(ValueHolder variableName) {
        return memory.get(readString(variableName));
    }

    void remove(ValueHolder variableName) {
        memory.remove(readString(variableName));
    }
}
