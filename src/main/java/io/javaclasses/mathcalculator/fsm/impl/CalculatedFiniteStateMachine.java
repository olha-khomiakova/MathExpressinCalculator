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
 * and evaluating calculated expression from string.
 * It may be numbers, expression in brackets, function, variable.
 */
class CalculatedFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    CalculatedFiniteStateMachine(FSMFactory factory) {
        State<List<Command>> number = new State<>(true, new NumberStateAcceptor(factory));
        State<List<Command>> expressionWithBrackets = new State<>(true,
                                                                  new FSMStateAcceptor(
                                                                          factory,
                                                                          FSMFactory.TypeFSM.EXPRESSION_WITH_BRACKETS));
        State<List<Command>> function = new State<>(true, new FSMStateAcceptor(factory,
                                                                               FSMFactory.TypeFSM.FUNCTION));
        State<List<Command>> variable = new State<>(true, new VariableStateAcceptor());

        registerPossibleStartState(asList(number, expressionWithBrackets, function, variable));
    }

    /**
     * This api starts the machine and gets the parsing interrupt status.
     * If the status is FINISHED, it returns the {@link Optional<Command>}
     * in which the parsed commands are stored, else return Optional.empty();
     *
     * @param input
     *         is an iterable string with input data
     * @return returns the {@link Optional<Command>}, if the status of run is FINISHED,
     *         else return Optional.empty();
     */
    Optional<Command> calculated(CharacterIterator input) {
        List<Command> commands = new ArrayList<>();
        Status status = run(input, commands);
        if (status == Status.FINISHED) {
            return Optional.of(new PushExpressionCommand(commands));
        }
        return Optional.empty();
    }
}
