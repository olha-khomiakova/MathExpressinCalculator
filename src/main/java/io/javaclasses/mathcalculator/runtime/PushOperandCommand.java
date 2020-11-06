package io.javaclasses.mathcalculator.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * This is implementation of {@link Function} for push operand to the
 * {@link java.util.Deque<ShuntingYard>}.
 */
public class PushOperandCommand implements Command {

    private final Logger logger = LoggerFactory.getLogger(PushOperandCommand.class);
    private final ValueHolder operand;

    public PushOperandCommand(ValueHolder operand) {
        this.operand = operand;
    }
    /**
     * This API push binary operand to the ShuntingYard.
     *
     * @param environment
     *         is data structure for storing {@link Memory}, {@link java.util.Deque<ShuntingYard>},
     */
    @Override
    public void execute(RuntimeEnvironment environment) {
        environment.stack()
                   .pushOperand(operand);
        if (logger.isInfoEnabled()) {
            logger.info(this.getClass().getSimpleName() + " :" + operand);

        }
    }
}
