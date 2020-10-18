package io.javaclasses.mathCalculator.fsm;

import io.javaclasses.mathCalculator.math.BinaryOperatorFactory;
import io.javaclasses.mathCalculator.math.ShuntingYard;

import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor}.
 * It defines whether the transition from one state to binary operator state is possible.
 * And if possible move an iterator forward in an inputChain.
 */
public class BinaryOperatorStateAcceptor implements StateAcceptor<ShuntingYard> {
    @Override
    public boolean accept(CharacterIterator inputChain, ShuntingYard outputChain) {
        char currentCharacter = inputChain.current();
        if (new BinaryOperatorFactory().isBinaryOperator(currentCharacter)) {
            outputChain.pushBinaryOperator(new BinaryOperatorFactory()
                    .getRequiredBinaryOperator(currentCharacter));
            inputChain.next();
            return true;
        }
        return false;
    }
}
