package io.javaclasses.mathcalculator.fsm;

import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.math.ShuntingYard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor} that starts {@link ExpressionStateAcceptor},
 * defines whether the transition from one state to number state is possible
 * and if possible adds result to the outputChain.
 */
public class NumberStateAcceptor implements StateAcceptor<ShuntingYard> {

    /**
     * This API creates {@link StringBuilder} for {@link NumberFiniteStateMachine}, starts FSM
     * and adds result of shunting yard to the outputChain.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an {@link ShuntingYard} to which will be added result of NumberFSM.
     * @return the truth if the FSM has worked successfully
     *         and added the result to the outputChain, otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, ShuntingYard outputChain) {
        Logger logger = LoggerFactory.getLogger(NumberStateAcceptor.class);

        NumberFiniteStateMachine numberFiniteStateMachine = new NumberFiniteStateMachine();
        StringWriter stringBuilder = new StringWriter();
        if (numberFiniteStateMachine.run(inputChain, stringBuilder) ==
                FiniteStateMachine.Status.FINISHED) {
            outputChain.pushOperand(Double.parseDouble(stringBuilder.toString()));
            if (logger.isInfoEnabled()) {
                logger.info(this.getClass() + " :" + stringBuilder.toString());
            }
            return true;
        }
        return false;
    }
}
