package io.javaclasses.mathcalculator.fsm;

import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.math.FunctionDataStructure;
import io.javaclasses.mathcalculator.math.ShuntingYard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor} that defines
 * whether the transition from one state to function state is possible.
 */
@SuppressWarnings({"ClassWithTooManyTransitiveDependencies", "CyclicClassDependency"})
public class FunctionStateAcceptor implements StateAcceptor<ShuntingYard> {

    /**
     * This API creates {@link FunctionFiniteStateMachine}
     * and a function data structure {@link FunctionDataStructure}.
     * This structure is filled with data during the operation of FSM,
     * and then calculates the value of the function.
     * <p>
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an {@link ShuntingYard} to which will be added result of {@link
     *         FunctionDataStructure}.
     * @return the truth if the FSMs have worked successfully and added the result to the
     *         outputChain,
     *         otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, ShuntingYard outputChain) {
        Logger logger = LoggerFactory.getLogger(FunctionStateAcceptor.class);

        FunctionDataStructure functionDataStructure = new FunctionDataStructure();
        FunctionFiniteStateMachine functionFSM =
                new FunctionFiniteStateMachine();
        if (functionFSM.run(inputChain, functionDataStructure) ==
                FiniteStateMachine.Status.FINISHED) {
            outputChain.pushOperand(functionDataStructure.calculate());
            if (logger.isInfoEnabled()) {
                logger.info(this.getClass() + " :" + functionDataStructure.functionNameBuilder() +
                                    "()");
            }
            return true;
        }
        return false;
    }

}
