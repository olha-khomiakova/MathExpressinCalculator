package io.javaclasses.mathCalculator.fsm;

import io.javaclasses.mathCalculator.math.ShuntingYard;

import static java.util.Arrays.asList;

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
        State openingBrackets = new State(false,
                new BracketsStateAcceptor('('));
        State closingParenthesis = new State(true,
                new BracketsStateAcceptor(')'));
        State expression = new State(false, new ExpressionStateAcceptor());

        expression.addTransmission(closingParenthesis);
        openingBrackets.addTransmission(expression);

        registerPossibleStartState(asList(openingBrackets));
    }
}
