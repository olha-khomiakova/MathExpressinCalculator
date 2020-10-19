package io.javaclasses.mathCalculator.fsm;

import io.javaclasses.mathCalculator.math.ShuntingYard;

import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor}.
 * It service defines whether the transition from one state
 * to parenthesis state is possible.
 * And if possible move an iterator forward in an inputChain.
 */
public class ParenthesisStateAcceptor implements StateAcceptor<ShuntingYard> {
    private final char requiredCharacter;

    public ParenthesisStateAcceptor(char requiredCharacter) {
        this.requiredCharacter = requiredCharacter;
    }

    @Override
    public boolean accept(CharacterIterator inputChain, ShuntingYard outputChain) {
        char currentCharacter = inputChain.current();
        if (this.requiredCharacter == currentCharacter) {
            inputChain.next();
            return true;
        }
        return false;
    }
}
