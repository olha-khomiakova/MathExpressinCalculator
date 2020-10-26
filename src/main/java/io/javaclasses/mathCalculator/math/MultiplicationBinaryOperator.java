package io.javaclasses.mathCalculator.math;

/**
 * Implementation of {@link BinaryOperator} that calculates the multiplication of two operands .
 */
public class MultiplicationBinaryOperator implements BinaryOperator {
    private final BinaryOperator.priority priority;

    public MultiplicationBinaryOperator(BinaryOperator.priority priority) {
        this.priority = priority;
    }

    /**
     * Calculates the multiplication of two operands
     *
     * @param firstOperand  is double left operands
     * @param secondOperand is double right operands
     * @return multiplication result
     */
    @Override
    public double calculate(double firstOperand, double secondOperand) {
        return firstOperand * secondOperand;
    }

    public BinaryOperator.priority priority() {
        return priority;
    }

}
