package io.javaclasses.fsm.impl;

import io.javaclasses.fsm.api.Compiler;
import io.javaclasses.fsm.api.CompilerFactory;
import io.javaclasses.fsm.api.CompilerType;
import io.javaclasses.fsm.base.StateAcceptor;
import io.javaclasses.runtime.Command;

import java.io.StringWriter;
import java.text.CharacterIterator;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link StateAcceptor} that creates {@link Compiler}, starts it and
 * defines whether the transition from one state to number state is possible
 * and if it is possible adds result to the outputChain.
 */
public class NumberStateAcceptor implements StateAcceptor<List<Command>> {

    private final CompilerFactory factory;

    NumberStateAcceptor(CompilerFactory factory) {
        this.factory = factory;
    }

    /**
     * This API creates {@link StringWriter} for {@link NumberFiniteStateMachine},
     * starts {@link Compiler} and adds result of shunting yard to the outputChain.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an {@link List<Command>} to which will be added result of NumberFSM.
     * @return true if the FSM has worked successfully
     *         and added the result to the outputChain, otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, List<Command> outputChain) {

        Compiler compiler = factory.create(CompilerType.NUMBER);
        Optional<Command> command = compiler.compile(inputChain);
        if (command.isPresent()) {
            outputChain.add(command.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean isLexeme() {
        return true;
    }
}
