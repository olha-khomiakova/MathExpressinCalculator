package io.javaclasses.mathcalculator.runtime;

/**
 * This service is part of visitor pattern that describes the method of accepting a visitor.
 */
public interface ValueHolder {

    /**
     * This is method of accepting a visitor.
     *
     * @param visitor
     *         is common interface for all types of visitors
     */
    void accept(ValueHolderVisitor visitor);

    @Override
    String toString();
}
