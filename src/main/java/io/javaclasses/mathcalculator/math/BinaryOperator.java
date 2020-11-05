package io.javaclasses.mathcalculator.math;

import io.javaclasses.mathcalculator.runtime.ValueHolder;

/**
 * Interface of variable binary operations.
 */
public interface BinaryOperator {

    enum priority {
        MORE_THEN_LOW, // boolean operators
        LOW, // - +
        MEDIUM //* /
    }

    ValueHolder calculate(double firstOperand, double secondOperand);

    priority priority();
}
