package io.javaclasses.fsm.impl;

import io.javaclasses.fsm.api.CompilerFactory;
import io.javaclasses.fsm.api.CompilerType;
import io.javaclasses.fsm.base.FiniteStateMachine;
import io.javaclasses.fsm.base.State;
import io.javaclasses.runtime.Command;
import io.javaclasses.runtime.PushListCommand;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

/**
 * Implementation of {@link FiniteStateMachine} for parsing
 * and evaluating calculated expression from string.
 * It may be numbers, expression in brackets, function, variable.
 */
class CalculatedFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    CalculatedFiniteStateMachine(CompilerFactory factory) {
        State<List<Command>> number = new State<>(true, new NumberStateAcceptor(factory));
        State<List<Command>> expressionWithBrackets = new State<>(true,
                                                                  new FSMStateAcceptor(
                                                                          factory,
                                                                          CompilerType.EXPRESSION_WITH_BRACKETS));
        State<List<Command>> function = new State<>(true, new FSMStateAcceptor(factory,
                                                                               CompilerType.FUNCTION));
        State<List<Command>> variable = new State<>(true, new FSMStateAcceptor(factory,
                                                                               CompilerType.READ_VARIABLE));

        registerPossibleStartState(asList(number, expressionWithBrackets, function, variable));
    }

    /**
     * This api starts the machine and gets the parsing interrupt status.
     * If the status is FINISHED, it returns the {@link Optional<Command>}
     * in which the parsed commands are stored, else return Optional.empty();
     *
     * @param input
     *         is an iterable string with input data
     * @return {@link Optional<Command>}, if the status of run is FINISHED,
     *         else return Optional.empty()
     */
    Optional<Command> compile(CharacterIterator input) {
        List<Command> commands = new ArrayList<>();
        Status status = run(input, commands);
        if (status == Status.FINISHED) {
            return Optional.of(new PushListCommand(commands));
        }
        return Optional.empty();
    }
}
