package io.javaclasses.mathCalculator.fsm;

import io.javaclasses.mathCalculator.fsm.base.StateAcceptor;

import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor} that defines
 * whether the transition from one state to digit state is possible,
 * and if possible adds it to the outputChain and moves an iterator forward in an inputChain.
 */
public class DigitCharacterStateAcceptor implements StateAcceptor<StringBuilder> {
    /**
     * This API adds digit to the outputChain and moves an iterator forward in an inputChain.
     *
     * @param inputChain  is an iterable string with input data
     * @param outputChain is an StringBuilder to which will be appended digit.
     * @return returns the truth if it was possible to add current character to the outputChain,
     * otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, StringBuilder outputChain) {
        char currentCharacter = inputChain.current();
        if (Character.isDigit(currentCharacter)) {
            outputChain.append(currentCharacter);
            inputChain.next();
            return true;
        }
        return false;
    }
}
