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

        State<List<Command>> semicolon = new State<>(false,
                new RequiredCharacterStateAcceptorListCommands(';'));

        State<List<Command>> endOfProgram = new State<>(true,
                new RequiredCharacterStateAcceptorListCommands(CharacterIterator.DONE));

        statement.addTransmission(semicolon);
        semicolon.addTransmission(statement);
        semicolon.addTransmission(endOfProgram);

        registerPossibleStartState(Collections.singletonList(statement));
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
            return Optional.of(new PushExpressionCommand(commands));
        }
        return Optional.empty();
    }
}
