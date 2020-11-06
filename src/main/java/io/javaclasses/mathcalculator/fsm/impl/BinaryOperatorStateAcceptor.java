package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.runtime.BinaryOperator;
import io.javaclasses.mathcalculator.runtime.BinaryOperatorsFactory;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.PushBinaryOperatorCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
     * This API creates binary operator, adds it to the command list
     * and moves an iterator forward in an inputChain.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an command list to which will be added command with operator.
     * @return true if it was possible to create a binary operator
     *         and add it to the outputChain, otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, List<Command> outputChain) {
        final Logger logger = LoggerFactory.getLogger(BinaryOperatorStateAcceptor.class);

        int index = inputChain.getIndex();
        String binaryOperatorCharacters = String.valueOf(inputChain.current());
        inputChain.next();
        char currentCharacter = inputChain.current();
        if (currentCharacter == '=') {
            binaryOperatorCharacters += currentCharacter;
            inputChain.next();
        }
        Optional<BinaryOperator> binaryOperator =
                new BinaryOperatorsFactory().getBinaryOperator(binaryOperatorCharacters);
        if (binaryOperator.isPresent()) {
            outputChain.add(new PushBinaryOperatorCommand(binaryOperator.get()));
            if (logger.isInfoEnabled()) {
                logger.info(this.getClass()
                                .getSimpleName() + " add " + binaryOperatorCharacters);
            }
            return true;
        }
        inputChain.setIndex(index);
        return false;
    }

    @Override
    public boolean isLexeme() {
        return true;
    }
}
