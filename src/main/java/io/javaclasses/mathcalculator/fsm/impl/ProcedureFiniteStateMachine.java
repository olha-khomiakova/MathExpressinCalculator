package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.api.CompilerElement;
import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.FunctionDataStructure;
import io.javaclasses.mathcalculator.runtime.NameAndValuePair;
import io.javaclasses.mathcalculator.runtime.PushProcedureCommand;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.Optional;

/**
 * Implementation of {@link FiniteStateMachine} for parsing and evaluating function from string.
 * Function may be print(x) or clearScreen().
 */
public class ProcedureFiniteStateMachine extends FiniteStateMachine<NameAndValuePair> implements CompilerElement {

    public ProcedureFiniteStateMachine(FSMFactory factory) {
        State<NameAndValuePair> name = new State<>(false, new NameStateAcceptor());
        State<NameAndValuePair> openingBrackets = new State<>(false,
                                                                   new RequiredCharacterStateAcceptorNameAndValuePair(
                                                                           '('));
        State<NameAndValuePair> expression = new State<>(false,
                                                              new ExpressionStateAcceptorNameAndValuePair(factory));
        State<NameAndValuePair> closingBrackets = new State<>(true,
                                                                   new RequiredCharacterStateAcceptorNameAndValuePair(
                                                                           ')'));
        State<NameAndValuePair> comma = new State<>(false,
                                                         new RequiredCharacterStateAcceptorNameAndValuePair(
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
        NameAndValuePair pair = new NameAndValuePair();
        Status status = run(input, pair);
        if (status == Status.FINISHED) {
            return Optional.of(new PushProcedureCommand(pair));
        }
        return Optional.empty();
    }
}
