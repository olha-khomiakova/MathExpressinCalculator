package io.javaclasses.fsm.impl;

import io.javaclasses.fsm.base.FiniteStateMachine;
import io.javaclasses.fsm.base.StateAcceptor;

import java.io.StringWriter;
import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor} that defines
 * whether the transition from one state to letter state is possible,
 * and if it is possible adds it to the outputChain and moves an iterator forward in an inputChain.
 */
public class ReservedNameStateAcceptor implements StateAcceptor<DataStructureForLoop> {

    public final String reservedName;

    public ReservedNameStateAcceptor(String reservedName) {
        this.reservedName = reservedName;
    }

    /**
     * This API adds letter to the outputChain and moves an iterator forward in an inputChain.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an {@link StringWriter} to which will be added letter.
     * @return returns the truth if it was possible to add current character to the outputChain,
     *         otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, DataStructureForLoop outputChain) {
        StringWriter stringWriter = new StringWriter();
        NameBuilderFiniteStateMachine fsm = new NameBuilderFiniteStateMachine();
        return fsm.run(inputChain, stringWriter) == FiniteStateMachine.Status.FINISHED &&
                stringWriter.toString()
                            .equals(reservedName);
    }

    @Override
    public boolean isLexeme() {
        return false;
    }
}
