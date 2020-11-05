package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.api.CompilerElement;
import io.javaclasses.mathcalculator.fsm.api.FSMFactory;

public class FSMFactoryImpl implements FSMFactory {
    @Override
    public CompilerElement create(TypeFSM typeFSM) {
        switch (typeFSM) {
            case NUMBER:
                return input -> {
                    NumberFiniteStateMachine fsm = new NumberFiniteStateMachine();
                    return fsm.number(input);
                };
            case EXPRESSION:
                return input -> {
                    ExpressionFiniteStateMachine fsm = new ExpressionFiniteStateMachine(this);
                    return fsm.expression(input);
                };
            case EXPRESSION_WITH_BRACKETS:
                return input -> {
                    ExpressionWithBracketsFiniteStateMachine fsm =
                            new ExpressionWithBracketsFiniteStateMachine(this);
                    return fsm.expressionWithBrackets(input);
                };
            case FUNCTION:
                return input -> {
                    FunctionFiniteStateMachine fsm = new FunctionFiniteStateMachine(this);
                    return fsm.function(input);
                };
            case CALCULATED:
                return input -> {
                    CalculatedFiniteStateMachine fsm = new CalculatedFiniteStateMachine(this);
                    return fsm.calculated(input);
                };
            case STATEMENT:
                return input -> {
                    StatementFiniteStateMachine fsm = new StatementFiniteStateMachine(this);
                    return fsm.statement(input);
                };
            case INITIALIZATION:
                return input -> {
                    InitializationFiniteStateMachine fsm = new InitializationFiniteStateMachine(
                            this);
                    return fsm.initialization(input);
                };
            case PROGRAM:
                return input -> {
                    ProgramFiniteStateMachine fsm = new ProgramFiniteStateMachine(this);
                    return fsm.program(input);
                };
//            case BOOLEAN_EXPRESSION:
//                return input -> {
//                    BooleanExpressionFiniteStateMachine fsm = new BooleanExpressionFiniteStateMachine(this);
//                    return fsm.booleanExpression(input);
//                };
            default:
                throw new RuntimeException(typeFSM + " type is not served by this compiler");
        }
    }
}
