package io.javaclasses.mathcalculator.fsm;

import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.math.ShuntingYard;

import java.util.Collections;

/**
 * Implementation of {@link FiniteStateMachine} for parsing
 * and evaluating mathematical expression with brackets from string.
 * The expression looks like this pattern: "("+"{@link ExpressionFiniteStateMachine}"+")".
 * For example:
 * 1) (5+2);
 * 2) (min(3.5,2)*0.1).
 */
@SuppressWarnings({"ClassWithTooManyTransitiveDependencies", "CyclicClassDependency"})
class ExpressionWithBracketsFiniteStateMachine extends FiniteStateMachine<ShuntingYard> {

    ExpressionWithBracketsFiniteStateMachine() {
        State<ShuntingYard> openingBrackets = new State<>(false,
                                                          new BracketsStateAcceptor('('));
        State<ShuntingYard> closingParenthesis = new State<>(true,
                                                             new BracketsStateAcceptor(')'));
        State<ShuntingYard> expression = new State<>(false, new ExpressionStateAcceptor());

        expression.addTransmission(closingParenthesis);
        openingBrackets.addTransmission(expression);

        registerPossibleStartState(Collections.singletonList(openingBrackets));
    }
}
