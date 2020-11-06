package io.javaclasses.mathcalculator.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * This is implementation of {@link Function} for push expression to the
 * {@link java.util.Deque<ShuntingYard>}.
 */
public class PushExpressionCommand implements Command {

    private final Logger logger = LoggerFactory.getLogger(PushBinaryOperatorCommand.class);
    private final List<Command> commandList;

    public PushExpressionCommand(List<Command> list) {
        this.commandList = new ArrayList<>(list);
    }
    /**
     * This API push expression to the ShuntingYard.
     *
     * @param environment
     *         is data structure for storing {@link Memory}, {@link java.util.Deque<ShuntingYard>},
     */
    @Override
    public void execute(RuntimeEnvironment environment) {
        environment.startStack();
        for (Command command : commandList) {
            command.execute(environment);
        }
        Optional<ValueHolder> result = environment.stack()
                                                  .result();
        environment.closeStack();
        if (result.isPresent()) {
            environment.stack()
                       .pushOperand(result.get());
            if (logger.isInfoEnabled()) {
                logger.info(this.getClass().getSimpleName() + " :" + result);
            }
        }

    }
}
