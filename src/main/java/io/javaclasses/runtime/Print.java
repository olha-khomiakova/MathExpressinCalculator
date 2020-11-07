package io.javaclasses.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
     * @param environment is data structure for storing {@link Memory}, {@link java.util.Deque<ShuntingYard>},
     */
    @Override
    public void execute(RuntimeEnvironment environment) {
        List<ValueHolder> parameters = parameters(environment);
        for(int i=0;i<parameters.size();i++)
        {
            environment.output().append(parameters.get(i).toString());
            if(i<parameters.size()-1)
            {
                environment.output().append(", ");
            }
        }

        if (logger.isInfoEnabled()) {
            logger.info(this.getClass() + " :" + this.getClass()
                    .getName()
                    .toLowerCase() + "()");
        }
    }

}
