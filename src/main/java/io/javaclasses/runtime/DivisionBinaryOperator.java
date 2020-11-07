package io.javaclasses.runtime;

/**
 * Implementation of {@link BinaryOperator} that calculates the division of two operands .
 */
public class DivisionBinaryOperator implements BinaryOperator {

    private final BinaryOperator.priority priority;

    public DivisionBinaryOperator(BinaryOperator.priority priority) {
        this.priority = priority;
    }

    /**
     * This API calculates the division of two operands.
     *
     * @param firstOperand
     *         is double left operands
     * @param secondOperand
     *         is double right operands
     * @return division result
     */
    @Override
    public ValueHolder apply(double firstOperand, double secondOperand) {
        return new DoubleValueHolder(firstOperand / secondOperand);
    }

    @Override
    public BinaryOperator.priority priority() {
        return priority;
    }
}
