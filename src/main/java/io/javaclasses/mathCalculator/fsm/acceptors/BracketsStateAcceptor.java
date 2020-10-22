package io.javaclasses.mathCalculator.fsm.acceptors;

import io.javaclasses.mathCalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathCalculator.math.ShuntingYard;

import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor} that defines whether the transition from one state
 * to brackets state is possible and if it possible moves an iterator forward in an inputChain.
 */
public class BracketsStateAcceptor implements StateAcceptor<ShuntingYard> {
    private final char requiredCharacter;

    public BracketsStateAcceptor(char requiredCharacter) {
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
