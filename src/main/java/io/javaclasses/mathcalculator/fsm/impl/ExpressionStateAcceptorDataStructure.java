package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.api.CompilerElement;
import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.runtime.Command;

import java.text.CharacterIterator;
import java.util.Optional;

/**
 * Implementation of {@link StateAcceptor} that creates {@link CompilerElement}, starts it,
 * defines whether the transition from one state to expression state is possible
 * and if possible adds result as a function parameter to the outputChain.
 */
public class ExpressionStateAcceptorDataStructure implements StateAcceptor<DataStructure> {

    private final FSMFactory factory;

    ExpressionStateAcceptorDataStructure(FSMFactory factory) {
        this.factory = factory;
    }

    /**
     * This API creates {@link CompilerElement},
     * starts it and adds result of compiling as a function parameter to the outputChain.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an {@link DataStructure} to which will be added result of ExpressionFSM.
     * @return true if the FSM has worked successfully
     *         and added the result to the outputChain, otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, DataStructure outputChain) {
        CompilerElement compilerElement = factory.create(FSMFactory.TypeFSM.EXPRESSION);
        Optional<Command> command = compilerElement.compile(inputChain);
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
