package io.javaclasses.mathCalculator.math;

/**
 * Interface of variable binary operations.
 */
public interface BinaryOperator extends Comparable<BinaryOperator> {
    double calculate(double firstOperand, double secondOperand);
    int priority();
}
