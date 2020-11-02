package io.javaclasses.mathcalculator.runtime;

abstract class Procedure implements Command{
    private int minimumNumberOfParameters=-1;
    private int maximumNumberOfParameters=100;
    private final String functionName;

    Procedure(int minimumNumber, int maximumNumber, String functionName) {
        this.minimumNumberOfParameters = minimumNumber;
        this.maximumNumberOfParameters = maximumNumber;
        this.functionName = functionName;
    }
    Procedure( String functionName) {
        this.functionName = functionName;
    }
    public void accept(int numberOfParameters) {
        if (numberOfParameters < maximumNumberOfParameters ||
                numberOfParameters > minimumNumberOfParameters) {
            throw new IncorrectParametersException("Wrong number of procedure parameters! " +
                                                             functionName + " procedure can have " +
                                                             minimumNumberOfParameters + " to " +
                                                             maximumNumberOfParameters +
                                                             " parameters. ");
        }
    }

}
