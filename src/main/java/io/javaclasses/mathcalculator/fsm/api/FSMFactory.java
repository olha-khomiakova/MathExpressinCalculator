package io.javaclasses.mathcalculator.fsm.api;

public interface FSMFactory {

    enum TypeFSM {
        NUMBER,
        EXPRESSION,
        EXPRESSION_WITH_BRACKETS,
        FUNCTION,
        CALCULATED,
        INITIALIZATION,
        STATEMENT,
        PROCEDURE
    }

    CompilerElement create(TypeFSM typeFSM);
}
