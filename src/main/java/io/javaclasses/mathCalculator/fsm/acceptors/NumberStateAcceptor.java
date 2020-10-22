package io.javaclasses.mathCalculator.fsm.acceptors;

import io.javaclasses.mathCalculator.IncorrectMathExpressionException;
import io.javaclasses.mathCalculator.fsm.NumberFiniteStateMachine;
import io.javaclasses.mathCalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathCalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathCalculator.math.ShuntingYard;

import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor}.
 * It starts {@link NumberFiniteStateMachine}.
 * It defines whether the transition from one state to number state is possible.
 */
public class NumberStateAcceptor implements StateAcceptor<ShuntingYard> {
    @Override
    public boolean accept(CharacterIterator inputChain, ShuntingYard outputChain) throws IncorrectMathExpressionException {
        NumberFiniteStateMachine numberFiniteStateMachine = new NumberFiniteStateMachine();
        StringBuilder stringBuilder = new StringBuilder();
        if (numberFiniteStateMachine.run(inputChain, stringBuilder) == FiniteStateMachine.Status.FINISHED) {
            outputChain.pushOperand(Double.parseDouble(stringBuilder.toString()));
            return true;
        }
        return false;
    }
}
