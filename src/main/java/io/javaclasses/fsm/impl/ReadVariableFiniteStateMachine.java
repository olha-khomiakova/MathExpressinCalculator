package io.javaclasses.fsm.impl;

import io.javaclasses.fsm.api.FSMFactory;
import io.javaclasses.fsm.base.FiniteStateMachine;
import io.javaclasses.fsm.base.State;
import io.javaclasses.runtime.Command;
import io.javaclasses.runtime.ReadVariableCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.text.CharacterIterator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

/**
 * Implementation of {@link FiniteStateMachine} for parsing number from string.
 * Numbers are integer or decimal, positive or negative.
 */
class ReadVariableFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    ReadVariableFiniteStateMachine(FSMFactory factory) {
        State<List<Command>> name = new State<>(false,
                                                       new FSMStateAcceptor(factory, FSMFactory.TypeFSM.READ_VARIABLE));


        registerPossibleStartState(Collections.singletonList(name));
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

        StringWriter output = new StringWriter();
        Status status = new NameBuilderFiniteStateMachine().run(input, output);
        if (status == Status.FINISHED) {
            return Optional.of(new ReadVariableCommand(output));
        }
        return Optional.empty();
    }
}
