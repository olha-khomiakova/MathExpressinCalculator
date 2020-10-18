package io.javaclasses.mathCalculator.fsm;

import java.text.CharacterIterator;
/**
 * Implementation of {@link StateAcceptor}.
 * It service defines whether the transition from one state to digit state is possible.
 * And if possible move an iterator forward in an inputChain.
 */
public class DigitCharacterStateAcceptor implements StateAcceptor<StringBuilder> {
    @Override
    public boolean accept(CharacterIterator inputChain, StringBuilder outputChain) {
        char currentCharacter = inputChain.current();
        if(Character.isDigit(currentCharacter))
        {
            outputChain.append(currentCharacter);
            inputChain.next();
            return true;
        }
        return false;
    }
}
