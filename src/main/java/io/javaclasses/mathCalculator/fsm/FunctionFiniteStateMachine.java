package io.javaclasses.mathCalculator.fsm;

import io.javaclasses.mathCalculator.fsm.acceptors.LetterCharacterStateAcceptor;
import io.javaclasses.mathCalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathCalculator.fsm.base.State;
import io.javaclasses.mathCalculator.math.FunctionDataStructure;

import java.util.Collections;

/**
 * Implementation of {@link FiniteStateMachine} for parsing and evaluating expression from string.
 * For example, mathematical expression may be like these:
 * <p>
 * 1) "5.25+(10*2.1)-7.77"
 * 2) "0.1*(5+(10/2))"
 */
public class FunctionFiniteStateMachine extends FiniteStateMachine<FunctionDataStructure> {

    public FunctionFiniteStateMachine() {
        State<FunctionDataStructure> letter = new State<>(true, new LetterCharacterStateAcceptor());

        letter.addTransmission(letter);

        registerPossibleStartState(Collections.singletonList(letter));
    }
}
