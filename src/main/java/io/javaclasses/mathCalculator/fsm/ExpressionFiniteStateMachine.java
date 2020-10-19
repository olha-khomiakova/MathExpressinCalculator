package io.javaclasses.mathCalculator.fsm;

import io.javaclasses.mathCalculator.math.ShuntingYard;

/**
 * Implementation of {@link FiniteStateMachine} for parsing and evaluating expression from string.
 * For example, mathematical expression may be like these:
 * <p>
 * 1) "5.25+(10*2.1)-7.77"
 * 2) "0.1*(5+(10/2))"
 */
public class ExpressionFiniteStateMachine extends FiniteStateMachine<ShuntingYard> {
    public ExpressionFiniteStateMachine() {
        State number = new State(true, new NumberStateAcceptor());
        State binaryOperation = new State(false, new BinaryOperatorStateAcceptor());
        State expressionWithParenthesis = new State(true, new ExpressionWithParenthesisStateAcceptor());

        number.addTransmission(binaryOperation);
        binaryOperation.addTransmission(number);
        binaryOperation.addTransmission(expressionWithParenthesis);
        expressionWithParenthesis.addTransmission(binaryOperation);
        registerPossibleStartState(number, expressionWithParenthesis);
    }
}
