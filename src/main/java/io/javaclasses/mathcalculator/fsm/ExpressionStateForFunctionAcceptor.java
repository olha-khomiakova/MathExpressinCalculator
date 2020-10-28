package io.javaclasses.mathcalculator.fsm;

import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.runtime.FunctionDataStructure;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.ShuntingYard;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link StateAcceptor} that starts {@link ExpressionStateAcceptor},
 * defines whether the transition from one state to expression state is possible
 * and if possible adds result as a function parameter to the outputChain.
 */
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
        ExpressionFiniteStateMachine expressionFSM = new ExpressionFiniteStateMachine();
        List<Command> commands = new ArrayList<>();
        Optional<Command> command = expressionFSM.expression(inputChain, commands);
        if (command.isPresent()) {
            outputChain.addFunctionParameter(command.get());
            return true;
        }
        return false;
    }
}
