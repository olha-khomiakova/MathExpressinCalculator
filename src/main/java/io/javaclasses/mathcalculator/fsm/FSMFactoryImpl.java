package io.javaclasses.mathcalculator.fsm;

import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.impl.CalculatedFiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.impl.ExpressionFiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.impl.ExpressionWithBracketsFiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.impl.FunctionFiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.impl.InitializationFiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.impl.NumberFiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.impl.StatementFiniteStateMachine;

import java.util.HashMap;
import java.util.Map;

public class FSMFactoryImpl<T> implements FSMFactory<T> {

    private final Map<TypeFSM, FiniteStateMachine<T>> typeOfFSM = new HashMap<>();
    private FSMFactory<T> factory = this;

    public FSMFactoryImpl() {
        typeOfFSM.put(TypeFSM.NUMBER, new Creator() {
            @Override
            public FiniteStateMachine create() {
                return new NumberFiniteStateMachine();
            }
        }.create());
        typeOfFSM.put(TypeFSM.EXPRESSION, new Creator() {
            @Override
            public FiniteStateMachine create() {
                return new ExpressionFiniteStateMachine();
            }
        }.create());
        typeOfFSM.put(TypeFSM.EXPRESSION_WITH_BRACKETS, ((Creator) () -> {
            return new ExpressionWithBracketsFiniteStateMachine();
        }).create());
        typeOfFSM.put(TypeFSM.FUNCTION, new Creator() {
            @Override
            public FiniteStateMachine create() {
                return new FunctionFiniteStateMachine();
            }
        }.create());
        typeOfFSM.put(TypeFSM.CALCULATED, new Creator() {
            @Override
            public FiniteStateMachine create() {
                return new CalculatedFiniteStateMachine();
            }
        }.create());
        typeOfFSM.put(TypeFSM.INITIALIZATION, new Creator() {
            @Override
            public FiniteStateMachine create() {
                return new InitializationFiniteStateMachine();
            }
        }.create());
        typeOfFSM.put(TypeFSM.STATEMENT, new Creator() {
            @Override
            public FiniteStateMachine create() {
                return new StatementFiniteStateMachine();
            }
        }.create());
    }

    @Override
    public FiniteStateMachine<T> create(TypeFSM typeFSM) {
//        switch (typeFSM){
//            case NUMBER:
//                FiniteStateMachine<StringWriter> fsm
//                        = new NumberFiniteStateMachine(this::create);
//            case EXPRESSION:
//                break;
//            case EXPRESSION_WITH_BRACKETS:
//                break;
//            case FUNCTION:
//                break;
//            case CALCULATED:
//                break;

        return typeOfFSM.get(typeFSM);
    }

    interface Creator {

        FiniteStateMachine create();
    }
}

