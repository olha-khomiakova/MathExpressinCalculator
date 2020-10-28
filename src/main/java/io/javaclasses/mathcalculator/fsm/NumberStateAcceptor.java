package io.javaclasses.mathcalculator.fsm;

import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.PushOperandCommand;
import io.javaclasses.mathcalculator.runtime.ShuntingYard;

import java.io.StringWriter;
import java.text.CharacterIterator;
import java.util.List;

/**
 * Implementation of {@link StateAcceptor} that starts {@link ExpressionStateAcceptor},
 * defines whether the transition from one state to number state is possible
 * and if possible adds result to the outputChain.
 */
public class NumberStateAcceptor implements StateAcceptor<List<Command>> {

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
    public boolean accept(CharacterIterator inputChain, List<Command> outputChain) {

        NumberFiniteStateMachine numberFiniteStateMachine = new NumberFiniteStateMachine();
        StringWriter stringBuilder = new StringWriter();
        if (numberFiniteStateMachine.number(inputChain, stringBuilder)
                                    .isPresent()) {
            outputChain.add(new PushOperandCommand(Double.parseDouble(stringBuilder.toString())));
            return true;
        }
        return false;
    }
}
