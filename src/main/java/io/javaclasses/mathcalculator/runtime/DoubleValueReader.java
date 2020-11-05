package io.javaclasses.mathcalculator.runtime;

public class DoubleValueReader implements ValueHolderVisitor{
    private Double value;
    public static double readDouble(ValueHolder holder)
    {
        DoubleValueReader valueReader = new DoubleValueReader();
        holder.accept(valueReader);
        return valueReader.value;
    }
    @Override
    public void visit(DoubleValueHolder holder) {
        this.value=holder.value();
    }

    @Override
    public void visit(StringValueHolder holder) {
        throw new IllegalStateException("Double value expected. ");
    }

    @Override
    public void visit(BooleanValueHolder holder) {
        throw new IllegalStateException("Double value expected.");

    }
//
//    public double value(){
//        if(value==null)
//        {
//            throw new IllegalStateException("Double value expected.");
//        }
//        return value;
//    }
}
