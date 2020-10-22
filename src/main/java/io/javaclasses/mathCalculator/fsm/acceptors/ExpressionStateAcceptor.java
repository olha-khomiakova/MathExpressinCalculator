package io.javaclasses.mathCalculator.fsm.acceptors;

import io.javaclasses.mathCalculator.IncorrectMathExpressionException;
import io.javaclasses.mathCalculator.fsm.ExpressionFiniteStateMachine;
import io.javaclasses.mathCalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathCalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathCalculator.math.ShuntingYard;

import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor}.
 * It starts {@link ExpressionStateAcceptor}.
 * It defines whether the transition from one state to expression state is possible.
 */
public class ExpressionStateAcceptor implements StateAcceptor<ShuntingYard>{
    @Override
    public boolean accept(CharacterIterator inputChain, ShuntingYard outputChain) throws IncorrectMathExpressionException {
        FiniteStateMachine<ShuntingYard> expressionFiniteStateMachine = new ExpressionFiniteStateMachine();
        ShuntingYard shuntingYard = new ShuntingYard();
        if (expressionFiniteStateMachine.run(inputChain, shuntingYard) == FiniteStateMachine.Status.FINISHED) {
            outputChain.pushOperand(Double.parseDouble(shuntingYard.popAllOperators().toString()));
            return true;
        }
        return false;
    }
}
