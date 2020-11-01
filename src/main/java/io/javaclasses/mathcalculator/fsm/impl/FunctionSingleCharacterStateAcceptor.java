package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.runtime.FunctionDataStructure;

import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor} that defines whether the transition from one state to
 * next state is possible
 * and returns decision whether the transition to the next state is accepted.
 * And if possible move an iterator forward in an inputChain.
 */
public class FunctionSingleCharacterStateAcceptor implements StateAcceptor<FunctionDataStructure> {

    private final char requiredCharacter;

    public FunctionSingleCharacterStateAcceptor(char requiredCharacter) {
        this.requiredCharacter = requiredCharacter;
    }

    /**
     * This API moves an iterator forward in an inputChain.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an {@link FunctionDataStructure}
     * @return returns the truth if an current character is the same as required,
     *         otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, FunctionDataStructure outputChain) {

        char currentCharacter = inputChain.current();
        if (this.requiredCharacter == currentCharacter) {
            inputChain.next();

            return true;
        }
        return false;
    }
}
