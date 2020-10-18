package io.javaclasses.mathCalculator.fsm;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

/**
 * This is general representing of a finite state machine.
 * The FSM can change from one state to another in response to some inputs.
 * You can read more at {@link "https://en.wikipedia.org/wiki/Finite-state_machine" }
 *
 * @param <T> output data type
 */
public class FiniteStateMachine<T> {
    public enum Status {
        NOT_STARTED,
        DEADLOCK,
        FINISHED
    }

    private final Collection<State> possibleStartStateList = new ArrayList<>();

    /**
     * This is API that change from one state to another.
     *
     * @param inputChain  An original expression
     * @param outputChain result of work FSM
     * @return status that indicates at what stage the FSM finished work
     */
    public Status run(CharacterIterator inputChain, T outputChain) {
        Optional<State> currentState = Optional.empty();
        while (true) {
            Collection<State> transitions = defineTransitions(currentState);
            Optional<State> nextState = stepForward(inputChain, outputChain, transitions);
            if (!nextState.isPresent()) {
                return defineStatus(currentState);
            }
            currentState = nextState;
        }
    }

    protected void registerPossibleStartState(State... states) {
        this.possibleStartStateList.addAll(Arrays.asList(states));
    }

    private Status defineStatus(Optional<State> currentState) {
        return currentState.map(state -> state.canBeFinish() ? Status.FINISHED
                : Status.DEADLOCK).orElse(Status.NOT_STARTED);
    }

    private Optional<State> stepForward(CharacterIterator inputChain, T outputChain,
                                        Iterable<State> transitions) {
        for (State state : transitions) {
            if (state.accept(inputChain, outputChain)) {
                return Optional.of(state);
            }
        }
        return Optional.empty();
    }

    private Collection<State> defineTransitions(Optional<State> currentState) {
        return currentState.isPresent() ? currentState.get().transitions() : possibleStartStateList;
    }
}
