package io.javaclasses.mathcalculator.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Delete extends Procedure {

    private final Logger logger = LoggerFactory.getLogger(Delete.class);
    List<Command> name;


    public Delete(int minimumNumber, int maximumNumber, List<Command> variableName) {
        super(minimumNumber,maximumNumber,Print.class.getName().toLowerCase());
        name=variableName;
    }

    @Override
    public void execute(RuntimeEnvironment environment) {
        name.get(0).execute(environment);
        environment.memory().pollLast();
        if (logger.isInfoEnabled()) {
            logger.info(this.getClass() + " : delete" +
                                name);
        }
    }
}
