package io.javaclasses.mathcalculator.fsm;

import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.runtime.FunctionDataStructure;

import java.text.CharacterIterator;
import java.util.Collections;

/**
 * Implementation of {@link FiniteStateMachine} for parsing function name from string.
 * Name may be "min", "max".
 */
class FunctionNameFiniteStateMachine extends FiniteStateMachine<FunctionDataStructure> {

    FunctionNameFiniteStateMachine() {
        State<FunctionDataStructure> letter = new State<>(true, new LetterCharacterStateAcceptor());

        letter.addTransmission(letter);

        registerPossibleStartState(Collections.singletonList(letter));
    }

    public boolean functionName(CharacterIterator inputChain, FunctionDataStructure function) {
        Status status = run(inputChain, function);
        return status == Status.FINISHED;
    }
}
