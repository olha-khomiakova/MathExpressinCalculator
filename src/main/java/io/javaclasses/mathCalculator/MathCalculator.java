package io.javaclasses.mathCalculator;

import io.javaclasses.mathCalculator.fsm.ExpressionFiniteStateMachine;
import io.javaclasses.mathCalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathCalculator.math.ShuntingYard;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

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
public class MathCalculator {
    /**
     * This is API that calculates result of mathematical expression.
     *
     * @param mathExpression is an expression that should be calculated
     * @return result of the mathematical expression
     * @throws IncorrectMathExpressionException occurs if the expression format is incorrect
     */
    public double evaluate(String mathExpression) throws IncorrectMathExpressionException {
        CharacterIterator stringNumber = new StringCharacterIterator(mathExpression);
        ShuntingYard result = new ShuntingYard();
        ExpressionFiniteStateMachine expressionFiniteStateMachine = new ExpressionFiniteStateMachine();
        FiniteStateMachine.Status status = expressionFiniteStateMachine.run(stringNumber, result);
        if (status == FiniteStateMachine.Status.NOT_STARTED ||
                stringNumber.getIndex() != stringNumber.getEndIndex()) {
            throw new IncorrectMathExpressionException("Incorrectly entered mathematical " +
                    "expression in position " + stringNumber.getIndex() + ".", stringNumber.getIndex());
        } else {
            return Double.parseDouble(result.popAllOperators().toString());
        }
    }
}
