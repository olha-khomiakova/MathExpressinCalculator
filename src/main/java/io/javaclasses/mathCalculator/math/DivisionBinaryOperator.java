package io.javaclasses.mathCalculator.math;

/**
 * Implementation of {@link BinaryOperator}.
 */
public class DivisionBinaryOperator implements BinaryOperator {
    private final int priority = 1;

    /**
     * Calculates the division of two operands
     *
     * @param firstOperand  double left operands
     * @param secondOperand double right operands
     * @return division result
     */
    @Override
    public double calculate(double firstOperand, double secondOperand) {
        return firstOperand / secondOperand;
    }

    @Override
    public int compareTo(BinaryOperator binaryOperator) {
        return priority < binaryOperator.priority() ? 1 : -1;
    }

    public int priority() {
        return priority;
    }
}
