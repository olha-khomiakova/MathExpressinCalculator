package io.javaclasses.mathcalculator.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;

/**
 * This is implementation of {@link Function} read variable value and write it to the
 * {@link java.util.Deque< ShuntingYard >}.
 */
public class ReadVariableCommand implements Command {

    private final Logger logger = LoggerFactory.getLogger(PushBinaryOperatorCommand.class);
    private final StringValueHolder name;

    public ReadVariableCommand(StringWriter name) {
        this.name = new StringValueHolder(name.toString());
    }

    /**
     * This API read variable value and write it to the ShuntingYard.
     *
     * @param environment
     *         is data structure for storing {@link Memory}, {@link java.util.Deque<ShuntingYard>},
     */
    @Override
    public void execute(RuntimeEnvironment environment) {
        if (environment.memory()
                       .containsKey(name)) {
            environment.stack()
                       .pushOperand(environment.memory()
                                               .get(name));
            if (logger.isInfoEnabled()) {
                logger.info(this.getClass()
                                .getSimpleName() + " :" + name +
                                    " exists");
            }
        } else {
            throw new CallingANonExistentVariableException("Cannot resolve symbol " + name);
        }

    }
}
