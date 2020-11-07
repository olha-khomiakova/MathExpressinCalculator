package io.javaclasses.runtime;

/**
 * This is part of command pattern that is a common interface for all specific commands.
 */
public interface Command {

    /**
     * This is API for calling commands.
     *
     * @param environment
     *         is data structure for storing {@link io.javaclasses.runtime.Memory}, {@link java.util.Deque<ShuntingYard>},
     *         {@link java.io.ByteArrayOutputStream} and processing them
     */
    void execute(RuntimeEnvironment environment);
}
