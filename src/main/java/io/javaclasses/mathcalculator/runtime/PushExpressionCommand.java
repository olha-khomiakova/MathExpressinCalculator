package io.javaclasses.mathcalculator.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PushExpressionCommand implements Command {

    private final Logger logger = LoggerFactory.getLogger(PushBinaryOperatorCommand.class);
    private final List<Command> commandList;

    public PushExpressionCommand(List<Command> list) {
        this.commandList = new ArrayList<>(list);
    }

    @Override
    public void execute(RuntimeEnvironment environment) {
        environment.startStack();
        for (Command command : commandList) {
            command.execute(environment);
        }
        Optional<Double> result = environment.stack()
                                             .result();
        environment.closeStack();
        if (result.isPresent()) {
            environment.stack()
                       .pushOperand(result.get());
            if (logger.isInfoEnabled()) {
                logger.info(this.getClass() + " :" + result);
            }
        }

    }
}
