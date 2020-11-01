package io.javaclasses.language;

import io.javaclasses.mathcalculator.fsm.FSMFactoryImpl;
import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.RuntimeEnvironment;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

public class Compiler {

    public void compile(String code) {
        CharacterIterator stringNumber = new StringCharacterIterator(code);
        RuntimeEnvironment environment = new RuntimeEnvironment();
        FSMFactory<List<Command>> factory = new FSMFactoryImpl<>();
        FiniteStateMachine<List<Command>> fsm = factory.create(FSMFactory.TypeFSM.STATEMENT);
        List<Command> commandList = new ArrayList<>();
        FiniteStateMachine.Status accepted = fsm.run(stringNumber, commandList);
        if (stringNumber.getIndex() != stringNumber.getEndIndex() || accepted ==
                FiniteStateMachine.Status.NOT_STARTED) {
            throw new IncorrectStatementException("Incorrectly entered statement in position " +
                                                          stringNumber.getIndex() + '.',
                                                  stringNumber.getIndex());
        }
        for (Command command : commandList) {
            command.execute(environment);
        }
    }
}
