package io.javaclasses.mathcalculator.fsm;

import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.math.ShuntingYard;

import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor} that starts {@link ExpressionStateAcceptor},
 * defines whether the transition from one state to calculated state is possible
 * and if possible adds result to the outputChain.
 */
@SuppressWarnings("CyclicClassDependency")
public class CalculatedStateAcceptor implements StateAcceptor<ShuntingYard> {

    /**
     * This API creates {@link ShuntingYard} for {@link CalculatedFiniteStateMachine}, starts FSM
     * and adds result of shunting yard to the outputChain.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an {@link ShuntingYard} to which will be added result of CalculatedFSM.
     * @return the truth if the FSM has worked successfully
     *         and added the result to the outputChain, otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, ShuntingYard outputChain) {
        CalculatedFiniteStateMachine calculatedFSM = new CalculatedFiniteStateMachine();
        ShuntingYard shuntingYard = new ShuntingYard();
        if (calculatedFSM.run(inputChain, shuntingYard) == FiniteStateMachine.Status.FINISHED) {
            outputChain.pushOperand(Double.parseDouble(shuntingYard.popAllOperators()
                                                                   .toString()));
            return true;
        }
        return false;
    }
}
