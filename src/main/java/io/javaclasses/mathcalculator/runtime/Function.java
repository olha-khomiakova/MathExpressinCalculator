package io.javaclasses.mathcalculator.runtime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.javaclasses.mathcalculator.runtime.DoubleValueReader.readDouble;

/**
 * This is general representing of function that stores information about it
 * and have abstract method calculate() that should be implemented by a descendant of this class.
 */
public abstract class Function implements Command {

    private final int minimumNumberOfParameters;
    private final int maximumNumberOfParameters;
    private final String functionName;
    private final List<Command> functionParameters = new ArrayList<>();
    protected Function(int minimumNumber, int maximumNumber, String functionName, List<Command> parameters) {
        this.functionParameters.addAll( parameters);
        this.minimumNumberOfParameters = minimumNumber;
        this.maximumNumberOfParameters = maximumNumber;
        this.functionName = functionName;
    }


    public abstract double apply(List<Double> parameters);
    //public abstract void apply(RuntimeEnvironment environment, List<Double> parameters);

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

    public List<Double> parameters(RuntimeEnvironment environment) {
        List<Double> parameters = new ArrayList<>();
        for (Command command : functionParameters) {
            environment.startStack();
            command.execute(environment);
            Optional<Double> result = Optional.of(readDouble(environment.stack()
                                                 .result().get()));
            parameters.add(result.get());
            environment.closeStack();
        }
//        environment.stack()
//                   .pushOperand(dataStructure.calculate(parameters));
        return parameters;
    }
}
