package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.DataStructure;
import io.javaclasses.mathcalculator.runtime.FunctionFactory;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.Optional;

/**
 * Implementation of {@link FiniteStateMachine} for parsing and evaluating function from string.
 * Function may be min(x,y) or max(x,y).
 */
public class FunctionFiniteStateMachine extends FiniteStateMachine<DataStructure> {

    public FunctionFiniteStateMachine(FSMFactory factory) {
        State<DataStructure> name = new State<>(false, new FunctionNameStateAcceptor());
        State<DataStructure> openingBrackets = new State<>(false,
                                                           new RequiredCharacterStateAcceptorFunction(
                                                                           '('));
        State<DataStructure> expression = new State<>(false,
                                                      new ExpressionStateAcceptorDataStructure(
                                                                      factory));
        State<DataStructure> closingBrackets = new State<>(true,
                                                           new RequiredCharacterStateAcceptorFunction(
                                                                           ')'));
        State<DataStructure> comma = new State<>(false,
                                                 new RequiredCharacterStateAcceptorFunction(
                                                                 ','));

        name.addTransmission(openingBrackets);
        openingBrackets.addTransmission(expression);
        expression.addTransmission(comma);
        comma.addTransmission(expression);
        expression.addTransmission(closingBrackets);

        registerPossibleStartState(Collections.singletonList(name));
    }

    public Optional<Command> function(CharacterIterator input) {
        DataStructure dataStructure = new DataStructure();
        Status status = run(input, dataStructure);
        if (status == Status.FINISHED) {
            return Optional.of(new FunctionFactory(dataStructure.name(),
                                                   dataStructure.parameters()).create()
                                                                              .get());
        }
        return Optional.empty();
    }
}
