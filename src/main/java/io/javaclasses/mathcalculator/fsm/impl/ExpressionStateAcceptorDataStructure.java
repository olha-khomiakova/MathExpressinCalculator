package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.api.CompilerElement;
import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.DataStructure;
import io.javaclasses.mathcalculator.runtime.ShuntingYard;

import java.text.CharacterIterator;
import java.util.Optional;

/**
 * Implementation of {@link StateAcceptor} that starts {@link },
 * defines whether the transition from one state to expression state is possible
 * and if possible adds result as a function parameter to the outputChain.
 */
public class ExpressionStateAcceptorDataStructure implements StateAcceptor<DataStructure> {
private final FSMFactory factory;
private final FSMFactory.TypeFSM typeFSM;
    ExpressionStateAcceptorDataStructure(FSMFactory factory, FSMFactory.TypeFSM type) {
        this.factory=factory;
        typeFSM=type;
    }

    /**
     * This API creates {@link ShuntingYard} for {@link ExpressionFiniteStateMachine},
     * starts FSM and adds result of shunting yard as a function parameter to the outputChain.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an {@link DataStructure} to which will be added result of ExpressionFSM.
     * @return the truth if the FSM has worked successfully
     *         and added the result to the outputChain, otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, DataStructure outputChain) {
        CompilerElement compilerElement = factory.create(typeFSM);
        Optional<Command> command=compilerElement.compile(inputChain);
        if (command.isPresent()) {
            outputChain.addFunctionParameter(command.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean isLexeme() {
        return true;
    }
}
