package io.javaclasses.mathcalculator.runtime;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class ProcedureFactory {

    private final Map<String, Procedure> functionMap = new HashMap<>();
    private NameAndValuePair pair;

    public ProcedureFactory(NameAndValuePair pair) {
        this.pair = pair;
        int minNumberOfParameters = 0;
        int maxNumberOfParameters = 10;
        functionMap.put("print",
                        ((ProcedureFactory.Creator) () -> new Print(pair.value())).create());
        functionMap.put("delete", ((ProcedureFactory.Creator) () -> new Delete(1,
                                                                               1, pair.value())).create());
//        functionMap.put("cleanScreen", ((ProcedureFactory.Creator) () -> new CleanScreen(minNumberOfParameters,
//                                                                               minNumberOfParameters)).create());
    }

    /**
     * This method checks if the function we need exists and returns an object of the needed type or
     * throws an exception.
     *
     *
     * has information about function name and parameters.
     *
     * @return required function
     */
    public Optional<Procedure> create() {
        if (!functionMap.containsKey(pair.name()
                                         .toString())) {
            throw new IncorrectParametersException(
                    "The entered function is not processed in this calculator");
        }
        this.pair = pair;
        return Optional.of(functionMap.get(pair.name()
                                               .toString()));
    }

    private interface Creator {

        Procedure create();
    }
}
