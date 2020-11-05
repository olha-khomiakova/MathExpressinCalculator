package io.javaclasses.mathcalculator.math;

import io.javaclasses.mathcalculator.runtime.BooleanValueHolder;
import io.javaclasses.mathcalculator.runtime.ValueHolder;

/**
 * Implementation of {@link BinaryOperator} that calculates the addition of two operands .
 */
public class LessBooleanOperator implements BinaryOperator {
    private final BinaryOperator.priority priority;

    public LessBooleanOperator(BinaryOperator.priority priority) {
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
    public ValueHolder calculate(double firstOperand, double secondOperand) {
        return new BooleanValueHolder(firstOperand < secondOperand);
    }

    @Override
    public priority priority() {
        return priority;
    }

}
