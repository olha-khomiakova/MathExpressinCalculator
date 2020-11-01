package io.javaclasses.mathcalculator.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;

public class AvailabilityVariableInMemoryCommand implements Command {

    private final Logger logger = LoggerFactory.getLogger(PushBinaryOperatorCommand.class);
    private final StringWriter name;

    public AvailabilityVariableInMemoryCommand(StringWriter name) {
        this.name = name;
    }

    @Override
    public void execute(RuntimeEnvironment environment) {
        if (environment.memory()
                       .containsKey(name.toString())) {
            environment.stack()
                       .pushOperand(environment.memory()
                                               .get(name.toString()));
            if (logger.isInfoEnabled()) {
                logger.info(this.getClass() + " :" + name +
                                    "exists");
            }
        } else {
            throw new CallingANonExistentVariableException("Cannot resolve symbol " + name);
        }

    }
}
