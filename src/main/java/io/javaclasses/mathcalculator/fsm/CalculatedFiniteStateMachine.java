package io.javaclasses.mathcalculator.fsm;

import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.runtime.Command;

import java.text.CharacterIterator;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Implementation of {@link FiniteStateMachine} for parsing
 * and evaluating calculated expression from string.
 * It may be numbers, function, expression in brackets.
 */
class CalculatedFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    CalculatedFiniteStateMachine() {
        State<List<Command>> number = new State<>(true, new NumberStateAcceptor());
        State<List<Command>> expressionWithBrackets = new State<>(true,
                                                                  new ExpressionWithBracketsStateAcceptor());
        State<List<Command>> function = new State<>(true, new FunctionStateAcceptor());

        registerPossibleStartState(asList(number, expressionWithBrackets, function));
    }

    public boolean calculated(CharacterIterator input, List<Command> output) {
        Status status = run(input, output);
        return status == Status.FINISHED;
    }
}
