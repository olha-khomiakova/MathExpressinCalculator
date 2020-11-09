package io.javaclasses.fsm.impl;

import io.javaclasses.runtime.Command;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a data structure that stores a String and command list.
 */
class StringAndCommandsDataStructure {

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
