package io.javaclasses.mathcalculator.runtime;

public class StringValueHolder implements ValueHolder {
    private String value;
    public StringValueHolder(String value)
    {
        this.value=value;
    }
    @Override
    public void accept(ValueHolderVisitor visitor) {
         visitor.visit(this);
    }
    public String value()
    {
        return value;
    }
}
