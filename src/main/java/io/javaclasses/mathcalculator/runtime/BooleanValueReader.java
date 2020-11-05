package io.javaclasses.mathcalculator.runtime;

public class BooleanValueReader implements ValueHolderVisitor{
    private Boolean value;
    public static Boolean readDouble(ValueHolder holder)
    {
        BooleanValueReader valueReader = new BooleanValueReader();
        holder.accept(valueReader);
        return valueReader.value;
    }
    @Override
    public void visit(BooleanValueHolder holder) {
        this.value=holder.value();
    }

    @Override
    public void visit(DoubleValueHolder holder) {
        throw new IllegalStateException("Boolean value expected.");
    }

    @Override
    public void visit(StringValueHolder holder) {
        throw new IllegalStateException("Boolean value expected.");
    }

}
