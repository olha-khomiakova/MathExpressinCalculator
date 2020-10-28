package io.javaclasses.mathcalculator.fsm;

import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.PushShuntingYardCommand;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link FiniteStateMachine} for parsing
 * and evaluating mathematical expression with brackets from string.
 * The expression looks like this pattern: "("+"{@link ExpressionFiniteStateMachine}"+")".
 * For example:
 * 1) (5+2);
 * 2) (min(3.5,2)*0.1).
 */
class ExpressionWithBracketsFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    ExpressionWithBracketsFiniteStateMachine() {
        State<List<Command>> openingBrackets = new State<>(false,
                                                           new BracketsStateAcceptor('('));
        State<List<Command>> closingParenthesis = new State<>(true,
                                                              new BracketsStateAcceptor(')'));
        State<List<Command>> expression = new State<>(false, new ExpressionStateAcceptor());

        expression.addTransmission(closingParenthesis);
        openingBrackets.addTransmission(expression);

        registerPossibleStartState(Collections.singletonList(openingBrackets));
    }

    public Optional<Command> expressionWithBrackets
            (CharacterIterator inputChain, List<Command> outputChain) {
        List<Command> commands = new ArrayList<>();
        Status status = run(inputChain, commands);
        if (status == Status.FINISHED) {
            return Optional.of(new PushShuntingYardCommand(commands));
        }
        return Optional.empty();
    }
}
