package io.javaclasses.mathcalculator.runtime;

public class DoubleValueHolder implements ValueHolder {
    private double value;
    public DoubleValueHolder(double value)
    {
        this.value=value;
    }
    @Override
    public void accept(ValueHolderVisitor visitor) {
         visitor.visit(this);
    }
    public double value()
    {
        return value;
    }
}
