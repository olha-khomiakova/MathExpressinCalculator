package io.javaclasses.mathCalculator.fsm;

import io.javaclasses.mathCalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathCalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathCalculator.math.FunctionDataStructure;
import io.javaclasses.mathCalculator.math.ShuntingYard;

import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor} that defines
 * whether the transition from one state to function state is possible.
 */
public class FunctionStateAcceptor implements StateAcceptor<ShuntingYard> {
    /**
     * This API creates {@link FunctionFiniteStateMachine}
     * and a function data structure {@link FunctionDataStructure}.
     * This structure is filled with data during the operation of FSM,
     * and then calculates the value of the function.
     * <p>
     *
     * @param inputChain  is an iterable string with input data
     * @param outputChain is an {@link ShuntingYard} to which will be added result of {@link FunctionDataStructure}.
     * @return the truth if the FSMs have worked successfully and added the result to the outputChain,
     * otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, ShuntingYard outputChain) {
        FunctionDataStructure functionDataStructure = new FunctionDataStructure();

        FunctionFiniteStateMachine functionParametersFSM =
                new FunctionFiniteStateMachine();
        if (functionParametersFSM.run(inputChain, functionDataStructure) == FiniteStateMachine.Status.FINISHED) {
            outputChain.pushOperand(functionDataStructure.calculate());
            return true;
        }
        return false;
    }

}
