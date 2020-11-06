package io.javaclasses.mathcalculator.runtime;

/**
 * Implementation of {@link BinaryOperator} that calculates the addition of two operands .
 */
public class AdditionBinaryOperator implements BinaryOperator {

    private final BinaryOperator.priority priority;

     AdditionBinaryOperator() {
        this.priority = BinaryOperator.priority.LOW;
    }

    /**
     * This API calculates the sum of two operands.
     *
     * @param firstOperand
     *         is double left operand
     * @param secondOperand
     *         is double right operand
     * @return addition result
     */
    @Override
    public ValueHolder apply(double firstOperand, double secondOperand) {
        return new DoubleValueHolder(firstOperand + secondOperand);
    }

    @Override
     public BinaryOperator.priority priority() {
        return this.priority;
    }
}
