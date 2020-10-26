package io.javaclasses.mathcalculator.fsm;

import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.math.FunctionDataStructure;
import io.javaclasses.mathcalculator.math.ShuntingYard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor} that starts {@link ExpressionStateAcceptor},
 * defines whether the transition from one state to expression state is possible
 * and if possible adds result as a function parameter to the outputChain.
 */
@SuppressWarnings({"ClassWithTooManyTransitiveDependencies", "CyclicClassDependency"})
public class ExpressionStateForFunctionAcceptor implements StateAcceptor<FunctionDataStructure> {

    /**
     * This API creates {@link ShuntingYard} for {@link ExpressionFiniteStateMachine},
     * starts FSM and adds result of shunting yard as a function parameter to the outputChain.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an {@link FunctionDataStructure} to which will be added result of ExpressionFSM.
     * @return the truth if the FSM has worked successfully
     *         and added the result to the outputChain, otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, FunctionDataStructure outputChain) {
        Logger logger = LoggerFactory.getLogger(
                ExpressionStateForFunctionAcceptor.class);

        FiniteStateMachine<ShuntingYard> expressionFSM = new ExpressionFiniteStateMachine();
        ShuntingYard shuntingYard = new ShuntingYard();
        if (expressionFSM.run(inputChain, shuntingYard) == FiniteStateMachine.Status.FINISHED) {
            outputChain.addFunctionParameter(Double.parseDouble(shuntingYard.popAllOperators()
                                                                            .toString()));
            if (logger.isInfoEnabled()) {
                logger.info(this.getClass() + " :" + shuntingYard.popAllOperators()
                                                                 .toString());
            }
            return true;
        }
        return false;
    }
}
