package io.javaclasses.runtime;

import static io.javaclasses.runtime.DoubleValueReader.readDouble;

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
    public ValueHolder apply(ValueHolder firstOperand, ValueHolder secondOperand) {
        return new DoubleValueHolder(readDouble(firstOperand) + readDouble(secondOperand));
    }

    @Override
    public BinaryOperator.priority priority() {
        return this.priority;
    }
}
