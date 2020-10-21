package io.javaclasses.mathCalculator.math;

/**
 * Implementation of {@link BinaryOperator}.
 */
public class AdditionalBinaryOperator implements BinaryOperator {
    private final BinaryOperator.priority priority;
    public AdditionalBinaryOperator(BinaryOperator.priority priority)
    {
        this.priority = priority;
    }
    /**
     * Calculates the sum of two operands
     * @param firstOperand double left operands
     * @param secondOperand double right operands
     * @return addition result
     */
    @Override
    public double calculate(double firstOperand, double secondOperand) {
        return firstOperand + secondOperand;
    }

    @Override
    public BinaryOperator.priority priority() {
        return this.priority;
    }
    @Override
    public int compareTo(BinaryOperator anotherBinaryOperator)
    {
        return this.priority.compareTo(anotherBinaryOperator.priority());
    }
}
