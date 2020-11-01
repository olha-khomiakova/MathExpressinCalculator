package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.FSMFactoryImpl;
import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.FunctionDataStructure;
import io.javaclasses.mathcalculator.runtime.PushFunctionCommand;
import io.javaclasses.mathcalculator.runtime.ShuntingYard;

import java.text.CharacterIterator;
import java.util.List;

/**
 * Implementation of {@link StateAcceptor} that defines
 * whether the transition from one state to function state is possible.
 */
public class FunctionStateAcceptor implements StateAcceptor<List<Command>> {

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
        FunctionDataStructure functionDataStructure = new FunctionDataStructure();
        FSMFactory<FunctionDataStructure> factory = new FSMFactoryImpl<>();
        FiniteStateMachine<FunctionDataStructure> fsm = factory.create(FSMFactory.TypeFSM.FUNCTION);
        if (fsm.run(inputChain, functionDataStructure) == FiniteStateMachine.Status.FINISHED) {
            functionDataStructure.validateFunction();
            outputChain.add(new PushFunctionCommand(functionDataStructure));
            return true;
        }
        inputChain.setIndex(index);
        return false;
    }

}
