package io.javaclasses.runtime;

/**
 * Interface of binary operations.
 */
public interface BinaryOperator {

     enum priority {
        MORE_THEN_LOW, // boolean operators
        LOW, // - +
        MEDIUM //* /
    }

    /**
     * This API apply the function.
     *
     * @param firstOperand
     *         is double left operand
     * @param secondOperand
     *         is double left operand
     * @return result of function apply
     */
     ValueHolder apply(ValueHolder firstOperand, ValueHolder secondOperand);

     priority priority();
}
