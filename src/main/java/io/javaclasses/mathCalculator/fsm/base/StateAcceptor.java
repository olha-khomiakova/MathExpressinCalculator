package io.javaclasses.mathCalculator.fsm.base;


import java.text.CharacterIterator;

/**
 * This service defines whether the transition from one state to another is possible.
 *
 * @param <T> is a data structure in which fsm writes the result of its work
 */
public interface StateAcceptor<T> {
    /**
     * This API returns decision whether the transition to the next state is accepted.
     *
     * @param inputChain  is an iterable string with input data
     * @param outputChain is an result of any {@link FiniteStateMachine}
     * @return is decision whether the transition to the next state is accepted
     */
    boolean accept(CharacterIterator inputChain, T outputChain);
}
