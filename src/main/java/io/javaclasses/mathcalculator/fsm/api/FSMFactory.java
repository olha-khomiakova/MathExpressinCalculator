package io.javaclasses.mathcalculator.fsm.api;

import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;

public interface FSMFactory<T> {

    enum TypeFSM {
        NUMBER,
        EXPRESSION,
        EXPRESSION_WITH_BRACKETS,
        FUNCTION,
        CALCULATED,
        INITIALIZATION,
        STATEMENT
    }

    FiniteStateMachine<T> create(TypeFSM typeFSM);
}
