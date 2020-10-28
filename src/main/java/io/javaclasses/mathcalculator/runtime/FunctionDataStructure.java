package io.javaclasses.mathcalculator.runtime;

import io.javaclasses.mathcalculator.math.Function;
import io.javaclasses.mathcalculator.math.FunctionFactory;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * This is a data structure that stores a name and parameters of a function
 * and can calculates the function stored in it.
 */
public class FunctionDataStructure {

    private final StringWriter functionNameBuilder = new StringWriter();
    private final List<Command> functionParameters = new ArrayList<>();
    private Function function;

    /**
     * This API calculates the function that stored in this data structure.
     *
     * @return result of calculation of function
     */

    public double calculate(List<Double> parameters) {
        return function.calculate(parameters);
    }
    public void addCharacterToFunctionName(char character) {
        this.functionNameBuilder.append(character);
    }

    public void validateFunction() {
        FunctionFactory factory = new FunctionFactory();
        Optional<Function> func = factory.getRequiredFunction(functionNameBuilder.toString());
        func.ifPresent(value -> function = value);
        function.accept(functionParameters.size());
    }

    public void addFunctionParameter(Command parameter) {
        this.functionParameters.add(parameter);
    }

    public List<Command> functionParameters() {
        return Collections.unmodifiableList(functionParameters);
    }



    public StringWriter functionNameBuilder() {
        return functionNameBuilder;
    }
}
