package io.javaclasses.mathcalculator.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PushVariableCommand implements Command {

    private final Logger logger = LoggerFactory.getLogger(PushBinaryOperatorCommand.class);
    private final StringValueHolder nameVariable;
    private final List<Command> valueVariable = new ArrayList<>();

    public PushVariableCommand(String variable, List<Command> value) {
        nameVariable = new StringValueHolder(variable);
        valueVariable.addAll( value);
    }

    @Override
    public void execute(RuntimeEnvironment environment) {
        environment.startStack();
        for (Command command : valueVariable) {
            command.execute(environment);
        }
        ValueHolder result = null;
        if (environment.stack().result().isPresent()) {
            result = environment.stack().result().get();
        }
        if(environment.memory().containsKey(nameVariable)){
           environment.memory().replace(nameVariable.toString(),nameVariable);
        }
        else{
            environment.memory()
                       .put(nameVariable, result);
        }
        if (logger.isInfoEnabled()) {
            logger.info(this.getClass().getSimpleName() + " :" + nameVariable +
                                '=' + result);
        }

    }
}
