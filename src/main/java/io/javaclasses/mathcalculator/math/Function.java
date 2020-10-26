package io.javaclasses.mathcalculator.math;

/**
 * This is general representing of function that stores information about it
 * and have abstract method calculate() that should be implemented by a descendant of this class.
 */
@SuppressWarnings({"ClassWithTooManyTransitiveDependencies", "CyclicClassDependency"})
public abstract class Function {

    private FunctionDataStructure functionDataStructure = new FunctionDataStructure();
    private final int minimumNumberOfParameters;
    private final int maximumNumberOfParameters;
    private final String functionName;

    Function(int minimumNumber, int maximumNumber, String functionName) {
        this.minimumNumberOfParameters = minimumNumber;
        this.maximumNumberOfParameters = maximumNumber;
        this.functionName = functionName;
    }

    public abstract double calculate();

    void accept() {
        if (!(this.maximumNumberOfParameters >= functionDataStructure.getFunctionParameters()
                                                                     .size()) ||
                !(this.minimumNumberOfParameters <= functionDataStructure.getFunctionParameters()
                                                                         .size())) {
            throw new IncorrectMathFunctionException("Wrong number of function parameters! " +
                                                             functionName + " function can have " +
                                                             minimumNumberOfParameters + " to " +
                                                             maximumNumberOfParameters +
                                                             " parameters.");
        }
    }

    public void setFunctionDataStructure(FunctionDataStructure dataStructure) {
        this.functionDataStructure = dataStructure;
    }

    FunctionDataStructure functionDataStructure() {
        return this.functionDataStructure;
    }
}
