package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.FSMFactoryImpl;
import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.PushExpressionCommand;
import io.javaclasses.mathcalculator.runtime.ShuntingYard;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link StateAcceptor} that starts {@link ExpressionWithBracketsFiniteStateMachine},
 * defines whether the transition from one state to expression with brackets state is possible
 * and if possible adds result to the outputChain.
 */
public class ExpressionWithBracketsStateAcceptor implements StateAcceptor<List<Command>> {

    /**
     * This API creates {@link ShuntingYard} for {@link ExpressionWithBracketsFiniteStateMachine},
     * starts FSM and adds result of shunting yard to the outputChain.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an {@link ShuntingYard} to which will be added result of
     *         ExpressionWithBracketsFSM.
     * @return the truth if the FSM has worked successfully
     *         and added the result to the outputChain, otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, List<Command> outputChain) {

        FSMFactory<List<Command>> factory = new FSMFactoryImpl<>();
        FiniteStateMachine<List<Command>> fsm = factory.create(
                FSMFactory.TypeFSM.EXPRESSION_WITH_BRACKETS);

        List<Command> commands = new ArrayList<>();
        FiniteStateMachine.Status status = fsm.run(inputChain, commands);
        if (status == FiniteStateMachine.Status.FINISHED) {
            outputChain.add(new PushExpressionCommand(commands));
            return true;
        }
        return false;
    }
}
