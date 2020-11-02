package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.NameAndValuePair;

import java.io.StringWriter;
import java.text.CharacterIterator;
import java.util.List;

/**
 * Implementation of {@link StateAcceptor} that defines whether the add variable to the memory is
 * possible.
 * And if possible adds variable to the memory and move an iterator forward in an inputChain.
 */

public class NameStateAcceptor implements StateAcceptor<NameAndValuePair> {

    /**
     * This API adds variable to the memory and move an iterator forward in an inputChain.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an {@link List<Command>} to which will be added command.
     * @return returns the truth if it was possible to add current character to the outputChain,
     *         otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, NameAndValuePair outputChain) {
        FiniteStateMachine<StringWriter> fsm =
                new NameBuilderFiniteStateMachine();
        StringWriter stringWriter = new StringWriter();
        if (fsm.run(inputChain, stringWriter) == FiniteStateMachine.Status.FINISHED) {
            outputChain.addVariableName(stringWriter);
            return true;
        }
        return false;
    }
}
