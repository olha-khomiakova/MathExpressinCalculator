package io.javaclasses.mathcalculator.runtime;

/**
 * Implementation of {@link BinaryOperator} that return
 * the boolean value depending on the relation.
 */
public class LessBooleanOperator implements BinaryOperator {

    private final BinaryOperator.priority priority;

    LessBooleanOperator() {
        this.priority = BinaryOperator.priority.MORE_THEN_LOW;
    }

    /**
     * This API determines the veracity of the relation.
     *
     * @param firstOperand
     *         is double left operands
     * @param secondOperand
     *         is double right operands
     * @return true if left operands is less then right, otherwise false
     */
    @Override
    public ValueHolder apply(double firstOperand, double secondOperand) {
        return new BooleanValueHolder(firstOperand < secondOperand);
    }

    @Override
    public priority priority() {
        return priority;
    }

}
