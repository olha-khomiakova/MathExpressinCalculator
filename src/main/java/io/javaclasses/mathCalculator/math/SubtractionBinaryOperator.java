package io.javaclasses.mathCalculator.math;

/**
 * Implementation of {@link BinaryOperator}.
 */
public class SubtractionBinaryOperator implements BinaryOperator {
    private final BinaryOperator.priority priority;

    public SubtractionBinaryOperator(BinaryOperator.priority priority) {
        this.priority = priority;
    }

    /**
     * Calculates the subtraction of two operands
     *
     * @param firstOperand  double left operands
     * @param secondOperand double right operands
     * @return subtraction result
     */
    @Override
    public double calculate(double firstOperand, double secondOperand) {
        return firstOperand - secondOperand;
    }

    public BinaryOperator.priority priority() {
        return priority;
    }

    @Override
    public int compareTo(BinaryOperator anotherBinaryOperator) {
        return this.priority.compareTo(anotherBinaryOperator.priority());
    }
}
