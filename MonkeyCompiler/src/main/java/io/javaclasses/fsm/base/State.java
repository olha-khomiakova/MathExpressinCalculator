package io.javaclasses.fsm.base;

import com.google.common.base.Preconditions;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Part of {@link FiniteStateMachine} that stores information about can be finish it,
 * possible transitions from it to another state and acceptor.
 *
 * @param <T>
 *         is a data structure in which fsm writes the result of its work
 */
public class State<T> {

    public static <T>Builder<T> builder()
    {
        return new Builder<>();
    }

    public static class Builder<T> {

        private boolean canBeFinish;
        private StateAcceptor<T> acceptor;

        public Builder<T> allowFinish() {
            this.canBeFinish = true;
            return this;
        }
        public Builder<T> withAcceptor(StateAcceptor<T> acceptor)
        {
            this.acceptor = acceptor;
            return this;
        }
        public State<T> build()
        {
            Preconditions.checkNotNull(this.acceptor);
            return new State<>(this.canBeFinish, this.acceptor);
        }
    }

    private final boolean canBeFinish;
    private final StateAcceptor<T> acceptor;
    private final Collection<State<T>> transitions = new ArrayList<>();

    public State(boolean canBeFinish, StateAcceptor<T> acceptor) {
        this.canBeFinish = canBeFinish;
        if (acceptor == null) {
            throw new NullPointerException("Acceptor is null");
        }
        this.acceptor = acceptor;
    }

    /**
     * This API returns decision whether the transition to the next state is accepted.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an result of some {@link FiniteStateMachine}
     * @return true if the transition to the next state is accepted, otherwise it returns false
     */
    public boolean accept(CharacterIterator inputChain, T outputChain) {
        return acceptor.accept(inputChain, outputChain);
    }

    public void addTransmission(State<T> state) {
        this.transitions.add(state);
    }

    StateAcceptor<T> acceptor() {
        return acceptor;
    }

    Collection<State<T>> transitions() {
        return Collections.unmodifiableCollection(this.transitions);
    }

    boolean canBeFinish() {
        return canBeFinish;
    }

}
