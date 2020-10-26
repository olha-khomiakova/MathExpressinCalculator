package io.javaclasses.mathcalculator.math;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a data structure that stores a name and parameters of a function
 * and can calculates the function stored in it.
 */
@SuppressWarnings({"ClassWithTooManyDependents", "CyclicClassDependency"})
public class FunctionDataStructure {

    private final StringWriter functionNameBuilder = new StringWriter();
    private final List<Double> functionParameters = new ArrayList<>();

    /**
     * This API calculates the function that stored in this data structure.
     *
     * @return result of calculation of function
     */
    public double calculate() {
        return new FunctionFactory().getRequiredFunction(this)
                                    .get()
                                    .calculate();
    }

    public void addCharacterToFunctionName(char character) {
        this.functionNameBuilder.append(character);
    }

    public void addFunctionParameter(double parameter) {
        this.functionParameters.add(parameter);
    }

    List<Double> getFunctionParameters() {
        return Collections.unmodifiableList(functionParameters);
    }

    public StringWriter functionNameBuilder() {
        return functionNameBuilder;
    }
}
