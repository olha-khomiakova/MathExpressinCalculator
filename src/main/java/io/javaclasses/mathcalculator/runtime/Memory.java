package io.javaclasses.mathcalculator.runtime;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class Memory {

    private final LinkedHashMap<String, Double> memory = new LinkedHashMap<>();

    void put(String variableName, Double variableValue) {
        memory.put(variableName, variableValue);
    }

    boolean containsKey(String variableName) {
        return memory.containsKey(variableName);
    }

    double get(String variableName) {
        return memory.get(variableName);
    }

    void remove(String variableName) {
        memory.remove(variableName);
    }
    void pollLast() {
        int counter = 0;
        for (Map.Entry<String, Double> entry : memory.entrySet()) {
            if (counter == memory.size() - 1) {
                memory.remove(entry.getKey());
            }
            counter++;
        }
    }
}
