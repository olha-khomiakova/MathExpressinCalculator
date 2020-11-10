package io.javaclasses.fsm.api;

/**
 * This is enumeration of existing type of {@link Compiler}, that is referred to one
 * corresponding {@link Compiler}, that will be created in the {@link CompilerFactory}.
 */
public enum CompilerType {
    NUMBER,
    EXPRESSION,
    EXPRESSION_WITH_BRACKETS,
    FUNCTION,
    CALCULATED,
    INITIALIZATION,
    STATEMENT,
    PROGRAM,
    BOOLEAN_EXPRESSION,
    NEGATIVE_BOOLEAN,
    BOOLEAN_IN_BRACKETS,
    READ_VARIABLE,
    WHILE_CYCLE
}
