package io.javaclasses.mathcalculator.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class Print extends Function {

    private final Logger logger = LoggerFactory.getLogger(Print.class);

    Print(int min, int max, List<Command> commands) {
        super(min, max, "print()", commands);
    }

    @Override
    public void execute(RuntimeEnvironment environment) {
        List<ValueHolder> parameters = parameters(environment);
        try {
            environment.output()
                       .write(parameters.toString()
                                                .getBytes());
        } catch (IOException e) {
            if(logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
        }
        if(logger.isInfoEnabled()) {
            logger.info(this.getClass() + " :" + this.getClass()
                                                     .getName()
                                                     .toLowerCase() + "()");
        }
    }
    @Override
    public double apply(List<Double> parameters) {
        return 0;
    }
}
