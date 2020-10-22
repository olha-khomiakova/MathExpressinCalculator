package io.javaclasses.mathCalculator.fsm;

import io.javaclasses.mathCalculator.fsm.acceptors.ClosingParametersBrackets;
import io.javaclasses.mathCalculator.fsm.acceptors.ExpressionStateForFunctionAcceptor;
import io.javaclasses.mathCalculator.fsm.acceptors.OpeningParametersBrackets;
import io.javaclasses.mathCalculator.fsm.acceptors.CommaAcceptor;
import io.javaclasses.mathCalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathCalculator.fsm.base.State;
import io.javaclasses.mathCalculator.math.FunctionDataStructure;

import java.util.Collections;

/**
 * Implementation of {@link FiniteStateMachine} for parsing and evaluating expression from string.
 * For example, mathematical expression may be like these:
 * <p>
 * 1) "5.25+(10*2.1)-7.77"
 * 2) "0.1*(5+(10/2))"
 */
public class FunctionParametersFiniteStateMachine extends FiniteStateMachine<FunctionDataStructure> {

    public FunctionParametersFiniteStateMachine() {

        State<FunctionDataStructure> openingBrackets = new State<>(false, new OpeningParametersBrackets());
        State<FunctionDataStructure> expression = new State<>(false, new ExpressionStateForFunctionAcceptor());
        State<FunctionDataStructure> closingBrackets = new State<> (true, new ClosingParametersBrackets());
        State<FunctionDataStructure> comma = new State<>(false, new CommaAcceptor());

        openingBrackets.addTransmission(expression);
        expression.addTransmission(comma);
        comma.addTransmission(expression);
        expression.addTransmission(closingBrackets);


        registerPossibleStartState(Collections.singletonList(openingBrackets));
    }
}
