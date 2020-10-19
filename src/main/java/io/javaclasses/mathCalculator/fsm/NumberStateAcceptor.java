package io.javaclasses.mathCalculator.fsm;

import io.javaclasses.mathCalculator.math.ShuntingYard;

import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor}.
 * It starts {@link NumberFiniteStateMachine}.
 * It defines whether the transition from one state to number state is possible.
 */
public class NumberStateAcceptor implements StateAcceptor<ShuntingYard> {
    @Override
    public boolean accept(CharacterIterator inputChain, ShuntingYard outputChain) {
        NumberFiniteStateMachine numberFiniteStateMachine = new NumberFiniteStateMachine();
        StringBuilder stringBuilder = new StringBuilder();
        if (numberFiniteStateMachine.run(inputChain, stringBuilder) == FiniteStateMachine.Status.FINISHED) {
            outputChain.pushNumber(Double.parseDouble(stringBuilder.toString()));
            return true;
        }
        return false;
    }
}
