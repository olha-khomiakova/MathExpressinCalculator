package io.javaclasses.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;

import static io.javaclasses.runtime.BooleanValueReader.readBoolean;

/**
 * This is implementation of {@link Function} for push operand to the
 * {@link java.util.Deque<ShuntingYard>}.
 */
public class PushLoopCommand implements Command {

    private final Logger logger = LoggerFactory.getLogger(PushLoopCommand.class);
    private final Command condition;
    private final Collection<Command> statements = new ArrayList<>();

    public PushLoopCommand(Command condition, Collection<Command> statements) {
        this.condition = condition;
        this.statements.addAll(statements);
    }

    /**
     * This API push binary operand to the ShuntingYard.
     *
     * @param environment
     *         is data structure for storing {@link Memory}, {@link java.util.Deque<ShuntingYard>},
     */
    @Override
    public void execute(RuntimeEnvironment environment) {
        environment.startStack();
        condition.execute(environment);
        boolean booleanCondition = readBoolean(environment.stack()
                                                          .result());
        environment.closeStack();
        while (booleanCondition) {

            for (Command command : statements) {
                command.execute(environment);
            }

            environment.startStack();
            condition.execute(environment);
            booleanCondition = readBoolean(environment.stack()
                                                      .result());
            environment.closeStack();

        }

        if (logger.isInfoEnabled()) {
            logger.info(this.getClass()
                            .getSimpleName() + " end");

        }
    }
}
