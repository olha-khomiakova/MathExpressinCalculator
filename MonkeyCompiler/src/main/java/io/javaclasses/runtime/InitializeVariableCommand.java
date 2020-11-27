package io.javaclasses.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This is implementation of {@link Function} for push variable to the
 * {@link java.util.Deque<ShuntingYard>}.
 */
public class InitializeVariableCommand implements Command {

    private final Logger logger = LoggerFactory.getLogger(PushBinaryOperatorCommand.class);
    private final StringValueHolder nameVariable;
    private final Collection<Command> evaluateValueCommands = new ArrayList<>();

    public InitializeVariableCommand(String variable, Collection<Command> evaluateValueCommands) {
        nameVariable = new StringValueHolder(variable);
        this.evaluateValueCommands.addAll(evaluateValueCommands);
    }

    /**
     * This API push binary variable to the ShuntingYard.
     *
     * @param environment
     *         is data structure for storing {@link io.javaclasses.runtime.Memory}, {@link
     *         java.util.Deque<ShuntingYard>},
     */
    @Override
    public void execute(RuntimeEnvironment environment) {

        environment.startStack();

        for (Command command : evaluateValueCommands) {
            command.execute(environment);
        }

        ValueHolder result = environment.stack()
                                        .result();

        environment.closeStack();

        environment.memory()
                   .put(nameVariable, result);

        if (logger.isInfoEnabled()) {
            logger.info(this.getClass()
                            .getSimpleName() + " :" + nameVariable +
                                '=' + result);
        }

    }
}
