package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.api.CompilerElement;
import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.runtime.Command;

import java.text.CharacterIterator;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link StateAcceptor} that defines whether the initialization is possible.
 * And if possible adds a variable and a value to the memory and move an iterator forward in an
 * inputChain.
 */

public class FSMStateAcceptor implements StateAcceptor<List<Command>> {

    private final FSMFactory factory;
    private final FSMFactory.TypeFSM typeFSM;

    FSMStateAcceptor(FSMFactory factory, FSMFactory.TypeFSM typeFSM) {
        this.factory = factory;
        this.typeFSM=typeFSM;
    }

    /**
     * This API adds a variable and a value to the memory and moves an iterator forward in an
     * inputChain.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an {@link List<Command>} to which will be added command.
     * @return returns the truth if it was possible to add the variable and the value to memory,
     *         otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, List<Command> outputChain) {
        int indexInputChain = inputChain.getIndex();
        CompilerElement compilerElement = factory.create(typeFSM);
        Optional<Command> command = compilerElement.compile(inputChain);
        if (command.isPresent()) {
            outputChain.add(command.get());
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
