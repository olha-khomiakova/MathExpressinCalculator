package io.javaclasses.runtime;

/**
 * This is part of visitor pattern that implement double reading.
 */
public class DoubleValueReader implements ValueHolderVisitor {

    private Double value;

    /**
     * This API reads double value from {@link ValueHolder} and save it.
     *
     * @param holder
     *         accepts value
     * @return double value
     */
    public static double readDouble(ValueHolder holder) {
        DoubleValueReader valueReader = new DoubleValueReader();
        holder.accept(valueReader);
        return valueReader.value;
    }

    @Override
    public void visit(DoubleValueHolder holder) {
        this.value = holder.value();
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
