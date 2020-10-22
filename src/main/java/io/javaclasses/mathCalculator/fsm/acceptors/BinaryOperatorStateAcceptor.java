package io.javaclasses.mathCalculator.fsm.acceptors;

import io.javaclasses.mathCalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathCalculator.math.BinaryOperator;
import io.javaclasses.mathCalculator.math.BinaryOperatorFactory;
import io.javaclasses.mathCalculator.math.ShuntingYard;

import java.text.CharacterIterator;
import java.util.Optional;

/**
 * Implementation of {@link StateAcceptor}.
 * It defines whether the transition from one state to binary operator state is possible.
 * And if possible move an iterator forward in an inputChain.
 */
public class BinaryOperatorStateAcceptor implements StateAcceptor<ShuntingYard> {
    @Override
    public boolean accept(CharacterIterator inputChain, ShuntingYard outputChain) {
        char currentCharacter = inputChain.current();
        Optional<BinaryOperator> binaryOperator =
                new BinaryOperatorFactory().getRequiredBinaryOperator(currentCharacter);
        if (binaryOperator.isPresent()) {
            outputChain.pushBinaryOperator(binaryOperator.get());
            inputChain.next();
            return true;
        }
        return false;
    }
}
