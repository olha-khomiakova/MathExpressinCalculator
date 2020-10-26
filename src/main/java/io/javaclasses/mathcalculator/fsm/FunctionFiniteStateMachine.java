package io.javaclasses.mathcalculator.fsm;

import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.math.FunctionDataStructure;

import java.util.Collections;

/**
 * Implementation of {@link FiniteStateMachine} for parsing and evaluating function from string.
 * Function may be min(x,y) or max(x,y).
 */
@SuppressWarnings({"ClassWithTooManyTransitiveDependencies", "CyclicClassDependency"})
class FunctionFiniteStateMachine extends FiniteStateMachine<FunctionDataStructure> {

    FunctionFiniteStateMachine() {
        State<FunctionDataStructure> name = new State<>(false, new FunctionNameStateAcceptor());
        State<FunctionDataStructure> openingBrackets = new State<>(false,
                                                                   new FunctionSingleCharacterStateAcceptor(
                                                                           '('));
        State<FunctionDataStructure> expression = new State<>(false,
                                                              new ExpressionStateForFunctionAcceptor());
        State<FunctionDataStructure> closingBrackets = new State<>(true,
                                                                   new FunctionSingleCharacterStateAcceptor(
                                                                           ')'));
        State<FunctionDataStructure> comma = new State<>(false,
                                                         new FunctionSingleCharacterStateAcceptor(
                                                                 ','));

        name.addTransmission(openingBrackets);
        openingBrackets.addTransmission(expression);
        expression.addTransmission(comma);
        comma.addTransmission(expression);
        expression.addTransmission(closingBrackets);

        registerPossibleStartState(Collections.singletonList(name));
    }
}
