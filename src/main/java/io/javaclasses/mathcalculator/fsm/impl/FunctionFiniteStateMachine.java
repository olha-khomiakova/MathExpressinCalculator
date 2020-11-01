package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.runtime.FunctionDataStructure;

import java.util.Collections;

/**
 * Implementation of {@link FiniteStateMachine} for parsing and evaluating function from string.
 * Function may be min(x,y) or max(x,y).
 */
public class FunctionFiniteStateMachine<F extends FiniteStateMachine<FunctionDataStructure>> extends FiniteStateMachine<FunctionDataStructure> {

    public FunctionFiniteStateMachine() {
        State<FunctionDataStructure> name = new State<>(false, new FunctionNameStateAcceptor());
        State<FunctionDataStructure> openingBrackets = new State<>(false,
                                                                   new FunctionSingleCharacterStateAcceptor(
                                                                           '('));
        State<FunctionDataStructure> expression = new State<>(false,
                                                              new ExpressionStateAcceptorFunctionDataStructure());
        State<FunctionDataStructure> closingBrackets = new State<>(true,
                                                                   new FunctionSingleCharacterStateAcceptor(
                                                                           ')'));
        State<FunctionDataStructure> comma = new State<>(false,
                                                         new FunctionSingleCharacterStateAcceptor(
                                                                 ','));

        name.addTransmission(openingBrackets);
        openingBrackets.addTransmission(expression);
        expression.addTransmission(comma);
        comma.addTransmission(expression);
        expression.addTransmission(closingBrackets);

        registerPossibleStartState(Collections.singletonList(name));
    }

//    public Optional<Command> function(CharacterIterator inputChain,
//                                      FunctionDataStructure dataStructure) {
//        Status status = run(inputChain, dataStructure);
//        if (status == Status.FINISHED) {
//            return Optional.of(new PushFunctionCommand(dataStructure));
//        }
//        return Optional.empty();
//    }
}
