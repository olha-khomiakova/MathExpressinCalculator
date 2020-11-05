package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.runtime.DataStructure;

import java.io.StringWriter;
import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor} that defines
 * whether the transition from one state to letter state is possible,
 * and if possible adds it to the outputChain and moves an iterator forward in an inputChain.
 */
public class LetterCharacterStateAcceptor implements StateAcceptor<StringWriter> {

    /**
     * This API adds letter to the outputChain and moves an iterator forward in an inputChain.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an {@link DataStructure} to which will be added letter.
     * @return returns the truth if it was possible to add current character to the outputChain,
     *         otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, StringWriter outputChain) {

        char currentCharacter = inputChain.current();
        if (Character.isLetter(currentCharacter)) {
            outputChain.append(currentCharacter);
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
