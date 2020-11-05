package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.PushExpressionCommand;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

/**
 * Implementation of {@link FiniteStateMachine} for parsing
 * any statement from string.
 * For example, statement may be like these:
 * <p>
 * 1) "a=5;"
 * 2) "print(a);"
 */
public class ProgramFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    ProgramFiniteStateMachine(FSMFactory factory) {
        State<List<Command>> statement = new State<>(false, new FSMStateAcceptor(factory,
                                                                                 FSMFactory.TypeFSM.STATEMENT));
        State<List<Command>> semicolon = new State<>(true,
                                                     new RequiredCharacterStateAcceptorListCommands(
                                                             ';'));

        statement.addTransmission(semicolon);
        semicolon.addTransmission(statement);
        registerPossibleStartState(Collections.singletonList(statement));
    }

    public Optional<Command> program(CharacterIterator input) {
        List<Command> commands = new ArrayList<>();
        Status status = run(input, commands);
        if (status == Status.FINISHED) {
            return Optional.of(new PushExpressionCommand(commands));
        }
        return Optional.empty();
    }
}
