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

/**
 * Implementation of {@link FiniteStateMachine} for parsing mathematical expression from string.
 * For example, mathematical expression may be like these:
 * <p>
 * 1) "5.25+(10*2.1)-7.77"
 * 2) "0.1*max(5,10)/2"
 */
 class ExpressionFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    ExpressionFiniteStateMachine(FSMFactory factory) {
        State<List<Command>> calculated = new State<>(true, new FSMStateAcceptor(factory,
                                                                                 FSMFactory.TypeFSM.CALCULATED));
        State<List<Command>> binaryOperation = new State<>(false,
                                                           new BinaryOperatorStateAcceptor());

        calculated.addTransmission(binaryOperation);
        binaryOperation.addTransmission(calculated);

        registerPossibleStartState(Collections.singletonList(calculated));
    }

    /**
     * This api starts the machine and gets the parsing interrupt status.
     * If the status is FINISHED, it returns the {@link Optional<Command>}
     * in which the parsed commands are stored, else return Optional.empty();
     *
     * @param input
     *         is an iterable string with input data
     * @return the {@link Optional<Command>}, if the status of run is FINISHED,
     *         else return Optional.empty()
     */
    public Optional<Command> expression(CharacterIterator input) {

        List<Command> commands = new ArrayList<>();
        Status status = run(input, commands);
        if (status == Status.FINISHED) {
            return Optional.of(new PushExpressionCommand(commands));
        }
        return Optional.empty();
    }
}
