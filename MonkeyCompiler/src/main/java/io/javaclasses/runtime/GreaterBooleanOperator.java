package io.javaclasses.runtime;

import static io.javaclasses.runtime.DoubleValueReader.readDouble;

/**
 * Implementation of {@link BinaryOperator} that return
 * the boolean value depending on the relation.
 */
public class GreaterBooleanOperator implements BinaryOperator {

    private final BinaryOperator.priority greaterPriority;

    GreaterBooleanOperator() {
        this.greaterPriority = BinaryOperator.priority.MORE_THEN_LOW;
    }

    /**
     * This API determines the veracity of the relation.
     *
     * @param firstOperand
     *         is double left operands
     * @param secondOperand
     *         is double right operands
     * @return true if left operands is greater then right, otherwise false
     */
    @Override
    public ValueHolder apply(ValueHolder firstOperand, ValueHolder secondOperand) {
        return new BooleanValueHolder(readDouble(firstOperand) > readDouble(secondOperand));
    }

    @Override
    public priority priority() {
        return greaterPriority;
    }

}
