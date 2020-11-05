package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.runtime.DataStructure;

import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor} that defines whether the transition from one state to
 * next state is possible
 * and returns decision whether the transition to the next state is accepted.
 * And if possible move an iterator forward in an inputChain.
 */
public class RequiredCharacterStateAcceptorFunction implements StateAcceptor<DataStructure> {

    private final char requiredCharacter;

     RequiredCharacterStateAcceptorFunction(char requiredCharacter) {
        this.requiredCharacter = requiredCharacter;
    }

    /**
     * This API moves an iterator forward in an inputChain.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an {@link DataStructure}
     * @return returns the truth if an current character is the same as required,
     *         otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, DataStructure outputChain) {

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
