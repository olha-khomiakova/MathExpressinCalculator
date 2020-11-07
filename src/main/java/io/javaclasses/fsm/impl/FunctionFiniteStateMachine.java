package io.javaclasses.fsm.impl;

import io.javaclasses.fsm.api.FSMFactory;
import io.javaclasses.fsm.base.FiniteStateMachine;
import io.javaclasses.fsm.base.State;
import io.javaclasses.runtime.Command;
import io.javaclasses.runtime.FunctionFactory;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.Optional;

/**
 * Implementation of {@link FiniteStateMachine} for parsing function and procedure from string.
 * It may be like that:
 * 1) min(x,y)
 * 2) max(x,y)
 * 3) print(x)
 */
class FunctionFiniteStateMachine extends FiniteStateMachine<NameAndParametersOutputChain> {

    FunctionFiniteStateMachine(FSMFactory factory) {
        State<NameAndParametersOutputChain> name = new State<>(false, new NameStateAcceptor());
        State<NameAndParametersOutputChain> openingBrackets = new State<>(false,
                                                           new RequiredCharacterStateAcceptorFunction(
                                                                   '('));
        State<NameAndParametersOutputChain> expression = new State<>(false,
                                                      new ExpressionStateAcceptorDataStructure(
                                                              factory));
        State<NameAndParametersOutputChain> closingBrackets = new State<>(true,
                                                           new RequiredCharacterStateAcceptorFunction(
                                                                   ')'));
        State<NameAndParametersOutputChain> comma = new State<>(false,
                                                 new RequiredCharacterStateAcceptorFunction(
                                                         ','));

        name.addTransmission(openingBrackets);
        openingBrackets.addTransmission(expression);
        expression.addTransmission(comma);
        comma.addTransmission(expression);
        expression.addTransmission(closingBrackets);

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
    public Optional<Command> compile(CharacterIterator input) {
        NameAndParametersOutputChain nameAndParameters = new NameAndParametersOutputChain();
        Status status = run(input, nameAndParameters);
        if (status == Status.FINISHED) {
            return Optional.of(new FunctionFactory(nameAndParameters.name(),
                                                   nameAndParameters.parameters()).create());
        }
        return Optional.empty();
    }
}
