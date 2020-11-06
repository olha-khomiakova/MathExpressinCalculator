package io.javaclasses.mathcalculator.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This is implementation of {@link Function} for push variable to the
 * {@link java.util.Deque<ShuntingYard>}.
 */
public class PushVariableCommand implements Command {

    private final Logger logger = LoggerFactory.getLogger(PushBinaryOperatorCommand.class);
    private final StringValueHolder nameVariable;
    private final Collection<Command> valueVariable = new ArrayList<>();

    public PushVariableCommand(String variable, Collection<Command> value) {
        nameVariable = new StringValueHolder(variable);
        valueVariable.addAll(value);
    }

    /**
     * This API push binary variable to the ShuntingYard.
     *
     * @param environment
     *         is data structure for storing {@link Memory}, {@link java.util.Deque<ShuntingYard>},
     */
    @Override
    public void execute(RuntimeEnvironment environment) {
        environment.startStack();
        for (Command command : valueVariable) {
            command.execute(environment);
        }
        ValueHolder result = null;
        if (environment.stack()
                       .result()
                       .isPresent()) {
            result = environment.stack()
                                .result()
                                .get();
        }
        if (environment.memory()
                       .containsKey(nameVariable)) {
            environment.memory()
                       .replace(nameVariable.toString(), result);
        } else {
            environment.memory()
                       .put(nameVariable, result);
        }
        if (logger.isInfoEnabled()) {
            logger.info(this.getClass()
                            .getSimpleName() + " :" + nameVariable +
                                '=' + result);
        }

    }
}
