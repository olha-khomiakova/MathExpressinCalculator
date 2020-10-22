package io.javaclasses.mathCalculator.fsm;

import io.javaclasses.mathCalculator.fsm.acceptors.BracketsStateAcceptor;
import io.javaclasses.mathCalculator.fsm.acceptors.ExpressionStateAcceptor;
import io.javaclasses.mathCalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathCalculator.fsm.base.State;
import io.javaclasses.mathCalculator.math.ShuntingYard;

import java.util.Collections;

/**
 * Implementation of {@link FiniteStateMachine} for parsing
 * and evaluating expression with brackets from string.
 * The expression looks like this pattern: "("+"simple mathematical expression"+")".
 * For example:
 * 1) (5+2):
 * 2) (7.5+65*0.1).
 */
public class ExpressionWithBracketsFiniteStateMachine extends FiniteStateMachine<ShuntingYard> {
    public ExpressionWithBracketsFiniteStateMachine() {
        State<ShuntingYard> openingBrackets = new State<>(false,
                new BracketsStateAcceptor('('));
        State<ShuntingYard> closingParenthesis = new State<>(true,
                new BracketsStateAcceptor(')'));
        State<ShuntingYard> expression = new State<>(false, new ExpressionStateAcceptor());

        expression.addTransmission(closingParenthesis);
        openingBrackets.addTransmission(expression);

        registerPossibleStartState(Collections.singletonList(openingBrackets));
    }
}
