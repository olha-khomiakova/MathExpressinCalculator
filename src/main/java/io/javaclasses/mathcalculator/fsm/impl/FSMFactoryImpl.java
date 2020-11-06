package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.api.CompilerElement;
import io.javaclasses.mathcalculator.fsm.api.FSMFactory;

import java.util.EnumMap;

/**
 * This is part of abstract factory pattern that is a implementation of {@link FSMFactory}.
 * It creates certain {@link CompilerElement}, which has
 */

public class FSMFactoryImpl implements FSMFactory {

    private final EnumMap<TypeFSM, CompilerElement> fsmMap = new EnumMap<>(TypeFSM.class);

    public FSMFactoryImpl() {
        fsmMap.put(TypeFSM.NUMBER, input -> {
            NumberFiniteStateMachine fsm = new NumberFiniteStateMachine();
            return fsm.number(input);
        });
        fsmMap.put(TypeFSM.EXPRESSION, input -> {
            ExpressionFiniteStateMachine fsm = new ExpressionFiniteStateMachine(this);
            return fsm.expression(input);
        });
        fsmMap.put(TypeFSM.EXPRESSION_WITH_BRACKETS, input -> {
            ExpressionWithBracketsFiniteStateMachine fsm =
                    new ExpressionWithBracketsFiniteStateMachine(this);
            return fsm.expressionWithBrackets(input);
        });
        fsmMap.put(TypeFSM.FUNCTION, input -> {
            FunctionFiniteStateMachine fsm = new FunctionFiniteStateMachine(this);
            return fsm.function(input);
        });
        fsmMap.put(TypeFSM.CALCULATED, input -> {
            CalculatedFiniteStateMachine fsm = new CalculatedFiniteStateMachine(this);
            return fsm.calculated(input);
        });
        fsmMap.put(TypeFSM.STATEMENT, input -> {
            StatementFiniteStateMachine fsm = new StatementFiniteStateMachine(this);
            return fsm.statement(input);
        });
        fsmMap.put(TypeFSM.INITIALIZATION, input -> {
            InitializationFiniteStateMachine fsm = new InitializationFiniteStateMachine(
                    this);
            return fsm.initialization(input);
        });
        fsmMap.put(TypeFSM.PROGRAM, input -> {
            ProgramFiniteStateMachine fsm = new ProgramFiniteStateMachine(this);
            return fsm.program(input);
        });

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
