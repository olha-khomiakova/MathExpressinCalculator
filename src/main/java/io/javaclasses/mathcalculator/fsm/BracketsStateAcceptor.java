package io.javaclasses.mathcalculator.fsm;

import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.math.ShuntingYard;

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

    /**
     * This API moves an iterator forward in an inputChain.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an {@link ShuntingYard}
     * @return returns the truth if an current character is the same as required,
     *         otherwise it returns false
     */
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
