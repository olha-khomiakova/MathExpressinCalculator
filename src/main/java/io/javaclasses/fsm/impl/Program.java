package io.javaclasses.fsm.impl;

import io.javaclasses.fsm.api.FSMFactory;
import io.javaclasses.fsm.base.FiniteStateMachine;
import io.javaclasses.fsm.base.State;
import io.javaclasses.runtime.Command;
import io.javaclasses.runtime.PushListCommand;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

/**
 * Implementation of {@link FiniteStateMachine} for parsing code block.
 * For example, code block may be like these:
 * <p>
 * 1) " a = 5>6; print(a);"
 * 2) "max=3.9543; result = max < min(2+2,3+3)*2; print(result);"
 */
class Program extends FiniteStateMachine<List<Command>> {

    Program(FSMFactory factory) {

        State<List<Command>> statement = new State<>(false, new FSMStateAcceptor(factory,
                FSMFactory.TypeFSM.STATEMENT));

        State<List<Command>> semicolon = new State<>(true,
                new RequiredCharacterStateAcceptorListCommands(';'));

        State<List<Command>> whileCycle = new State<>(true, new FSMStateAcceptor(factory,
                                                                                 FSMFactory.TypeFSM.WHILE_CYCLE));


        State<List<Command>> endOfProgram = new State<>(true,
                new RequiredCharacterStateAcceptorListCommands(CharacterIterator.DONE));

        statement.addTransmission(semicolon);
        semicolon.addTransmission(whileCycle);
        semicolon.addTransmission(statement);
        semicolon.addTransmission(endOfProgram);
        whileCycle.addTransmission(whileCycle);
        whileCycle.addTransmission(statement);

        registerPossibleStartState(asList(whileCycle, statement));
    }

    /**
     * This api starts the machine and gets the parsing interrupt status.
     * If the status is FINISHED, it returns the {@link Optional<Command>}
     * in which the parsed commands are stored, else return Optional.empty();
     *
     * @param input is an iterable string with input data
     * @return {@link Optional<Command>}, if the status of run is FINISHED,
     * else return Optional.empty()
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
