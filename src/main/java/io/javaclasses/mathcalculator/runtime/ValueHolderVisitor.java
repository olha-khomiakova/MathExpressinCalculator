package io.javaclasses.mathcalculator.runtime;

/**
 * This is part of visitor pattern that describes a common interface for all types of visitors.
 * It declares a set of methods, differing in the type of the input parameter,
 * that are needed to start an operation for all types of specific elements.
 */
 interface ValueHolderVisitor {

    /**
     * It is set of methods, differing in the type of the input parameter,
     * that are needed to start an operation for all types of specific elements.
     *
     * @param holder
     *         specific element.
     */
    void visit(DoubleValueHolder holder);

    void visit(StringValueHolder holder);

    void visit(BooleanValueHolder holder);
}
