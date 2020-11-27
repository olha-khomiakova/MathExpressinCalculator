package io.javaclasses.fsm.api;

import io.javaclasses.runtime.Command;

import java.text.CharacterIterator;
import java.util.Optional;

/**
 * This is a common interface for compiler that compiles a program that is represented as a
 * character iterator.
 *
 * It takes the program, parses it some way and returns a {@link Optional<Command>}
 * with compiled program.
 */
public interface Compiler {

    /**
     * This API takes the program, parses it some way and returns a {@link Optional<Command>}
     * with compiled program.
     *
     * @param input
     *         is an iterable string with program
     * @return compiled program that is represented as a {@link Command}
     */

    Optional<Command> compile(CharacterIterator input);
}
