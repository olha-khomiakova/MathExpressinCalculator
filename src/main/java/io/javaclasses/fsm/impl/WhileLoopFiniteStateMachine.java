package io.javaclasses.fsm.impl;

import io.javaclasses.fsm.api.FSMFactory;
import io.javaclasses.fsm.base.FiniteStateMachine;
import io.javaclasses.fsm.base.State;
import io.javaclasses.runtime.Command;
import io.javaclasses.runtime.PushLoopCommand;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.Optional;

/**
 * Implementation of {@link FiniteStateMachine} for parsing mathematical expression from string.
 * For example, mathematical expression may be like these:
 * <p>
 * 1) "5.25+(10*2.1)-7.77"
 * 2) "0.1*max(5,10)/2"
 */
class WhileLoopFiniteStateMachine extends FiniteStateMachine<DataStructureForLoop> {

    WhileLoopFiniteStateMachine(FSMFactory factory) {
        State<DataStructureForLoop> nameWhile = new State<>(false, new ReservedNameStateAcceptor("while"));
        State<DataStructureForLoop> condition = new State<>(false, new ConditionForLoopAcceptor(factory));
        State<DataStructureForLoop> openingCurlyBrackets = new State<>(false,
                                                                       new RequiredCharacterStateAcceptorLoop(
                                                                        '{'));
        State<DataStructureForLoop> statements = new State<>(false, new StatementForLoopAcceptor(factory));
        State<DataStructureForLoop> closingCurlyBrackets = new State<>(true,
                                                                       new RequiredCharacterStateAcceptorLoop(
                                                                        '}'));
        State<DataStructureForLoop> semicolon = new State<>(true,
                                                            new RequiredCharacterStateAcceptorLoop(
                                                                                ';'));

        nameWhile.addTransmission(condition);
        condition.addTransmission(openingCurlyBrackets);
        openingCurlyBrackets.addTransmission(statements);
        statements.addTransmission(closingCurlyBrackets);

        registerPossibleStartState(Collections.singletonList(nameWhile));
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

        DataStructureForLoop dataStructure = new DataStructureForLoop();
        Status status = run(input, dataStructure);
        if (status == Status.FINISHED) {
            return Optional.of(new PushLoopCommand(dataStructure.condition(), dataStructure.statements()));
        }
        return Optional.empty();
    }
}
