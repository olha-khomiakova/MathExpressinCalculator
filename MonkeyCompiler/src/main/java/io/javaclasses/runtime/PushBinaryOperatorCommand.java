package io.javaclasses.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is implementation of {@link Function} for push binary operator to the
 * {@link java.util.Deque<ShuntingYard>}.
 */
public class PushBinaryOperatorCommand implements Command {

    private final Logger logger = LoggerFactory.getLogger(PushBinaryOperatorCommand.class);
    private final BinaryOperator binaryOperator;

    public PushBinaryOperatorCommand(BinaryOperator operator) {
        binaryOperator = operator;
    }

    /**
     * This API push binary operator to the ShuntingYard.
     *
     * @param environment
     *         is data structure for storing {@link Memory}, {@link java.util.Deque<ShuntingYard>},
     */
    @Override
    public void execute(RuntimeEnvironment environment) {
        environment.stack()
                   .pushBinaryOperator(binaryOperator);
        if (logger.isInfoEnabled()) {
            logger.info(this.getClass()
                            .getSimpleName() + " :" + binaryOperator.getClass()
                                                                    .getSimpleName());

        }
    }
}
