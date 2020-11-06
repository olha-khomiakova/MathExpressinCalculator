package io.javaclasses.mathcalculator.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * This is implementation of {@link Function} for output.
 */
public class Print extends Function {

    private final Logger logger = LoggerFactory.getLogger(Print.class);

    Print(Collection<Command> commands) {
        super(1, 100, "print()", commands);
    }

    /**
     * This API add data to {@link java.io.ByteArrayOutputStream}.
     *
     * @param environment
     *         is data structure for storing {@link Memory}, {@link java.util.Deque<ShuntingYard>},
     */
    @Override
    public void execute(RuntimeEnvironment environment) {
        List<ValueHolder> parameters = parameters(environment);
        try {
            environment.output()
                       .write(parameters.toString()
                                        .getBytes());
        } catch (IOException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
        }
        if (logger.isInfoEnabled()) {
            logger.info(this.getClass() + " :" + this.getClass()
                                                     .getName()
                                                     .toLowerCase() + "()");
        }
    }

}
