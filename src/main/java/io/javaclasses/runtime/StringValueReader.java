package io.javaclasses.runtime;

/**
 * This is part of visitor pattern that implement string reading.
 */
public class StringValueReader implements ValueHolderVisitor {

    private String value;

    /**
     * This API reads string value from {@link ValueHolder} and save it.
     *
     * @param holder
     *         accepts value
     * @return string value
     */
    static String readString(ValueHolder holder) {
        StringValueReader valueReader = new StringValueReader();
        holder.accept(valueReader);
        return valueReader.value;
    }

    @Override
    public void visit(StringValueHolder holder) {
        this.value = holder.value();
    }

    @Override
    public void visit(DoubleValueHolder holder) {
        throw new IllegalStateException("Expected string.");
    }

    @Override
    public void visit(BooleanValueHolder holder) {
        throw new IllegalStateException("String value expected. ");

    }

//    public String value() {
//        if (value == null) {
//            throw new IllegalStateException("String value expected.");
//        }
//        return value;
//    }
}
