package io.javaclasses.mathcalculator;

import io.javaclasses.mathcalculator.fsm.ExpressionFiniteStateMachine;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.RuntimeEnvironment;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This service calculates result of any mathematical expression.
 * Expression may contain numbers, binary operation, function.
 * Numbers are integer or decimal, positive or negative.
 * Function are min(x,y) or max(x,y).
 * <p>
 * For example, mathematical expression may be like these:
 * 1) "5.25+(10*2.1)-7.77"
 * 2) "0.1*max(5,10)/2"
 */
@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
class MathCalculator {

    /**
     * This is API that calculates result of mathematical expression.
     *
     * @param mathExpression
     *         is an expression that should be calculated
     * @return result of the mathematical expression
     *         occurs if the expression format is incorrect
     */
    public double evaluate(String mathExpression) {
        CharacterIterator stringNumber = new StringCharacterIterator(mathExpression);
        RuntimeEnvironment environment = new RuntimeEnvironment();
        ExpressionFiniteStateMachine expressionFSM = new ExpressionFiniteStateMachine();
        List<Command> commandList = new ArrayList<>();
        Optional<Command> command = expressionFSM.expression(stringNumber, commandList);
        if (stringNumber.getIndex() != stringNumber.getEndIndex() || !command.isPresent()) {
            throw new IncorrectMathExpressionException("Incorrectly entered mathematical " +
                                                               "expression in position " +
                                                               stringNumber.getIndex() + '.',
                                                       stringNumber.getIndex());
        }
        command.get()
               .execute(environment);

        return environment.stack()
                          .result().get();
    }
}
