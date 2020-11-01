package io.javaclasses.mathcalculator;

import io.javaclasses.mathcalculator.fsm.FSMFactoryImpl;
import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.RuntimeEnvironment;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

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
        FSMFactory<List<Command>> factory = new FSMFactoryImpl<>();
        FiniteStateMachine<List<Command>> expressionFSM = factory.create(
                FSMFactory.TypeFSM.EXPRESSION);
        List<Command> commandList = new ArrayList<>();
        FiniteStateMachine.Status accepted = expressionFSM.run(stringNumber, commandList);
        if (stringNumber.getIndex() != stringNumber.getEndIndex() || accepted ==
                FiniteStateMachine.Status.NOT_STARTED) {
            throw new IncorrectMathExpressionException("Incorrectly entered mathematical " +
                                                               "expression in position " +
                                                               stringNumber.getIndex() + '.',
                                                       stringNumber.getIndex());
        }
        for (Command command : commandList) {
            command.execute(environment);
        }
        return environment.stack()
                          .result()
                          .get();
    }
}
