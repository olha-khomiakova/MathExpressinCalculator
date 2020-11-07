package io.javaclasses.mathcalculator;

import io.javaclasses.fsm.api.CompilerElement;
import io.javaclasses.fsm.api.FSMFactory;
import io.javaclasses.fsm.impl.FSMFactoryImpl;
import io.javaclasses.runtime.Command;
import io.javaclasses.runtime.RuntimeEnvironment;
import io.javaclasses.runtime.ValueHolder;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Optional;

import static io.javaclasses.runtime.DoubleValueReader.readDouble;

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
class MathCalculator {

    /**
     * This is API that calculates result of mathematical expression.
     *
     * @param mathExpression is an expression that should be calculated
     * @return result of the mathematical expression
     * occurs if the expression format is incorrect
     */
    public double evaluate(String mathExpression) {

        CharacterIterator stringNumber = new StringCharacterIterator(mathExpression);
        RuntimeEnvironment environment = new RuntimeEnvironment();

        FSMFactory factory = new FSMFactoryImpl();
        CompilerElement compilerElement = factory.create(FSMFactory.TypeFSM.EXPRESSION);

        Optional<Command> command = compilerElement.compile(stringNumber);

        if ( !command.isPresent()||stringNumber.getIndex()!=stringNumber.getEndIndex()) {
            throw new IncorrectMathExpressionException("Incorrectly entered mathematical " +
                    "expression in position " +
                    stringNumber.getIndex() + '.',
                    stringNumber.getIndex());
        }

        environment.startStack();
        command.get().execute(environment);
        ValueHolder result = environment.stack().result();

        if (result == null) {


            throw new IncorrectMathExpressionException("Impossible to calculate the expression " +
                    stringNumber.getIndex() + '.',
                    stringNumber.getIndex());

        }

        return readDouble(result);
    }
}
