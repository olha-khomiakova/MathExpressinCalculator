package io.javaclasses.mathcalculator.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

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
     * @param environment is data structure for storing {@link Memory}, {@link java.util.Deque<ShuntingYard>},
     */
    @Override
    public void execute(RuntimeEnvironment environment) {

        parameters(environment).forEach(environment.output()::println);

        if (logger.isInfoEnabled()) {
            logger.info(this.getClass() + " :" + this.getClass()
                    .getName()
                    .toLowerCase() + "()");
        }
    }

}
