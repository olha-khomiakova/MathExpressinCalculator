package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.api.CompilerElement;
import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.FunctionDataStructure;
import io.javaclasses.mathcalculator.runtime.ShuntingYard;

import java.text.CharacterIterator;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link StateAcceptor} that defines
 * whether the transition from one state to function state is possible.
 */
public class ProcedureStateAcceptor implements StateAcceptor<List<Command>> {

    private final FSMFactory factory;

    ProcedureStateAcceptor(FSMFactory factory) {
        this.factory = factory;
    }

    /**
     * This API creates {@link FunctionFiniteStateMachine}
     * and a function data structure {@link FunctionDataStructure}.
     * This structure is filled with data during the operation of FSM,
     * and then calculates the value of the function.
     * <p>
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an {@link ShuntingYard} to which will be added result of {@link
     *         FunctionDataStructure}.
     * @return the truth if the FSMs have worked successfully and added the result to the
     *         outputChain,
     *         otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, List<Command> outputChain) {
        int index = inputChain.getIndex();
        CompilerElement compilerElement = factory.create(FSMFactory.TypeFSM.PROCEDURE);
        Optional<Command> command = compilerElement.compile(inputChain);
        if (command.isPresent()) {
            outputChain.add(command.get());
            return true;
        }
        inputChain.setIndex(index);
        return false;
    }

}
