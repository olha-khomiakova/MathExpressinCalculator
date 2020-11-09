package io.javaclasses.fsm.impl;

import io.javaclasses.fsm.api.FSMFactory;
import io.javaclasses.fsm.base.FiniteStateMachine;
import io.javaclasses.fsm.base.State;
import io.javaclasses.runtime.Command;
import io.javaclasses.runtime.InitializeVariableCommand;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.Optional;

/**
 * Implementation of {@link FiniteStateMachine} to parse the variable initialization pattern from
 * string.
 * Pattern looks like "variable name" + " = " + " expression ".
 * <p>
 * 1) "a = 5"
 * 2) "res = 3<1"
 */
class InitializeVariableStatement extends FiniteStateMachine<StringAndCommandsDataStructure> {

    InitializeVariableStatement(FSMFactory factory) {

        State<StringAndCommandsDataStructure> variable = new State<>(false,
                                                                     new NameStateAcceptor());
        State<StringAndCommandsDataStructure> equalSign = new State<>(false,
                                                                      new RequiredCharacterStateAcceptorFunction(
                                                             '='));
        State<StringAndCommandsDataStructure> expression = new State<>(true,
                                                                       new ExpressionStateAcceptorDataStructure(
                                                              factory, FSMFactory.TypeFSM.EXPRESSION));
        State<StringAndCommandsDataStructure> booleanExpression = new State<>(true,
                                                                              new ExpressionStateAcceptorDataStructure(
                                                                             factory, FSMFactory.TypeFSM.BOOLEAN_EXPRESSION ));
        variable.addTransmission(equalSign);
        equalSign.addTransmission(booleanExpression);
        equalSign.addTransmission(expression);

        registerPossibleStartState(Collections.singletonList(variable));
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
    public Optional<Command> compile(CharacterIterator input) {

        StringAndCommandsDataStructure nameAndParameters = new StringAndCommandsDataStructure();
        Status status = run(input, nameAndParameters);
        if (status == Status.FINISHED) {
            return Optional.of(
                    new InitializeVariableCommand(nameAndParameters.name(), nameAndParameters.parameters()));
        }
        return Optional.empty();
    }

}
