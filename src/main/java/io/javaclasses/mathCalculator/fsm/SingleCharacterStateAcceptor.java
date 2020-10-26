package io.javaclasses.mathCalculator.fsm;

import io.javaclasses.mathCalculator.fsm.base.StateAcceptor;

import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor} that defines whether the transition from one state to next state is possible.
 * And if possible move an iterator forward in an inputChain.
 */
public class SingleCharacterStateAcceptor implements StateAcceptor<StringBuilder> {
    private final char requiredCharacter;

    public SingleCharacterStateAcceptor(char requiredCharacter) {
        this.requiredCharacter = requiredCharacter;
    }

    /**
     * This API moves an iterator forward in an inputChain
     * and returns decision whether the transition to the next state is accepted.
     * And if possible move an iterator forward in an inputChain.
     *
     * @param inputChain  is an iterable string with input data
     * @param outputChain is an {@link StringBuilder}
     * @return returns the truth if an current character is the same as required,
     * otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, StringBuilder outputChain) {
        char currentCharacter = inputChain.current();
        if (this.requiredCharacter == currentCharacter) {
            outputChain.append(currentCharacter);
            inputChain.next();
            return true;
        }
        return false;
    }
}
