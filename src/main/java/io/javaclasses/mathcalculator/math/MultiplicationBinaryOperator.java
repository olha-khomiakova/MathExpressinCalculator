package io.javaclasses.mathcalculator.math;

import io.javaclasses.mathcalculator.runtime.DoubleValueHolder;
import io.javaclasses.mathcalculator.runtime.ValueHolder;

/**
 * Implementation of {@link BinaryOperator} that calculates the multiplication of two operands .
 */
public class MultiplicationBinaryOperator implements BinaryOperator {

    private final BinaryOperator.priority priority;

    public MultiplicationBinaryOperator(BinaryOperator.priority priority) {
        this.priority = priority;
    }

    /**
     * Calculates the multiplication of two operands.
     *
     * @param firstOperand
     *         is double left operands
     * @param secondOperand
     *         is double right operands
     * @return multiplication result
     */
    @Override
    public ValueHolder calculate(double firstOperand, double secondOperand) {
        return new DoubleValueHolder(firstOperand * secondOperand);
    }

    @Override
    public BinaryOperator.priority priority() {
        return priority;
    }

}
