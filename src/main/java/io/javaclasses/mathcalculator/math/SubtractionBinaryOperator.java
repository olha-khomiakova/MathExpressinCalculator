package io.javaclasses.mathcalculator.math;

import io.javaclasses.mathcalculator.runtime.DoubleValueHolder;
import io.javaclasses.mathcalculator.runtime.ValueHolder;

/**
 * Implementation of {@link BinaryOperator} that calculates the subtraction of two operands .
 */
public class SubtractionBinaryOperator implements BinaryOperator {

    private final BinaryOperator.priority priority;

    public SubtractionBinaryOperator(BinaryOperator.priority priority) {
        this.priority = priority;
    }

    /**
     * Calculates the subtraction of two operands.
     *
     * @param firstOperand
     *         is double left operands
     * @param secondOperand
     *         is double right operands
     * @return subtraction result
     */
    @Override
    public ValueHolder calculate(double firstOperand, double secondOperand) {
        return new DoubleValueHolder(firstOperand - secondOperand);
    }

    @Override
    public BinaryOperator.priority priority() {
        return priority;
    }

}
