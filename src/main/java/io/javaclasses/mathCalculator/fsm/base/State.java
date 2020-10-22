package io.javaclasses.mathCalculator.fsm.base;

import io.javaclasses.mathCalculator.IncorrectMathExpressionException;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Part of {@link FiniteStateMachine}.
 * State stores information about itself and possible transitions from it to another state.
 *
 * @param <T> output data type
 */
public class State <T>{
    private final boolean canBeFinish;
    private final StateAcceptor<T> acceptor;
    private final Collection<State<T>> transitions = new ArrayList<>();
    public State(boolean canBeFinish, StateAcceptor<T> acceptor)
    {
        this.canBeFinish=canBeFinish;
        if(acceptor == null)
        {
            throw new NullPointerException("Acceptor is null");
        }
        this.acceptor = acceptor;
    }
    public boolean accept(CharacterIterator inputChain, T outputChain) throws IncorrectMathExpressionException {
        return acceptor.accept(inputChain, outputChain);
    }
    public void addTransmission(State<T> state)
    {
        this.transitions.add(state);
    }
    public boolean canBeFinish() {
        return canBeFinish;
    }
    public Collection<State<T>> transitions()
    {
        return Collections.unmodifiableCollection(this.transitions);
    }
}
