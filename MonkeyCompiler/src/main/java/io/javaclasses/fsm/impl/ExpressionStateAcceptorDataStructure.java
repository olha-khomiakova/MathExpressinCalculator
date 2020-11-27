package io.javaclasses.fsm.impl;

import io.javaclasses.fsm.api.Compiler;
import io.javaclasses.fsm.api.CompilerFactory;
import io.javaclasses.fsm.api.CompilerType;
import io.javaclasses.fsm.base.StateAcceptor;
import io.javaclasses.runtime.Command;

import java.text.CharacterIterator;
import java.util.Optional;

/**
 * Implementation of {@link StateAcceptor} that creates {@link Compiler}, starts it,
 * defines whether the transition from one state to expression state is possible
 * and if possible adds result as a function parameter to the outputChain.
 */
public class ExpressionStateAcceptorDataStructure implements StateAcceptor<StringAndCommandsDataStructure> {

    private final CompilerFactory factory;
    private final CompilerType type;

    ExpressionStateAcceptorDataStructure(CompilerFactory factory,
                                         CompilerType type) {
        this.factory = factory;
        this.type = type;
    }

    /**
     * This API creates {@link Compiler},
     * starts it and adds result of compiling as a function parameter to the outputChain.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an {@link StringAndCommandsDataStructure} to which will be added result of
     *         ExpressionFSM.
     * @return true if the FSM has worked successfully
     *         and added the result to the outputChain, otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain,
                          StringAndCommandsDataStructure outputChain) {
        Compiler compiler = factory.create(type);
        Optional<Command> command = compiler.compile(inputChain);
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
