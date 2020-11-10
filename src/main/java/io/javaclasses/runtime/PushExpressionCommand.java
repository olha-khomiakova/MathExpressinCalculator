package io.javaclasses.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This is implementation of {@link Command} to run a list of commands that will fill
 * the stack with data and get the result of the expression from the {@link ShuntingYard}.
 */
public class PushExpressionCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(PushBinaryOperatorCommand.class);

    private final List<Command> commandList;

    public PushExpressionCommand(List<Command> list) {
        this.commandList = new ArrayList<>(list);
    }

    /**
     * This API opens a new stack, executes a list of input commands that fill the {@link
     * ShuntingYard} with data, gets the result of the expression compiled in the command list,
     * closes the stack and puts the result of the expression into the original stack.
     *
     * @param environment
     *         is data structure for storing {@link io.javaclasses.runtime.Memory},
     *         {@link java.util.Deque<ShuntingYard>},
     *         {@link java.io.ByteArrayOutputStream} and processing them
     */
    @Override
    public void execute(RuntimeEnvironment environment) {

        environment.startStack();

        for (Command command : commandList) {
            command.execute(environment);
        }
        ValueHolder result = environment.stack()
                                        .result();

        environment.closeStack();

        environment.stack()
                   .pushOperand(result);

        if (logger.isInfoEnabled()) {

            logger.info(this.getClass()
                            .getSimpleName() + " : " + result);
        }

    }
}
