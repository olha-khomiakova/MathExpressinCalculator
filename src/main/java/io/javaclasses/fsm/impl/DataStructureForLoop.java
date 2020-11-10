package io.javaclasses.fsm.impl;

import io.javaclasses.runtime.Command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataStructureForLoop {

    private Command condition;
    private final List<Command> statements = new ArrayList<>();

    public Command condition() {
        return condition;
    }

    public List<Command> statements() {
        return Collections.unmodifiableList(statements);
    }

    public void pushCondition(Command command) {
        condition = command;
    }

    public void pushStatements(Command command) {
        statements.add(command);
    }

}
