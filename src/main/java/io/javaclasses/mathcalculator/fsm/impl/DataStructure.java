package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.runtime.Command;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a data structure that stores a name and values or parameters of functions, variable
 * or procedure.
 */
class DataStructure {

    private StringWriter name;
    private final List<Command> parameters = new ArrayList<>();

    void addFunctionName(StringWriter character) {
        this.name = character;
    }

    void addFunctionParameter(Command parameter) {
        this.parameters.add(parameter);
    }

    public List<Command> parameters() {
        return Collections.unmodifiableList(parameters);
    }

    public String name() {
        return name.toString();
    }

}
