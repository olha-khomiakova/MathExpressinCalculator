package io.javaclasses.mathcalculator.math.binaryoperator;

/**
 * Interface of variable binary operations.
 */
public interface BinaryOperator {

    enum priority {
        LOW, // - +
        MEDIUM //* /
    }

    double calculate(double firstOperand, double secondOperand);

    priority priority();
}
