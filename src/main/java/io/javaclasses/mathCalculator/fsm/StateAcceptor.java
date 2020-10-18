package io.javaclasses.mathCalculator.fsm;

import java.text.CharacterIterator;

/**
 * This service defines whether the transition from one state to another is possible.
 *
 * @param <T> output data type
 */
public interface StateAcceptor<T> {
     boolean accept(CharacterIterator inputChain, T outputChain);
}
