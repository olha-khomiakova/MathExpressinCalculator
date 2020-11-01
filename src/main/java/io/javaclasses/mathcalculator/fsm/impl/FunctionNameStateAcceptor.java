package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.runtime.FunctionDataStructure;

import java.io.StringWriter;
import java.text.CharacterIterator;

public class FunctionNameStateAcceptor implements StateAcceptor<FunctionDataStructure> {

    @Override
    public boolean accept(CharacterIterator inputChain, FunctionDataStructure outputChain) {

        FiniteStateMachine<StringWriter> fsm =
                new NameBuilderFiniteStateMachine();
        StringWriter stringWriter = new StringWriter();
        if (fsm.run(inputChain, stringWriter) == FiniteStateMachine.Status.FINISHED) {
            outputChain.addFunctionName(stringWriter);
            return true;
        }
        return false;
    }
}
