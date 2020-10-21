package io.javaclasses.mathCalculator.math;

/**
 * Implementation of {@link BinaryOperator}.
 */
public class DivisionBinaryOperator implements BinaryOperator {
    private final BinaryOperator.priority priority;
    public DivisionBinaryOperator(BinaryOperator.priority priority)
    {
        this.priority = priority;
    }
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


    public BinaryOperator.priority priority() {
        return priority;
    }
    @Override
    public int compareTo(BinaryOperator anotherBinaryOperator)
    {
        return this.priority.compareTo(anotherBinaryOperator.priority());
    }
}
