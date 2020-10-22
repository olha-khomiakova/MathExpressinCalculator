package io.javaclasses.mathCalculator.fsm.acceptors;

import io.javaclasses.mathCalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathCalculator.math.FunctionDataStructure;

import java.text.CharacterIterator;

public class CommaAcceptor implements StateAcceptor<FunctionDataStructure> {
    @Override
    public boolean accept(CharacterIterator inputChain, FunctionDataStructure outputChain) {
        char currentCharacter = inputChain.current();
        if (',' == currentCharacter) {
            inputChain.next();
            return true;
        }
        return false;
    }
}
