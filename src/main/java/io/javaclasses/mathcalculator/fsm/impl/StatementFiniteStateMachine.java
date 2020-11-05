package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.PushExpressionCommand;

import java.text.CharacterIterator;
import java.util.ArrayList;
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
public class StatementFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    StatementFiniteStateMachine(FSMFactory factory) {
        State<List<Command>> initialization = new State<>(true, new FSMStateAcceptor(factory,
                                                                                      FSMFactory.TypeFSM.INITIALIZATION));
        State<List<Command>> procedure = new State<>(true, new FSMStateAcceptor(factory,
                                                                                FSMFactory.TypeFSM.FUNCTION));

        registerPossibleStartState(asList(initialization,procedure));
    }

    public Optional<Command> statement(CharacterIterator input) {
        List<Command> commands = new ArrayList<>();
        Status status = run(input, commands);
        if (status == Status.FINISHED) {
            return Optional.of(new PushExpressionCommand(commands));
        }
        return Optional.empty();
    }
}
