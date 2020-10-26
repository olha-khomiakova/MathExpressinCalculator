package io.javaclasses.mathCalculator.fsm;

import io.javaclasses.mathCalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathCalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathCalculator.math.FunctionDataStructure;
import io.javaclasses.mathCalculator.math.ShuntingYard;

import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor} that starts {@link ExpressionStateAcceptor},
 * defines whether the transition from one state to expression state is possible
 * and if possible adds result as a function parameter to the outputChain.
 */
public class ExpressionStateForFunctionAcceptor implements StateAcceptor<FunctionDataStructure>{
    /**
     * This API creates {@link ShuntingYard} for {@link ExpressionFiniteStateMachine},
     * starts FSM and adds result of shunting yard as a function parameter to the outputChain.
     *
     * @param inputChain  is an iterable string with input data
     * @param outputChain is an {@link FunctionDataStructure} to which will be added result of ExpressionFSM.
     * @return the truth if the FSM has worked successfully
     * and added the result to the outputChain, otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, FunctionDataStructure outputChain) {
        FiniteStateMachine<ShuntingYard> expressionFiniteStateMachine = new ExpressionFiniteStateMachine();
        ShuntingYard shuntingYard = new ShuntingYard();
        if (expressionFiniteStateMachine.run(inputChain, shuntingYard) == FiniteStateMachine.Status.FINISHED) {
            outputChain.addFunctionParameter(Double.parseDouble(shuntingYard.popAllOperators().toString()));
            return true;
        }
        return false;
    }
}
