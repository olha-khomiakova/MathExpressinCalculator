package io.javaclasses.mathcalculator.runtime;

public class BooleanValueHolder implements ValueHolder {
    private boolean value;
    public BooleanValueHolder(boolean value)
    {
        this.value=value;
    }
    @Override
    public void accept(ValueHolderVisitor visitor) {
         visitor.visit(this);
    }
    public boolean value()
    {
        return value;
    }
    public String toString()
    {
        return new String(String.valueOf(value));
    }
}
