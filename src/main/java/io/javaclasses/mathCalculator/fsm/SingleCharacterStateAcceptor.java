package io.javaclasses.mathCalculator.fsm;

import java.text.CharacterIterator;
/**
 * Implementation of {@link StateAcceptor}.
 * It service defines whether the transition from one state
 * to certain single character state is possible.
 * And if possible move an iterator forward in an inputChain.
 */
public class SingleCharacterStateAcceptor implements StateAcceptor<StringBuilder> {
    private final char requiredCharacter;

    public SingleCharacterStateAcceptor(char requiredCharacter) {
        this.requiredCharacter = requiredCharacter;
    }

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
