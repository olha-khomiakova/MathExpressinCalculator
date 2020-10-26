package io.javaclasses.mathcalculator.fsm;

import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.math.ShuntingYard;

import java.util.Collections;

/**
 * Implementation of {@link FiniteStateMachine} for parsing
 * and evaluating mathematical expression from string.
 * For example, mathematical expression may be like these:
 * <p>
 * 1) "5.25+(10*2.1)-7.77"
 * 2) "0.1*max(5,10)/2"
 */
@SuppressWarnings({"ClassWithTooManyTransitiveDependencies", "CyclicClassDependency"})
public class ExpressionFiniteStateMachine extends FiniteStateMachine<ShuntingYard> {

    public ExpressionFiniteStateMachine() {

        State<ShuntingYard> calculated = new State<>(true, new CalculatedStateAcceptor());
        State<ShuntingYard> binaryOperation = new State<>(false, new BinaryOperatorStateAcceptor());

        calculated.addTransmission(binaryOperation);
        binaryOperation.addTransmission(calculated);

        registerPossibleStartState(Collections.singletonList(calculated));
    }
}
