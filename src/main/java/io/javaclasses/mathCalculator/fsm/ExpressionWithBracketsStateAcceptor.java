package io.javaclasses.mathCalculator.fsm;

import io.javaclasses.mathCalculator.math.ShuntingYard;

import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor}.
 * It starts {@link ExpressionWithBracketsFiniteStateMachine}.
 * And defines whether the transition from one state to expression with parenthesis state is possible.
 */
public class ExpressionWithBracketsStateAcceptor implements StateAcceptor<ShuntingYard> {
    @Override
    public boolean accept(CharacterIterator inputChain, ShuntingYard outputChain) {
        FiniteStateMachine<ShuntingYard> expressionWithParenthesisFiniteStateMachine = new ExpressionWithBracketsFiniteStateMachine();
        ShuntingYard shuntingYard = new ShuntingYard();
        if (expressionWithParenthesisFiniteStateMachine.run(inputChain, shuntingYard) == FiniteStateMachine.Status.FINISHED) {
            outputChain.pushNumber(Double.parseDouble(shuntingYard.popAllOperators().toString()));
            return true;
        }
        return false;
    }
}
