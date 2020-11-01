package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.FSMFactoryImpl;
import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.FunctionDataStructure;
import io.javaclasses.mathcalculator.runtime.PushExpressionCommand;
import io.javaclasses.mathcalculator.runtime.ShuntingYard;
import io.javaclasses.mathcalculator.runtime.VariableNameAndValuePair;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link StateAcceptor} that starts {@link ExpressionStateAcceptorNameAndValuePair},
 * defines whether the transition from one state to expression state is possible
 * and if possible adds result as a function parameter to the outputChain.
 */
public class ExpressionStateAcceptorNameAndValuePair implements StateAcceptor<VariableNameAndValuePair> {

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
    public boolean accept(CharacterIterator inputChain, VariableNameAndValuePair outputChain) {
        List<Command> commands = new ArrayList<>();
        FSMFactory<List<Command>> factory = new FSMFactoryImpl<>();
        FiniteStateMachine<List<Command>> fsm = factory.create(FSMFactory.TypeFSM.EXPRESSION);
        if (fsm.run(inputChain, commands) == FiniteStateMachine.Status.FINISHED) {
            outputChain.addValue(new PushExpressionCommand(commands));
            return true;
        }
        return false;
    }
}
