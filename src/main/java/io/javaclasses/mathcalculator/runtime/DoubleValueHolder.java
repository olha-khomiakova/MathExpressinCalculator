package io.javaclasses.mathcalculator.runtime;
/**
 * This is part of visitor pattern that implement visitor acceptance methods for double value.
 */
public class DoubleValueHolder implements ValueHolder {
    private final double value;
    public DoubleValueHolder(double value)
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
    public double value()
    {
        return value;
    }
    @Override
    public String toString()
    {
        return String.valueOf(value);
    }
}
