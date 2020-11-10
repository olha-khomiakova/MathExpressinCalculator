package io.javaclasses.fsm.impl;

import io.javaclasses.fsm.api.Compiler;
import io.javaclasses.fsm.api.CompilerFactory;
import io.javaclasses.fsm.api.CompilerType;
import io.javaclasses.fsm.base.StateAcceptor;
import io.javaclasses.runtime.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.CharacterIterator;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link StateAcceptor} that defines whether the transition is possible.
 * If it is possible adds a variable name and value to the outputChain and move an iterator forward
 * in an
 * inputChain.
 */

public class FSMStateAcceptor implements StateAcceptor<List<Command>> {

    private final CompilerFactory factory;
    private final CompilerType fsmType;
    private final Logger logger = LoggerFactory.getLogger(FSMStateAcceptor.class);

    FSMStateAcceptor(CompilerFactory factory, CompilerType fsmType) {
        this.factory = factory;
        this.fsmType = fsmType;
    }

    /**
     * This API adds a variable and a value to the outputChain and moves an iterator forward in an
     * inputChain.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an {@link List<Command>} to which will be added command with the name and value
     *         variable.
     * @return returns the truth if it was possible to add the variable and the value to memory,
     *         otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, List<Command> outputChain) {
        int indexInputChain = inputChain.getIndex();
        Compiler compiler = factory.create(fsmType);
        Optional<Command> command = compiler.compile(inputChain);
        if (command.isPresent()) {
            outputChain.add(command.get());

            if (logger.isInfoEnabled()) {
                logger.info(this.getClass()
                                .getSimpleName() + " add " + command.get()
                                                                    .getClass()
                                                                    .getSimpleName());
            }
            return true;
        }
        inputChain.setIndex(indexInputChain);
        return false;
    }

    @Override
    public boolean isLexeme() {
        return true;
    }
}
