package io.javaclasses.mathcalculator.fsm;

import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.math.BinaryOperator;
import io.javaclasses.mathcalculator.math.BinaryOperatorFactory;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.PushBinaryOperatorCommand;
import io.javaclasses.mathcalculator.runtime.ShuntingYard;

import java.text.CharacterIterator;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link StateAcceptor} that defines
 * whether the transition from one state to binary operator state is possible.
 * And if possible adds it to the outputChain and moves an iterator forward in an inputChain.
 */
public class BinaryOperatorStateAcceptor implements StateAcceptor<List<Command>> {

    /**
     * This API creates binary operator, adds it to the {@link ShuntingYard}
     * and moves an iterator forward in an inputChain.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an {@link ShuntingYard} to which will be added binary operator.
     * @return returns the truth if it was possible to create a binary operator
     *         and add it to the outputChain, otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, List<Command> outputChain) {
        char currentCharacter = inputChain.current();
        Optional<BinaryOperator> binaryOperator =
                new BinaryOperatorFactory().getRequiredBinaryOperator(currentCharacter);
        if (binaryOperator.isPresent()) {
            outputChain.add(new PushBinaryOperatorCommand(binaryOperator.get()));

            inputChain.next();
            return true;
        }
        return false;
    }
}
