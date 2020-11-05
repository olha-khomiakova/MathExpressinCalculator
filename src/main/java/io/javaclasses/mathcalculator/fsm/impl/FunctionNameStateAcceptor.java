package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathcalculator.runtime.DataStructure;

import java.io.StringWriter;
import java.text.CharacterIterator;

public class FunctionNameStateAcceptor implements StateAcceptor<DataStructure> {

    @Override
    public boolean accept(CharacterIterator inputChain, DataStructure outputChain) {

        FiniteStateMachine<StringWriter> fsm =
                new NameBuilderFiniteStateMachine();
        StringWriter stringWriter = new StringWriter();
        if (fsm.run(inputChain, stringWriter) == FiniteStateMachine.Status.FINISHED) {
            outputChain.addFunctionName(stringWriter);
            return true;
        }
        return false;
    }

    @Override
    public boolean isLexeme() {
        return true;
    }
}
