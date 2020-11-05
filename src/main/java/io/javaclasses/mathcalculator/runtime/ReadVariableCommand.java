package io.javaclasses.mathcalculator.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;

import static io.javaclasses.mathcalculator.runtime.StringValueReader.readString;

public class ReadVariableCommand implements Command {

    private final Logger logger = LoggerFactory.getLogger(PushBinaryOperatorCommand.class);
    private final StringValueHolder name;

    public ReadVariableCommand(StringWriter name) {
        this.name = new StringValueHolder(name.toString());
    }

    @Override
    public void execute(RuntimeEnvironment environment) {
        if (environment.memory()
                       .containsKey(name)) {
            ValueHolder value = environment.memory().get(name);
            environment.stack()
                       .pushOperand(environment.memory()
                                               .get(name));
            if (logger.isInfoEnabled()) {
                logger.info(this.getClass() + " :" + name +
                                    " exists");
            }
        } else {
            throw new CallingANonExistentVariableException("Cannot resolve symbol " + name);
        }

    }
}
