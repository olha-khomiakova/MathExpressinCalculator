package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.api.CompilerElement;
import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.PushExpressionCommand;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

/**
 * Implementation of {@link FiniteStateMachine} for parsing
 * and evaluating calculated expression from string.
 * It may be numbers, function, expression in brackets.
 */
public class CalculatedFiniteStateMachine extends FiniteStateMachine<List<Command>> implements CompilerElement {

     CalculatedFiniteStateMachine(FSMFactory factory) {
        State<List<Command>> number = new State<>(true, new NumberStateAcceptor(factory));
        State<List<Command>> expressionWithBrackets = new State<>(true,
                                                                  new ExpressionWithBracketsStateAcceptor(factory));
        State<List<Command>> function = new State<>(true, new FunctionStateAcceptor(factory));
        State<List<Command>> variable = new State<>(true, new VariableStateAcceptor());

        registerPossibleStartState(asList(number, expressionWithBrackets, function, variable));
    }

    @Override
    public Optional<Command> compile(CharacterIterator input) {
        List<Command> commands = new ArrayList<>();
        Status status = run(input, commands);
        if (status == Status.FINISHED) {
            return Optional.of(new PushExpressionCommand(commands));
        }
        return Optional.empty();
    }
}
