package io.javaclasses.fsm.base;

import org.slf4j.Logger;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * This is general representing of a finite state machine
 * that change from one state to another in response to some inputs.
 * You can read more at {@link "https://en.wikipedia.org/wiki/Finite-state_machine" }
 *
 * @param <T>
 *         is a data structure in which fsm writes the result of its work
 */
public class FiniteStateMachine<T> {

    public enum Status {
        NOT_STARTED,
        FINISHED
    }

    private final Collection<State<T>> possibleStartStateList = new ArrayList<>();
    private final Logger logger = getLogger(FiniteStateMachine.class);

    /**
     * This is API that changes finite state machine states from one to another.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         result of work FSM
     * @return status that indicates at what stage the FSM finished work
     */

    public Status run(CharacterIterator inputChain, T outputChain) {
        if (logger.isInfoEnabled()) {
            logger.info(this.getClass()
                            .getSimpleName() + " started.");
        }
        skipWhiteSpace(inputChain, true);
        Optional<State<T>> currentState = Optional.empty();
        while (true) {
            Collection<State<T>> transitions = currentState.isPresent() ?
                                               currentState.get()
                                                           .transitions() : possibleStartStateList;
            Optional<State<T>> nextState = stepForward(inputChain, outputChain, transitions);
            if (!nextState.isPresent()) {
                if (logger.isInfoEnabled()) {
                    logger.info(this.getClass()
                                    .getSimpleName() + " ended with status "
                                        + currentState.filter(State::canBeFinish)
                                                      .map(state -> Status.FINISHED)
                                                      .orElse(Status.NOT_STARTED));
                }
                return currentState.filter(State::canBeFinish)
                                   .map(state -> Status.FINISHED)
                                   .orElse(Status.NOT_STARTED);
            }
            currentState = nextState;
            skipWhiteSpace(inputChain, currentState.get()
                                                   .acceptor()
                                                   .isLexeme());
        }

    }

    protected final void registerPossibleStartState(Collection<State<T>> states) {
        this.possibleStartStateList.addAll(states);
    }

    private Optional<State<T>> stepForward(CharacterIterator inputChain, T outputChain,
                                           Iterable<State<T>> transitions) {
        for (State<T> state : transitions) {
            if (state.accept(inputChain, outputChain)) {
                return Optional.of(state);
            }
        }
        return Optional.empty();
    }

    private static void skipWhiteSpace(CharacterIterator inputChain, boolean isLexeme) {
        while (Character.isWhitespace(inputChain.current()) && isLexeme) {
            inputChain.next();
        }
    }

}
