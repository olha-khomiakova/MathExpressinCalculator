package io.javaclasses.fsm.impl;

import io.javaclasses.fsm.api.Compiler;
import io.javaclasses.fsm.api.CompilerFactory;
import io.javaclasses.fsm.api.CompilerType;

import java.util.EnumMap;

/**
 * This is an implementation of {@link CompilerFactory} that creates certain {@link Compiler},
 * which starts certain {@link io.javaclasses.fsm.base.FiniteStateMachine} depending on input
 * parameter {@link CompilerType}.
 */

public class CompilerFactoryImpl implements CompilerFactory {

    private final EnumMap<CompilerType, Compiler> fsmMap = new EnumMap<>(CompilerType.class);

    public CompilerFactoryImpl() {

        fsmMap.put(CompilerType.NUMBER, input -> new NumberFiniteStateMachine().compile(input));

        /*
         * This is an implementation of {@link Compiler} that starts the {@link ExpressionFiniteStateMachine}
         * with program that stored in input character iterator.
         * If the compilation of program was successful, this compiler receives a
         * {@link io.javaclasses.runtime.Command} that expression finite state machine will return,
         * otherwise FSM returns the Optional.empty().
         *
         * The API returns result of the expression finite state machine.
         */

        fsmMap.put(CompilerType.EXPRESSION,
                   input -> new ExpressionFiniteStateMachine(this).compile(input));

        fsmMap.put(CompilerType.EXPRESSION_WITH_BRACKETS,
                   input -> new ExpressionWithBracketsFiniteStateMachine(this).compile(input));

        fsmMap.put(CompilerType.FUNCTION,
                   input -> new FunctionFiniteStateMachine(this).compile(input));

        fsmMap.put(CompilerType.CALCULATED,
                   input -> new CalculatedFiniteStateMachine(this).compile(input));
        /*
         * This is an implementation of {@link Compiler} that starts the {@link Statement}
         * with program that stored in input character iterator.
         * If the compilation of program was successful, this compiler receives a
         * {@link io.javaclasses.runtime.Command} that expression finite state machine will return,
         * otherwise FSM returns the Optional.empty().
         *
         * The API returns result of the expression finite state machine.
         */
        fsmMap.put(CompilerType.STATEMENT, input -> new Statement(this).compile(input));

        fsmMap.put(CompilerType.INITIALIZATION,
                   input -> new InitializeVariableStatement(this).compile(input));

        fsmMap.put(CompilerType.PROGRAM, input -> new Program(this).compile(input));

        fsmMap.put(CompilerType.BOOLEAN_EXPRESSION,
                   input -> new ConditionFiniteStateMachine(this).compile(input));
        fsmMap.put(CompilerType.BOOLEAN_IN_BRACKETS,
                   input -> new BooleanInBracketsFiniteStateMachine(this).compile(input));
        fsmMap.put(CompilerType.NEGATIVE_BOOLEAN,
                   input -> new NegativeBooleanFiniteStateMachine(this).compile(input));
        fsmMap.put(CompilerType.READ_VARIABLE,
                   input -> new ReadVariableFiniteStateMachine(this).compile(input));
        fsmMap.put(CompilerType.WHILE_CYCLE,
                   input -> new WhileLoopFiniteStateMachine(this).compile(input));

    }

    /**
     * This API creates certain {@link Compiler},
     * which starts certain {@link io.javaclasses.fsm.base.FiniteStateMachine} depending on input
     * parameter {@link CompilerType}.
     *
     * @return required compiler depending on {@link CompilerType}
     */
    @Override
    public Compiler create(CompilerType compilerType) {
        return fsmMap.get(compilerType);
    }
}
