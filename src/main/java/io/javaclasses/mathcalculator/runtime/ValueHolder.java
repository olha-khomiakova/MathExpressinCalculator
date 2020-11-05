package io.javaclasses.mathcalculator.runtime;

public interface ValueHolder {
    void accept(ValueHolderVisitor visitor);
    @Override
    String toString();
}
