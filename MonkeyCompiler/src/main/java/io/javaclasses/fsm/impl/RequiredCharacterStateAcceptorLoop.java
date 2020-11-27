package io.javaclasses.fsm.impl;

import io.javaclasses.fsm.base.StateAcceptor;
import io.javaclasses.runtime.Command;

import java.text.CharacterIterator;
import java.util.List;

/**
 * Implementation of {@link StateAcceptor} that defines whether the transition from one state
 * to the next state is possible and if it is possible moves an iterator forward in an inputChain.
 */
public class RequiredCharacterStateAcceptorLoop implements StateAcceptor<DataStructureForLoop> {

    private final char requiredCharacter;

    public RequiredCharacterStateAcceptorLoop(char requiredCharacter) {
        this.requiredCharacter = requiredCharacter;
    }

    /**
     * This API moves an iterator forward in an inputChain.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an {@link List<Command>}
     * @return true if an current character is the same as required,
     *         otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, DataStructureForLoop outputChain) {
        char currentCharacter = inputChain.current();
        if (this.requiredCharacter == currentCharacter) {
            inputChain.next();
            return true;
        }
        return false;
    }

    @Override
    public boolean isLexeme() {
        return false;
    }
}
