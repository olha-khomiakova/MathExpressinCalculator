package io.javaclasses.runtime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This is implementation of {@link Command} and general representing of function that stores
 * information about it and have abstract method apply() that should be implemented
 * by a descendant of this class.
 */
public abstract class Function implements Command {

    private final int minimumNumberOfParameters;
    private final int maximumNumberOfParameters;
    private final String functionName;
    private final Collection<Command> functionParameters = new ArrayList<>();

    Function(int minimumNumber, int maximumNumber, String functionName,
             Collection<Command> parameters) {

        this.functionParameters.addAll(parameters);
        this.minimumNumberOfParameters = minimumNumber;
        this.maximumNumberOfParameters = maximumNumber;
        this.functionName = functionName;
    }

    /**
     * This is API that defines the parameters in numerical form.
     *
     * @param environment is data structure for storing {@link Memory}, {@link java.util.Deque<ShuntingYard>},
     *                    {@link java.io.ByteArrayOutputStream} and processing them
     * @return list of function or procedure parameters
     */
    List<ValueHolder> parameters(RuntimeEnvironment environment) {

        List<ValueHolder> parameters = new ArrayList<>();

        for (Command command : functionParameters) {

            environment.startStack();
            command.execute(environment);

            ValueHolder result = environment.stack().result();

            parameters.add(result);
            environment.closeStack();
        }
        return parameters;
    }

    public void accept(int numberOfParameters) {
        if (numberOfParameters > maximumNumberOfParameters ||
                numberOfParameters < minimumNumberOfParameters) {
            throw new IncorrectFunctionException("Wrong number of function parameters! " +
                    functionName + " function can have " +
                    minimumNumberOfParameters + " to " +
                    maximumNumberOfParameters +
                    " parameters.");
        }
    }

    public int size() {
        return this.functionParameters.size();
    }
}
