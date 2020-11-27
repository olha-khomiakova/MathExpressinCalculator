package io.javaclasses.fsm.impl;

import io.javaclasses.fsm.base.FiniteStateMachine;
import io.javaclasses.fsm.base.State;

import java.io.StringWriter;
import java.util.Collections;

/**
 * Implementation of {@link FiniteStateMachine} for parsing name from string.
 * Name may be "min", "max", "print".
 */
class NameBuilderFiniteStateMachine extends FiniteStateMachine<StringWriter> {

    NameBuilderFiniteStateMachine() {
        State<StringWriter> letter = new State<>(true, new LetterCharacterStateAcceptor());

        letter.addTransmission(letter);

        registerPossibleStartState(Collections.singletonList(letter));
    }

}
