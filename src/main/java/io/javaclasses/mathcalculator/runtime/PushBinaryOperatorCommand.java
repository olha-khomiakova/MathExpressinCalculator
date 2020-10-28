package io.javaclasses.mathcalculator.runtime;

import io.javaclasses.mathcalculator.math.BinaryOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PushBinaryOperatorCommand implements Command {

    private final Logger logger = LoggerFactory.getLogger(PushBinaryOperatorCommand.class);
    private final BinaryOperator binaryOperator;

    public PushBinaryOperatorCommand(BinaryOperator operator) {
        binaryOperator = operator;
    }

    @Override
    public void execute(RuntimeEnvironment environment) {
        environment.stack()
                   .pushBinaryOperator(binaryOperator);
        if (logger.isInfoEnabled()) {
            logger.info(this.getClass() + " :" + binaryOperator);

        }
    }
}
