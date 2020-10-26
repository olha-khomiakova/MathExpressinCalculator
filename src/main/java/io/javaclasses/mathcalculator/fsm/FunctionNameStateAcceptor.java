package io.javaclasses.mathcalculator.fsm;

import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.math.FunctionDataStructure;

import java.text.CharacterIterator;

public class FunctionNameStateAcceptor implements StateAcceptor<FunctionDataStructure> {

    @Override
    public boolean accept(CharacterIterator inputChain, FunctionDataStructure outputChain) {

        FiniteStateMachine<FunctionDataStructure> functionNameFSM =
                new FunctionNameFiniteStateMachine();
        return functionNameFSM.run(inputChain, outputChain)
                == FiniteStateMachine.Status.FINISHED;
    }
}
