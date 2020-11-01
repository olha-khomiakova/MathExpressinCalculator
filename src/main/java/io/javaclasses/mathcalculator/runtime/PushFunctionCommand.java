package io.javaclasses.mathcalculator.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PushFunctionCommand implements Command {

    private final Logger logger = LoggerFactory.getLogger(PushBinaryOperatorCommand.class);
    private final FunctionDataStructure dataStructure;

    public PushFunctionCommand(FunctionDataStructure dataStructure) {
        this.dataStructure = dataStructure;
    }

    @Override
    public void execute(RuntimeEnvironment environment) {
        List<Double> parameters = new ArrayList<>();
        for (Command command : dataStructure.functionParameters()) {
            command.execute(environment);
            Optional<Double> result = environment.stack()
                                                 .result();
            if (result.isPresent()) {

            }
            parameters.add(result.get());
        }
        environment.stack()
                   .pushOperand(dataStructure.calculate(parameters));
        if (logger.isInfoEnabled()) {
            logger.info(this.getClass() + " :" + dataStructure.functionNameBuilder() +
                                "()");
        }

    }
}
