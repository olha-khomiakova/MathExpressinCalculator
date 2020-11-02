package io.javaclasses.mathcalculator.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Print extends Procedure {

    private final Logger logger = LoggerFactory.getLogger(Print.class);

    private List<Command> parameters;

    Print(List<Command> parameters) {
        super(Print.class.getName().toLowerCase());
        this.parameters=parameters;
    }

    @Override
    public void execute(RuntimeEnvironment environment) {
        Collection<Double> list = new ArrayList<>();
        for (Command command : parameters){
            environment.startStack();
            command.execute(environment);
            Optional<Double> result = environment.stack()
                                                 .result();
            if (result.isPresent()) {
                list.add(result.get());
                environment.closeStack();
            }
        }
        try {
            environment.output().write(list.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        environment.output().println();

        if (logger.isInfoEnabled()) {
            logger.info(this.getClass() + " :" + this.getClass().getName().toLowerCase() +
                                "()");
        }
    }
}
