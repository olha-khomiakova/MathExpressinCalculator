package io.javaclasses.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.javaclasses.runtime.BooleanValueReader.readBoolean;

/**
 * This is implementation of {@link Command} negation unary operator for to reverse the boolean
 * value.
 */
public class PushNegationUnaryOperatorCommand implements Command {

    private final Logger logger = LoggerFactory.getLogger(PushNegationUnaryOperatorCommand.class);
    private final Command command;

    public PushNegationUnaryOperatorCommand(Command command) {
        this.command = command;
    }

    /**
     * This API push binary operator to the ShuntingYard.
     *
     * @param environment
     *         is data structure for storing {@link Memory}, {@link java.util.Deque<ShuntingYard>},
     */
    @Override
    public void execute(RuntimeEnvironment environment) {
        environment.startStack();
        command.execute(environment);
        boolean result = !readBoolean(environment.stack()
                                                 .result());
        environment.closeStack();
        environment.stack()
                   .pushOperand(new BooleanValueHolder(result));

        if (logger.isInfoEnabled()) {
            logger.info(this.getClass()
                            .getSimpleName() + " :" + this.getClass()
                                                          .getSimpleName());

        }
    }
}
