package io.javaclasses.mathcalculator.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PushVariableCommand implements Command {

    private final Logger logger = LoggerFactory.getLogger(PushBinaryOperatorCommand.class);
    private final NameAndValuePair pair;

    public PushVariableCommand(NameAndValuePair pair) {
        this.pair = pair;
    }

    @Override
    public void execute(RuntimeEnvironment environment) {
        environment.startStack();
        for (Command command : this.pair.value()) {
            command.execute(environment);
        }
        double result = 0;
        if (environment.stack().result().isPresent()) {
            result = environment.stack().result().get();
        }
        environment.closeStack();
        if(environment.memory().containsKey(pair.name().toString())){
           environment.memory().remove(pair.name()
                        .toString());
        }
        else{
            environment.memory()
                       .put(pair.name()
                                .toString(), result);
        }
        if (logger.isInfoEnabled()) {
            logger.info(this.getClass() + " :" + pair.name() +
                                '=' + result);
        }

    }
}
