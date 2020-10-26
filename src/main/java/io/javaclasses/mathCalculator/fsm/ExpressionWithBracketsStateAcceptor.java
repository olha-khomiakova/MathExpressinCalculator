package io.javaclasses.mathCalculator.fsm;

import io.javaclasses.mathCalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathCalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathCalculator.math.ShuntingYard;

import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor} that starts {@link ExpressionWithBracketsFiniteStateMachine},
 * defines whether the transition from one state to expression with brackets state is possible
 * and if possible adds result to the outputChain.
 */
public class ExpressionWithBracketsStateAcceptor implements StateAcceptor<ShuntingYard> {
    /**
     * This API creates {@link ShuntingYard} for {@link ExpressionWithBracketsFiniteStateMachine},
     * starts FSM and adds result of shunting yard to the outputChain.
     *
     * @param inputChain  is an iterable string with input data
     * @param outputChain is an {@link ShuntingYard} to which will be added result of ExpressionWithBracketsFSM.
     * @return the truth if the FSM has worked successfully
     * and added the result to the outputChain, otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, ShuntingYard outputChain) {
        FiniteStateMachine<ShuntingYard> expressionWithBracketsFiniteStateMachine
                = new ExpressionWithBracketsFiniteStateMachine();
        ShuntingYard shuntingYard = new ShuntingYard();
        if (expressionWithBracketsFiniteStateMachine.run(inputChain, shuntingYard)
                == FiniteStateMachine.Status.FINISHED) {
            outputChain.pushOperand(Double.parseDouble(shuntingYard.popAllOperators().toString()));
            return true;
        }
        return false;
    }
}
