package io.javaclasses.fsm.impl;

import io.javaclasses.fsm.api.FSMFactory;
import io.javaclasses.fsm.base.FiniteStateMachine;
import io.javaclasses.fsm.base.State;
import io.javaclasses.runtime.Command;
import io.javaclasses.runtime.PushBooleanExpressionCommand;
import io.javaclasses.runtime.PushExpressionCommand;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

/**
 * Implementation of {@link FiniteStateMachine} for parsing mathematical expression from string.
 * For example, mathematical expression may be like these:
 * <p>
 * 1) "5.25+(10*2.1)-7.77"
 * 2) "0.1*max(5,10)/2"
 */
class BooleanExpressionFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    BooleanExpressionFiniteStateMachine(FSMFactory factory) {

        State<List<Command>> calculated1 = new State<>(false, new FSMStateAcceptor(factory,
                                                                                 FSMFactory.TypeFSM.EXPRESSION));

        State<List<Command>> booleanOperation = new State<>(false,
                                                           new BooleanOperatorStateAcceptor());
        State<List<Command>> booleanCalculated2 = new State<>(true, new FSMStateAcceptor(factory,
                                                                                  FSMFactory.TypeFSM.BOOLEAN_EXPRESSION));
        State<List<Command>> calculated2 = new State<>(true, new FSMStateAcceptor(factory,
                                                                                  FSMFactory.TypeFSM.EXPRESSION));

        booleanOperation.addTransmission(booleanCalculated2);
        calculated1.addTransmission(booleanOperation);
        booleanOperation.addTransmission(calculated2);

        registerPossibleStartState(asList(calculated1));
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
    public Optional<Command> compile(CharacterIterator input) {

        List<Command> commands = new ArrayList<>();
        int index = input.getIndex();
        Status status = run(input, commands);
        if (status == Status.FINISHED) {
            return Optional.of(new PushBooleanExpressionCommand(commands));
        }
        input.setIndex(index);
        return Optional.empty();
    }
}
