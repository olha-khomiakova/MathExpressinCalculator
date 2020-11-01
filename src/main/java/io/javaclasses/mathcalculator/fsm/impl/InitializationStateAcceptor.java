package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.FSMFactoryImpl;
import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.PushVariableCommand;
import io.javaclasses.mathcalculator.runtime.VariableNameAndValuePair;

import java.text.CharacterIterator;
import java.util.List;

/**
 * Implementation of {@link StateAcceptor} that defines whether the initialization is possible.
 * And if possible adds a variable and a value to the memory and move an iterator forward in an
 * inputChain.
 */

public class InitializationStateAcceptor implements StateAcceptor<List<Command>> {

    /**
     * This API adds a variable and a value to the memory and moves an iterator forward in an
     * inputChain.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an {@link List<Command>} to which will be added command.
     * @return returns the truth if it was possible to add the variable and the value to memory,
     *         otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, List<Command> outputChain) {
        VariableNameAndValuePair pair = new VariableNameAndValuePair();
        FSMFactory<VariableNameAndValuePair> factory = new FSMFactoryImpl<>();
        FiniteStateMachine<VariableNameAndValuePair> fsm = factory.create(
                FSMFactory.TypeFSM.INITIALIZATION);
        if (fsm.run(inputChain, pair) == FiniteStateMachine.Status.FINISHED) {
            outputChain.add(new PushVariableCommand(pair));
            return true;
        }
        return false;
    }
}
