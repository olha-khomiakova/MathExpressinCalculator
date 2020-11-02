package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.api.CompilerElement;
import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.ShuntingYard;

import java.text.CharacterIterator;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link StateAcceptor} that starts {@link ExpressionWithBracketsFiniteStateMachine},
 * defines whether the transition from one state to expression with brackets state is possible
 * and if possible adds result to the outputChain.
 */
public class ExpressionWithBracketsStateAcceptor implements StateAcceptor<List<Command>> {

    private final FSMFactory factory;

    ExpressionWithBracketsStateAcceptor(FSMFactory factory) {
        this.factory = factory;
    }

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

        CompilerElement compilerElement = factory.create(
                FSMFactory.TypeFSM.EXPRESSION_WITH_BRACKETS);
        Optional<Command> command = compilerElement.compile(inputChain);
        if (command.isPresent()) {
            outputChain.add(command.get());
            return true;
        }
        return false;
    }
}
