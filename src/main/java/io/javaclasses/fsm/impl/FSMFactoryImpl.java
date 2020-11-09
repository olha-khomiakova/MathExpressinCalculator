package io.javaclasses.fsm.impl;

import io.javaclasses.fsm.api.CompilerElement;
import io.javaclasses.fsm.api.FSMFactory;

import java.util.EnumMap;

/**
 * This is part of abstract factory pattern that is a implementation of {@link FSMFactory}.
 * It creates certain {@link CompilerElement}, which starts certain FSM
 */

public class FSMFactoryImpl implements FSMFactory {

    private final EnumMap<TypeFSM, CompilerElement> fsmMap = new EnumMap<>(TypeFSM.class);

    public FSMFactoryImpl() {

        fsmMap.put(TypeFSM.NUMBER, input -> new NumberFiniteStateMachine().compile(input));

        fsmMap.put(TypeFSM.EXPRESSION,
                   input -> new ExpressionFiniteStateMachine(this).compile(input));

        fsmMap.put(TypeFSM.EXPRESSION_WITH_BRACKETS,
                   input -> new ExpressionWithBracketsFiniteStateMachine(this).compile(input));

        fsmMap.put(TypeFSM.FUNCTION, input -> new FunctionFiniteStateMachine(this).compile(input));

        fsmMap.put(TypeFSM.CALCULATED,
                   input -> new CalculatedFiniteStateMachine(this).compile(input));

        fsmMap.put(TypeFSM.STATEMENT, input -> new Statement(this).compile(input));

        fsmMap.put(TypeFSM.INITIALIZATION,
                   input -> new InitializeVariableStatement(this).compile(input));

        fsmMap.put(TypeFSM.PROGRAM, input -> new Program(this).compile(input));

        fsmMap.put(TypeFSM.BOOLEAN_EXPRESSION, input -> new BooleanExpressionFiniteStateMachine(this).compile(input));

    }

    /**
     * This method return certain {@link CompilerElement} type.
     *
     * @return required compiler element
     */
    @Override
    public CompilerElement create(TypeFSM typeFSM) {
        return fsmMap.get(typeFSM);
    }
}
