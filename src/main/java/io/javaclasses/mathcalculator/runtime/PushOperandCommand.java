package io.javaclasses.mathcalculator.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PushOperandCommand implements Command {

    private final Logger logger = LoggerFactory.getLogger(PushOperandCommand.class);
    private final Double operand;

    public PushOperandCommand(Double operand) {
        this.operand = operand;
    }

    @Override
    public void execute(RuntimeEnvironment environment) {
        environment.stack()
                   .pushOperand(operand);
        if (logger.isInfoEnabled()) {
            logger.info(this.getClass() + " :" + operand);

        }
    }
}
