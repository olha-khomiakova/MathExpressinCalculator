package io.javaclasses.fsm.impl;

import io.javaclasses.fsm.api.FSMFactory;
import io.javaclasses.fsm.base.FiniteStateMachine;
import io.javaclasses.fsm.base.State;
import io.javaclasses.runtime.Command;
import io.javaclasses.runtime.PushListCommand;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.javaclasses.fsm.api.FSMFactory.TypeFSM.BOOLEAN_IN_BRACKETS;
import static io.javaclasses.fsm.api.FSMFactory.TypeFSM.NEGATIVE_BOOLEAN;
import static io.javaclasses.fsm.api.FSMFactory.TypeFSM.READ_VARIABLE;
import static java.util.Arrays.asList;

/**
 * Implementation of {@link FiniteStateMachine} for parsing
 * and evaluating calculated expression from string.
 * It may be numbers, expression in brackets, function, variable.
 */
class NegativeBooleanFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    NegativeBooleanFiniteStateMachine(FSMFactory factory) {
        State<List<Command>> negationUnaryOperatorForCondition = new State<>(true,
                                                                 new NegationUnaryOperatorStateAcceptor(factory,
                                                                                                        BOOLEAN_IN_BRACKETS));
        State<List<Command>> negationUnaryOperatorForVariable = new State<>(true,
                                                                 new NegationUnaryOperatorStateAcceptor(factory, READ_VARIABLE));

        registerPossibleStartState(asList(negationUnaryOperatorForCondition, negationUnaryOperatorForVariable));
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
        int index= input.getIndex();
        List<Command> commands = new ArrayList<>();
        Status status = run(input, commands);
        if (status == Status.FINISHED) {
            return Optional.of(new PushListCommand(commands));
        }
        input.setIndex(index);
        return Optional.empty();
    }
}
