package io.javaclasses.runtime;

import static io.javaclasses.runtime.DoubleValueReader.readDouble;

/**
 * Implementation of {@link BinaryOperator} that calculates the subtraction of two operands .
 */
public class SubtractionBinaryOperator implements BinaryOperator {

    private final BinaryOperator.priority priority;

    public SubtractionBinaryOperator(BinaryOperator.priority priority) {
        this.priority = priority;
    }

    /**
     * This API calculates the subtraction of two operands.
     *
     * @param firstOperand
     *         is double left operands
     * @param secondOperand
     *         is double right operands
     * @return subtraction result
     */
    @Override
    public ValueHolder apply(ValueHolder firstOperand, ValueHolder secondOperand) {
        return new DoubleValueHolder(readDouble(firstOperand) - readDouble(secondOperand));
    }

    @Override
    public BinaryOperator.priority priority() {
        return priority;
    }

}
