package io.javaclasses.mathCalculator.fsm;

import io.javaclasses.mathCalculator.fsm.acceptors.*;
import io.javaclasses.mathCalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathCalculator.fsm.base.State;
import io.javaclasses.mathCalculator.math.ShuntingYard;

import static java.util.Arrays.asList;

/**
 * Implementation of {@link FiniteStateMachine} for parsing and evaluating expression from string.
 * For example, mathematical expression may be like these:
 * <p>
 * 1) "5.25+(10*2.1)-7.77"
 * 2) "0.1*(5+(10/2))"
 */
public class ExpressionFiniteStateMachine extends FiniteStateMachine<ShuntingYard> {
    public ExpressionFiniteStateMachine() {
        State<ShuntingYard> number = new State<>(true, new NumberStateAcceptor());
        State<ShuntingYard> binaryOperation = new State<>(false,  new BinaryOperatorStateAcceptor());
        State<ShuntingYard> expressionWithParenthesis = new State<>(true, new ExpressionWithBracketsStateAcceptor());
        State<ShuntingYard> function = new State<>(false, new FunctionStateAcceptor());

        number.addTransmission(binaryOperation);
        binaryOperation.addTransmission(number);
        binaryOperation.addTransmission(expressionWithParenthesis);
        expressionWithParenthesis.addTransmission(binaryOperation);
        function.addTransmission(binaryOperation);
        binaryOperation.addTransmission(function);
        registerPossibleStartState(asList(number, expressionWithParenthesis, function));
    }
}
