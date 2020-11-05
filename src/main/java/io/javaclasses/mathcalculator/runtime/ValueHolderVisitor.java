package io.javaclasses.mathcalculator.runtime;

public interface ValueHolderVisitor {
    void visit(DoubleValueHolder holder);
    void visit (StringValueHolder holder);
    void visit(BooleanValueHolder holder);
}
