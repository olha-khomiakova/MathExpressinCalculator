package io.javaclasses.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This is implementation of {@link Function} for push expression to the
 * {@link java.util.Deque<ShuntingYard>}.
 */
public class PushListCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(PushBinaryOperatorCommand.class);

    private final List<Command> commandList;

    public PushListCommand(List<Command> list) {
        this.commandList = new ArrayList<>(list);
    }

    /**
     * This API push expression to the ShuntingYard.
     *
     * @param environment is data structure for storing {@link Memory}, {@link java.util.Deque<ShuntingYard>},
     */
    @Override
    public void execute(RuntimeEnvironment environment) {


        for (Command command : commandList) {
            command.execute(environment);
        }

        if (logger.isInfoEnabled()) {

            logger.info(this.getClass().getSimpleName());
        }

    }
}
