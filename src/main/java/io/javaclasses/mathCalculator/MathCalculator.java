package io.javaclasses.mathCalculator;

import io.javaclasses.mathCalculator.fsm.ExpressionFiniteStateMachine;
import io.javaclasses.mathCalculator.fsm.FiniteStateMachine;
import io.javaclasses.mathCalculator.fsm.NumberFiniteStateMachine;
import io.javaclasses.mathCalculator.math.ShuntingYard;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/**
 * This service calculates result of any mathematical expression.
 * Expression may contain numbers, binary operation.
 * Numbers are integer or decimal, positive or negative.
 * <p>
 * For example, mathematical expression may be like these:
 * 1) "5.25+10*2.1-7.77"
 * 2) "0.1*5+10/2"
 */
public class MathCalculator {
    /**
     * This is API that calculates result of mathematical expression.
     *
     * @param mathExpression is an expression that should be calculated
     * @return result of the mathematical expression
     * @throws IncorrectMathExpressionException
     */
    public double evaluate(String mathExpression) throws IncorrectMathExpressionException {
        CharacterIterator stringNumber = new StringCharacterIterator(mathExpression);
        ShuntingYard result = new ShuntingYard();
        ExpressionFiniteStateMachine expressionFiniteStateMachine = new ExpressionFiniteStateMachine();
        FiniteStateMachine.Status status = expressionFiniteStateMachine.run(stringNumber, result);
        if (status == FiniteStateMachine.Status.DEADLOCK) {
            throw new DeadLock("Incorrectly entered mathematical expression.", stringNumber.getIndex());
        } else if (status == FiniteStateMachine.Status.NOT_STARTED) {
            throw new IncorrectMathExpressionException("Incorrectly entered mathematical expression.", stringNumber.getIndex());
        } else {
            return Double.parseDouble(result.popAllOperators().toString());
        }
    }
}
