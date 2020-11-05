package io.javaclasses.mathcalculator.math.binaryoperator;

import io.javaclasses.mathcalculator.runtime.DoubleValueHolder;
import io.javaclasses.mathcalculator.runtime.ValueHolder;

/**
 * Implementation of {@link BinaryOperator} that calculates the addition of two operands .
 */
public class AdditionBinaryOperator implements BinaryOperator {

    private final BinaryOperator.priority priority;

    public AdditionBinaryOperator(BinaryOperator.priority priority) {
        this.priority = priority;
    }

    /**
     * Calculates the sum of two operands.
     *
     * @param firstOperand
     *         is double left operands
     * @param secondOperand
     *         is double right operands
     * @return addition result
     */
    @Override
    public double calculate(double firstOperand, double secondOperand) {
        return firstOperand + secondOperand;
    }

    @Override
    public BinaryOperator.priority priority() {
        return this.priority;
    }
}
