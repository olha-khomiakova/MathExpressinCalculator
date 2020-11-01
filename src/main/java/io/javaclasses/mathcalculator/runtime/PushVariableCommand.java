package io.javaclasses.mathcalculator.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PushVariableCommand implements Command {

    private final Logger logger = LoggerFactory.getLogger(PushBinaryOperatorCommand.class);
    private final VariableNameAndValuePair pair;

    public PushVariableCommand(VariableNameAndValuePair pair) {
        this.pair = pair;
    }

    @Override
    public void execute(RuntimeEnvironment environment) {
        environment.startStack();
        for (Command command : this.pair.getValue()) {
            command.execute(environment);
        }
        double result = environment.stack()
                                   .result()
                                   .get();
        environment.closeStack();
        environment.memory()
                   .put(this.pair.getName()
                                 .toString(), result);
        if (logger.isInfoEnabled()) {
            logger.info(this.getClass() + " :" + pair.getName() +
                                "=" + result);
        }

    }
}
