package io.javaclasses.mathCalculator.fsm;

import io.javaclasses.mathCalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathCalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathCalculator.math.FunctionDataStructure;

import java.text.CharacterIterator;

public class FunctionNameStateAcceptor implements StateAcceptor<FunctionDataStructure> {
    @Override
    public boolean accept(CharacterIterator inputChain, FunctionDataStructure outputChain) {
        FiniteStateMachine<FunctionDataStructure> functionNameFiniteStateMachine =
                new FunctionNameFiniteStateMachine();
        return functionNameFiniteStateMachine.run(inputChain, outputChain)
                == FiniteStateMachine.Status.FINISHED;
    }
}
