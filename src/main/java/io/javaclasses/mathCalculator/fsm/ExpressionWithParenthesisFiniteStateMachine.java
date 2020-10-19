package io.javaclasses.mathCalculator.fsm;

import io.javaclasses.mathCalculator.math.ShuntingYard;

/**
 * Implementation of {@link FiniteStateMachine} for parsing
 * and evaluating expression with parenthesis from string.
 * The expression looks like this pattern: "("+"simple mathematical expression"+")".
 * For example:
 * 1) (5+2):
 * 2) (7.5+65*0.1).
 */
public class ExpressionWithParenthesisFiniteStateMachine extends FiniteStateMachine<ShuntingYard> {
    public ExpressionWithParenthesisFiniteStateMachine() {
        State openingParenthesis = new State(false,
                new ParenthesisStateAcceptor('('));
        State closingParenthesis = new State(true,
                new ParenthesisStateAcceptor(')'));
        State expression = new State(false, new ExpressionStateAcceptor());

        expression.addTransmission(closingParenthesis);
        openingParenthesis.addTransmission(expression);

        registerPossibleStartState(openingParenthesis);
    }
}
