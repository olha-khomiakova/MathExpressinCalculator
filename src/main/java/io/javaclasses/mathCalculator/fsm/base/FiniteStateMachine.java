package io.javaclasses.mathCalculator.fsm.base;

import io.javaclasses.mathCalculator.IncorrectMathExpressionException;

import java.text.CharacterIterator;
import java.util.ArrayList;
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
        FINISHED
    }

    private final Collection<State<T>> possibleStartStateList = new ArrayList<>();

    /**
     * This is API that change from one state to another.
     *
     * @param inputChain  An original expression
     * @param outputChain result of work FSM
     * @return status that indicates at what stage the FSM finished work
     */
    public Status run(CharacterIterator inputChain, T outputChain) throws IncorrectMathExpressionException {
        Optional<State<T>> currentState = Optional.empty();
        while (true) {
            Collection<State<T>> transitions = currentState.isPresent() ?
                    currentState.get().transitions() : possibleStartStateList;
            Optional<State<T>> nextState = stepForward(inputChain, outputChain, transitions);
            if (!nextState.isPresent()) {
                return currentState.filter(State::canBeFinish).map(state -> Status.FINISHED).orElse(Status.NOT_STARTED);
            }
            currentState = nextState;
        }
    }

    protected void registerPossibleStartState(Collection<State<T>> states) {
        this.possibleStartStateList.addAll(states);
    }

    private Optional<State<T>> stepForward(CharacterIterator inputChain, T outputChain,
                                        Iterable<State<T>> transitions) throws IncorrectMathExpressionException {
        for (State<T> state : transitions) {
            if (state.accept(inputChain, outputChain)) {
                return Optional.of(state);
            }
        }
        return Optional.empty();
    }

}
