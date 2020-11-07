package io.javaclasses.runtime;


/**
 * Implementation of {@link io.javaclasses.runtime.BinaryOperator} that calculates the addition of two operands .
 */
public class AdditionBinaryOperator implements BinaryOperator {

    private final BinaryOperator.priority priority;

    public AdditionBinaryOperator() {
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
