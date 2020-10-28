package io.javaclasses.mathcalculator.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class PushShuntingYardCommand implements Command {

    private final Logger logger = LoggerFactory.getLogger(PushBinaryOperatorCommand.class);
    private final List<Command> stack;

    public PushShuntingYardCommand(List<Command> stack) {
        this.stack = stack;
    }

    @Override
    public void execute(RuntimeEnvironment environment) {
        environment.startStack();
        for (Command command : stack) {
            command.execute(environment);
        }
        Optional<Double> result = environment.stack()
                                           .result();
        environment.closeStack();
        if(result.isPresent())
        {
            environment.stack().pushOperand(result.get());
            if (logger.isInfoEnabled()) {
                logger.info(this.getClass() + " :" + result);
            }
        }


    }
}
