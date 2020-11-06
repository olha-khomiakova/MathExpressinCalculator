package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.PushVariableCommand;

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
class InitializationFiniteStateMachine extends FiniteStateMachine<DataStructure> {

    InitializationFiniteStateMachine(FSMFactory factory) {
        State<DataStructure> variable = new State<>(false,
                                                    new NameStateAcceptor());
        State<DataStructure> equalSign = new State<>(false,
                                                     new RequiredCharacterStateAcceptorFunction(
                                                             '='));
        State<DataStructure> expression = new State<>(true,
                                                      new ExpressionStateAcceptorDataStructure(
                                                              factory));

        variable.addTransmission(equalSign);
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
    public Optional<Command> initialization(CharacterIterator input) {
        DataStructure dataStructure = new DataStructure();
        Status status = run(input, dataStructure);
        if (status == Status.FINISHED) {
            return Optional.of(
                    new PushVariableCommand(dataStructure.name(), dataStructure.parameters()));
        }
        return Optional.empty();
    }

}
