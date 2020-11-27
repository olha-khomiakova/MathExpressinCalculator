package io.javaclasses.runtime;

/**
 * This is a general representing of a command that is executed at runtime
 * and performs some actions with data.
 *
 * Data may be passed through the constructor, stack or taken from runtime memory,
 * depending on the command that implements this interface.
 *
 * If a command needs to get some data that needs to be put on the stack,
 * it must start a new stack, use it, and then close it.
 */
public interface Command {

    /**
     * This is API that carry out some actions at runtime.
     * It may read, write or modify data kept in {@link RuntimeEnvironment}.
     *
     * If a command needs to get some data that needs to be put on the stack,
     * it must start a new stack, use it, and then close it.
     *
     * @param environment
     *         is data structure for storing {@link io.javaclasses.runtime.Memory},
     *         {@link java.util.Deque<ShuntingYard>},
     *         {@link java.io.ByteArrayOutputStream} and processing them
     */
    void execute(RuntimeEnvironment environment);
}
