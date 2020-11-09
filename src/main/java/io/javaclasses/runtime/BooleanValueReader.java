package io.javaclasses.runtime;
/**
 * This is part of visitor pattern that implement boolean reading.
 */
public class BooleanValueReader implements ValueHolderVisitor{
    private Boolean value;
    /**
     * This API reads string value from {@link ValueHolder} and save it.
     *
     * @param holder
     *         accepts value
     * @return boolean value
     */
    public static Boolean readBoolean(ValueHolder holder)
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
        throw new IllegalStateException("Boolean value expected. ");
    }

    @Override
    public void visit(StringValueHolder holder) {
        throw new IllegalStateException("Boolean value expected.");
    }

}
