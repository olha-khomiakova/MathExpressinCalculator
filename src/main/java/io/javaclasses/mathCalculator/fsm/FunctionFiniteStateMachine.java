package io.javaclasses.mathCalculator.fsm;

import io.javaclasses.mathCalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathCalculator.fsm.base.State;
import io.javaclasses.mathCalculator.math.FunctionDataStructure;

import java.util.Collections;

/**
 * Implementation of {@link FiniteStateMachine} for parsing and evaluating function from string.
 * Function may be min(x,y) or max(x,y).
 */
public class FunctionFiniteStateMachine extends FiniteStateMachine<FunctionDataStructure> {

    public FunctionFiniteStateMachine() {
        State<FunctionDataStructure> name = new State<>(false, new FunctionNameStateAcceptor());
        State<FunctionDataStructure> openingBrackets = new State<>(false, new FunctionSingleCharacterStateAcceptor('('));
        State<FunctionDataStructure> expression = new State<>(false, new ExpressionStateForFunctionAcceptor());
        State<FunctionDataStructure> closingBrackets = new State<>(true, new FunctionSingleCharacterStateAcceptor(')'));
        State<FunctionDataStructure> comma = new State<>(false, new FunctionSingleCharacterStateAcceptor(','));

        name.addTransmission(openingBrackets);
        openingBrackets.addTransmission(expression);
        expression.addTransmission(comma);
        comma.addTransmission(expression);
        expression.addTransmission(closingBrackets);


        registerPossibleStartState(Collections.singletonList(name));
    }
}
