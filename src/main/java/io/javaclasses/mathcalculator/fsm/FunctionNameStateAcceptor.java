package io.javaclasses.mathcalculator.fsm;

import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.runtime.FunctionDataStructure;

import java.text.CharacterIterator;

public class FunctionNameStateAcceptor implements StateAcceptor<FunctionDataStructure> {

    @Override
    public boolean accept(CharacterIterator inputChain, FunctionDataStructure outputChain) {

        FunctionNameFiniteStateMachine functionNameFSM =
                new FunctionNameFiniteStateMachine();
        return functionNameFSM.functionName(inputChain, outputChain);

    }
}
