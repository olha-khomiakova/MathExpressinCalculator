package io.javaclasses.mathcalculator.fsm.api;

/**
 * This is part of abstract factory pattern that is a common interface factory for
 * {@link CompilerElement}.
 */
public interface FSMFactory {

    enum TypeFSM {
        NUMBER,
        EXPRESSION,
        EXPRESSION_WITH_BRACKETS,
        FUNCTION,
        CALCULATED,
        INITIALIZATION,
        STATEMENT,
        PROGRAM,
        BOOLEAN_EXPRESSION,
        NAME
    }

    /**
     * This is API that creates certain type of {@link CompilerElement} depending on
     * what type of {@link io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine}
     * compiler element will run.
     *
     * @param typeFSM
     *         is type of finite state machine
     * @return is {@link CompilerElement} certain type
     */
    CompilerElement create(TypeFSM typeFSM);
}
