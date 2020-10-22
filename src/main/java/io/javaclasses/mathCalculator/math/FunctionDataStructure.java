package io.javaclasses.mathCalculator.math;

import io.javaclasses.mathCalculator.IncorrectMathExpressionException;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a data structure that stores a name and parameters of a function
 * and has abstract method that, after implementation, will calculates the function.
 */
public class FunctionDataStructure {
    private final StringBuilder functionNameBuilder = new StringBuilder();
    private final List<Double> functionParameters = new ArrayList<>();

    public void addCharacterToFunctionName(char character) {
        this.functionNameBuilder.append(character);
    }

    public void addFunctionParameter(double parameter) {
        this.functionParameters.add(parameter);
    }

    public StringBuilder FunctionNameBuilder() {
        return this.functionNameBuilder;
    }

    public List<Double> getFunctionParameters() {
        return functionParameters;
    }

    public double calculate() throws IncorrectMathExpressionException {

        return new FunctionFactory().getRequiredFunction(this).get().calculate();
    }

}
