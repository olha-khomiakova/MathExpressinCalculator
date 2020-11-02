package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.api.CompilerElement;
import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.FunctionDataStructure;
import io.javaclasses.mathcalculator.runtime.PushFunctionCommand;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.Optional;

/**
 * Implementation of {@link FiniteStateMachine} for parsing and evaluating function from string.
 * Function may be min(x,y) or max(x,y).
 */
public class FunctionFiniteStateMachine extends FiniteStateMachine<FunctionDataStructure> implements CompilerElement {

    public FunctionFiniteStateMachine(FSMFactory factory) {
        State<FunctionDataStructure> name = new State<>(false, new FunctionNameStateAcceptor());
        State<FunctionDataStructure> openingBrackets = new State<>(false,
                                                                   new RequiredCharacterStateAcceptorFunction(
                                                                           '('));
        State<FunctionDataStructure> expression = new State<>(false,
                                                              new ExpressionStateAcceptorFunctionDataStructure(factory));
        State<FunctionDataStructure> closingBrackets = new State<>(true,
                                                                   new RequiredCharacterStateAcceptorFunction(
                                                                           ')'));
        State<FunctionDataStructure> comma = new State<>(false,
                                                         new RequiredCharacterStateAcceptorFunction(
                                                                 ','));

        name.addTransmission(openingBrackets);
        openingBrackets.addTransmission(expression);
        expression.addTransmission(comma);
        comma.addTransmission(expression);
        expression.addTransmission(closingBrackets);

        registerPossibleStartState(Collections.singletonList(name));
    }

    @Override
    public Optional<Command> compile(CharacterIterator input) {
        FunctionDataStructure dataStructure = new FunctionDataStructure();
        Status status = run(input, dataStructure);
        if (status == Status.FINISHED) {
            dataStructure.validateFunction();
            return Optional.of(new PushFunctionCommand(dataStructure));
        }
        return Optional.empty();
    }
}
