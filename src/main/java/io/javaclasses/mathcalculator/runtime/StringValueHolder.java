package io.javaclasses.mathcalculator.runtime;

/**
 * This is part of visitor pattern that stores string value
 * and implements visitor acceptance methods for string value.
 */
public class StringValueHolder implements ValueHolder {

    private final String value;

    StringValueHolder(String value) {
        this.value = value;
    }

    /**
     * This API calls the visit method that matches the type of this element.
     * This way the visitor will know which element he is working with.
     *
     * @param visitor
     *         is common interface for all types of visitors
     */
    @Override
    public void accept(ValueHolderVisitor visitor) {
        visitor.visit(this);
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
