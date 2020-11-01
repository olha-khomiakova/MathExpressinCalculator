package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.FSMFactoryImpl;
import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.ShuntingYard;

import java.text.CharacterIterator;
import java.util.List;

/**
 * Implementation of {@link StateAcceptor} that starts {@link ExpressionStateAcceptorListCommands},
 * defines whether the transition from one state to calculated state is possible
 * and if possible adds result to the outputChain.
 */
public class CalculatedStateAcceptor implements StateAcceptor<List<Command>> {

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
    public boolean accept(CharacterIterator inputChain, List<Command> outputChain) {
        FSMFactory<List<Command>> factory = new FSMFactoryImpl<>();
        FiniteStateMachine<List<Command>> fsm = factory.create(FSMFactory.TypeFSM.CALCULATED);
        return fsm.run(inputChain, outputChain) == FiniteStateMachine.Status.FINISHED;
    }
}
