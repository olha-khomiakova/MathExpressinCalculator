package io.javaclasses.fsm.api;

import io.javaclasses.runtime.Command;

import java.text.CharacterIterator;
import java.util.Optional;

/**
 * This is part of abstract factory pattern that is a common interface for compiler element.
 * It compiles program that stored in input character iterator.
 */
public interface CompilerElement {

    /**
     * This API compiles program and returns {@link Optional<Command>}
     * where the compiled program is stored.
     *
     * @param input
     *         is an iterable string with input data
     * @return is a {@link Optional<Command>} where the compiled program is stored
     */

    Optional<Command> compile(CharacterIterator input);
}
