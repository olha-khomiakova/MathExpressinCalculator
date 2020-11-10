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
 * Implementation of {@link FiniteStateMachine} for parsing statement from string.
 * For example, statement may be like these:
 * <p>
 * 1) "a=5;"
 * 2) "print(a);"
 */
class Statement extends FiniteStateMachine<List<Command>> {

    Statement(CompilerFactory factory) {
        State<List<Command>> initialization = new State<>(true, new FSMStateAcceptor(factory,
                                                                                     CompilerType.INITIALIZATION));
        State<List<Command>> procedure = new State<>(true, new FSMStateAcceptor(factory,
                                                                                CompilerType.FUNCTION));

        registerPossibleStartState(asList(initialization, procedure));
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
