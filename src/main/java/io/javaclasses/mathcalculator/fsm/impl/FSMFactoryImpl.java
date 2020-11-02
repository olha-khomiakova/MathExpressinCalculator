package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.api.CompilerElement;
import io.javaclasses.mathcalculator.fsm.api.FSMFactory;

public class FSMFactoryImpl implements FSMFactory {
    @Override
    public CompilerElement create(TypeFSM typeFSM) {
        CompilerElement fsm;
        switch (typeFSM) {
            case NUMBER:
                fsm = new NumberFiniteStateMachine();
                return fsm;
            case EXPRESSION:
                fsm = new ExpressionFiniteStateMachine(this);
                return fsm;
            case EXPRESSION_WITH_BRACKETS:
                fsm = new ExpressionWithBracketsFiniteStateMachine(this);
                return fsm;
            case FUNCTION:
                fsm = new FunctionFiniteStateMachine(this);
                return fsm;
            case CALCULATED:
                fsm = new CalculatedFiniteStateMachine(this);
                return fsm;
            case STATEMENT:
                fsm = new StatementFiniteStateMachine(this);
                return fsm;
            case INITIALIZATION:
                fsm = new InitializationFiniteStateMachine(this);
                return fsm;
            case PROCEDURE:
                fsm = new ProcedureFiniteStateMachine(this);
                return fsm;
            default:
                throw new RuntimeException(typeFSM+" type is not served by this compiler");
        }
    }
}

