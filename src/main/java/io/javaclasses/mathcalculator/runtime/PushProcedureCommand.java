package io.javaclasses.mathcalculator.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class PushProcedureCommand implements Command {

    private final Logger logger = LoggerFactory.getLogger(PushBinaryOperatorCommand.class);
    private final NameAndValuePair pair;

    public PushProcedureCommand(NameAndValuePair pair) {
        this.pair = pair;
    }

    @Override
    public void execute(RuntimeEnvironment environment) {
        ProcedureFactory factory = new ProcedureFactory(pair);
        Optional<Procedure> procedure = factory.create();
        if(procedure.isPresent()) {
            for (Command command : pair.value()) {
                environment.startStack();
                command.execute(environment);
                environment.closeStack();
            }
            procedure.get().execute(environment);
            if (logger.isInfoEnabled()) {
                logger.info(this.getClass() + " :" + pair.name() +
                                    "()");
            }
        }
    }
}
