package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.ShuntingYard;

import java.text.CharacterIterator;
import java.util.List;

/**
 * Implementation of {@link StateAcceptor} that defines whether the transition from one state
 * to brackets state is possible and if it possible moves an iterator forward in an inputChain.
 */
public class RequiredCharacterStateAcceptorListCommands implements StateAcceptor<List<Command>> {

    private final char requiredCharacter;

    public RequiredCharacterStateAcceptorListCommands(char requiredCharacter) {
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
    public boolean accept(CharacterIterator inputChain, List<Command> outputChain) {
        char currentCharacter = inputChain.current();
        if (this.requiredCharacter == currentCharacter) {
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
