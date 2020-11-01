package io.javaclasses.mathcalculator.runtime;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a data structure that stores a name and value variable.
 */
public class VariableNameAndValuePair {

    private StringWriter name;
    private final List<Command> value = new ArrayList<>();

    public void addVariableName(StringWriter name) {
        this.name = name;
    }

    public void addValue(Command command) {
        this.value.add(command);
    }

    public StringWriter getName() {
        return name;
    }

    public List<Command> getValue() {
        return Collections.unmodifiableList(value);
    }
}
