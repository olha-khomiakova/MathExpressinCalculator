package io.javaclasses.mathcalculator.runtime;


import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a data structure that stores a name and parameters of a function
 * and can calculates the function stored in it.
 */
public class DataStructure {

    private StringWriter name;
    private final List<Command> parameters = new ArrayList<>();
    //private Function function;

    /**
     * This API calculates the function that stored in this data structure.
     *
     * @return result of calculation of function
     */


    public void addFunctionName(StringWriter character) {
        this.name = character;
    }

//    public void validateFunction() {
//        FunctionFactory functionFactory = new FunctionFactory(name.toString(), functionParameters);
//        function = functionFactory.create().get();
//        function.accept(functionParameters.size());
//    }

    public void addFunctionParameter(Command parameter) {
        this.parameters.add(parameter);
    }

    public List<Command> parameters() {
        return Collections.unmodifiableList(parameters);
    }
    public String name() {
        return name.toString();
    }

//    public Function function(){
//        return function;
//    }

}
