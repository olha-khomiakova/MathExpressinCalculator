package io.javaclasses.mathcalculator.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Delete extends Function {

    private final Logger logger = LoggerFactory.getLogger(Delete.class);
    private final List<Command> name = new ArrayList<>();

    public Delete(int minimumNumber, int maximumNumber, List<Command> variableName) {
        super(minimumNumber, maximumNumber, "delete()", variableName);
        name.addAll(variableName);

    }

    @Override
    public void execute(RuntimeEnvironment environment) {
        //environment.startStack();
        for (Command command : name) {
            command.execute(environment);
        }
        Optional<ValueHolder> result = environment.stack()
                                                  .result();
        environment.memory()
                   .remove(result.get());
        if (logger.isInfoEnabled()) {
            logger.info(this.getClass() + " : delete" +
                                name);
        }
    }

    @Override
    public double apply(List<Double> parameters) {
        return 0;
    }

//    @Override
//    public void apply(RuntimeEnvironment environment, List<Double> parameters) {
//        return;
//    }
}
