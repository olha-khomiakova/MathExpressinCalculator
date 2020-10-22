package io.javaclasses.mathCalculator.fsm.acceptors;

import io.javaclasses.mathCalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathCalculator.math.FunctionDataStructure;

import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor}.
 * It service defines whether the transition from one state to letter state is possible,
 * and if possible move an iterator forward in an inputChain.
 */
public class LetterCharacterStateAcceptor implements StateAcceptor<FunctionDataStructure> {
    @Override
    public boolean accept(CharacterIterator inputChain, FunctionDataStructure outputChain) {
        char currentCharacter = inputChain.current();
        if (Character.isLetter(currentCharacter)) {
            outputChain.addCharacterToFunctionName(currentCharacter);
            inputChain.next();
            return true;
        }
        return false;
    }
}
