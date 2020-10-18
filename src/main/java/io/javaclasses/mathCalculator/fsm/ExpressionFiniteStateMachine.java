package io.javaclasses.mathCalculator.fsm;

import io.javaclasses.mathCalculator.math.ShuntingYard;

/**
 * Implementation of {@link FiniteStateMachine} for parsing and evaluating expression from string.
 */
public class ExpressionFiniteStateMachine extends FiniteStateMachine <ShuntingYard>{
    public ExpressionFiniteStateMachine() {
        State number = new State(true, new NumberStateAcceptor());
        State binaryOperation = new State(false, new BinaryOperatorStateAcceptor());

        number.addTransmission(binaryOperation);
        binaryOperation.addTransmission(number);

        registerPossibleStartState(number);
    }
}
