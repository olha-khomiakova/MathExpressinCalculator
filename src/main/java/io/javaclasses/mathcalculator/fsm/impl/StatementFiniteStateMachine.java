package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.runtime.Command;

import java.util.Collections;
import java.util.List;

/**
 * Implementation of {@link FiniteStateMachine} for parsing
 * any statement from string.
 * For example, statement may be like these:
 * <p>
 * 1) "a=5;"
 * 2) "print(a);"
 */
public class StatementFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    public StatementFiniteStateMachine() {
        State<List<Command>> initialization = new State<>(false, new InitializationStateAcceptor());
        State<List<Command>> semicolon = new State<>(true,
                                                     new RequiredCharacterStateAcceptorListCommands(
                                                             ';'));
        initialization.addTransmission(semicolon);
        semicolon.addTransmission(initialization);

        registerPossibleStartState(Collections.singletonList(initialization));
    }

//    public Optional<Command> expression(CharacterIterator inputChain, List<Command> output) {
//
//        List<Command> commands = new ArrayList<>();
//        Status status = run(inputChain, commands);
//        if (status == Status.FINISHED) {
//            return Optional.of(new PushExpressionCommand(commands));
//        }
//        return Optional.empty();
//    }
}
