package io.javaclasses.mathcalculator.runtime;
/**
 * This is part of visitor pattern that implement visitor acceptance methods for boolean value.
 */
public class BooleanValueHolder implements ValueHolder {
    private final boolean value;
    public BooleanValueHolder(boolean value)
    {
        this.value=value;
    }
    /**
     * This API calls the visit method that matches the type of this element.
     * This way the visitor will know which element he is working with.
     * @param visitor is common interface for all types of visitors
     */
    @Override
    public void accept(ValueHolderVisitor visitor) {
         visitor.visit(this);
    }
    public boolean value()
    {
        return value;
    }
    @Override
    public String toString()
    {
        return String.valueOf(value);
    }
}
