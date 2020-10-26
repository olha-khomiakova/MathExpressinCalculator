package io.javaclasses.mathcalculator.fsm;

import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.math.ShuntingYard;

import static java.util.Arrays.asList;

/**
 * Implementation of {@link FiniteStateMachine} for parsing
 * and evaluating calculated expression from string.
 * It may be numbers, function, expression in brackets.
 */
@SuppressWarnings({"ClassWithTooManyTransitiveDependencies", "CyclicClassDependency"})
class CalculatedFiniteStateMachine extends FiniteStateMachine<ShuntingYard> {

    CalculatedFiniteStateMachine() {
        State<ShuntingYard> number = new State<>(true, new NumberStateAcceptor());
        State<ShuntingYard> expressionWithBrackets = new State<>(true,
                                                                 new ExpressionWithBracketsStateAcceptor());
        State<ShuntingYard> function = new State<>(true, new FunctionStateAcceptor());

        registerPossibleStartState(asList(number, expressionWithBrackets, function));
    }
}
