package io.javaclasses.mathcalculator.runtime;

public class StringValueReader implements ValueHolderVisitor{
    private String value;
    public static String readString(ValueHolder holder)
    {
        StringValueReader valueReader = new StringValueReader();
        holder.accept(valueReader);
        return valueReader.value;
    }

    @Override
    public void visit(DoubleValueHolder holder) {
        throw new IllegalStateException("Expected string.");
    }

    @Override
    public void visit(StringValueHolder holder) {
        this.value=holder.value();
    }
    public String value(){
        if(value==null)
        {
            throw new IllegalStateException("String value expected.");
        }
        return value;
    }
}
