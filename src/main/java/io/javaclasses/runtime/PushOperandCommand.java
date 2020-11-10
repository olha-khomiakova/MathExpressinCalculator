package io.javaclasses.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is implementation of {@link Command} for pushing operand to the {@link ShuntingYard}.
 *
 * Usually {@link PushExpressionCommand} uses this command to fill the {@link ShuntingYard} with
 * data.
 */
public class PushOperandCommand implements Command {

    private final Logger logger = LoggerFactory.getLogger(PushOperandCommand.class);
    private final ValueHolder operand;

    public PushOperandCommand(ValueHolder operand) {
        this.operand = operand;
    }

    /**
     * This API push operand to the {@link ShuntingYard}.
     *
     * @param environment
     *         is data structure for storing {@link io.javaclasses.runtime.Memory},
     *         {@link java.util.Deque<ShuntingYard>},
     *         {@link java.io.ByteArrayOutputStream} and processing them
     */
    @Override
    public void execute(RuntimeEnvironment environment) {
        environment.stack()
                   .pushOperand(operand);
        if (logger.isInfoEnabled()) {
            logger.info(this.getClass()
                            .getSimpleName() + " :" + operand);

        }
    }
}
