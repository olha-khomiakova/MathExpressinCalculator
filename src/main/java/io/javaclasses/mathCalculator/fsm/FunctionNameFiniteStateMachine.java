package io.javaclasses.mathCalculator.fsm;

import io.javaclasses.mathCalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathCalculator.fsm.base.State;
import io.javaclasses.mathCalculator.math.FunctionDataStructure;

import java.util.Collections;

/**
 * Implementation of {@link FiniteStateMachine} for parsing function name from string.
 * Name can be min , max:
 */
public class FunctionNameFiniteStateMachine extends FiniteStateMachine<FunctionDataStructure> {

    public FunctionNameFiniteStateMachine() {
        State<FunctionDataStructure> letter = new State<>(true, new LetterCharacterStateAcceptor());

        letter.addTransmission(letter);

        registerPossibleStartState(Collections.singletonList(letter));
    }
}
