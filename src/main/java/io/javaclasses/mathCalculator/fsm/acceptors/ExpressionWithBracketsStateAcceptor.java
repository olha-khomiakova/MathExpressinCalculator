package io.javaclasses.mathCalculator.fsm.acceptors;

import io.javaclasses.mathCalculator.IncorrectMathExpressionException;
import io.javaclasses.mathCalculator.fsm.ExpressionWithBracketsFiniteStateMachine;
import io.javaclasses.mathCalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathCalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathCalculator.math.ShuntingYard;

import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor}.
 * It starts {@link ExpressionWithBracketsFiniteStateMachine}.
 * And defines whether the transition from one state to expression with parenthesis state is possible.
 */
public class ExpressionWithBracketsStateAcceptor implements StateAcceptor<ShuntingYard> {
    @Override
    public boolean accept(CharacterIterator inputChain, ShuntingYard outputChain) throws IncorrectMathExpressionException {
        FiniteStateMachine<ShuntingYard> expressionWithParenthesisFiniteStateMachine = new ExpressionWithBracketsFiniteStateMachine();
        ShuntingYard shuntingYard = new ShuntingYard();
        if (expressionWithParenthesisFiniteStateMachine.run(inputChain, shuntingYard) == FiniteStateMachine.Status.FINISHED) {
            outputChain.pushOperand(Double.parseDouble(shuntingYard.popAllOperators().toString()));
            return true;
        }
        return false;
    }
}
