package io.javaclasses.runtime;

import static io.javaclasses.runtime.DoubleValueReader.readDouble;

/**
 * Implementation of {@link BinaryOperator} that calculates the multiplication of two operands .
 */
public class MultiplicationBinaryOperator implements BinaryOperator {

    private final BinaryOperator.priority multiplicationPriority;

    public MultiplicationBinaryOperator(BinaryOperator.priority multPriority) {
        this.multiplicationPriority = multPriority;
    }

    /**
     * This API calculates the multiplication of two operands.
     *
     * @param firstOperand
     *         is double left operands
     * @param secondOperand
     *         is double right operands
     * @return multiplication result
     */
    @Override
    public ValueHolder apply(ValueHolder firstOperand, ValueHolder secondOperand) {
        return new DoubleValueHolder(readDouble(firstOperand) * readDouble(secondOperand));
    }

    @Override
    public BinaryOperator.priority priority() {
        return multiplicationPriority;
    }

}
