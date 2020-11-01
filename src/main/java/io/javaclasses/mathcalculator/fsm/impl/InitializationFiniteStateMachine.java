package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.runtime.VariableNameAndValuePair;

import java.util.Collections;

/**
 * Implementation of {@link FiniteStateMachine} for parsing
 * any statement from string.
 * For example, statement may be like these:
 * <p>
 * 1) "a=5;"
 * 2) "print(a);"
 */
public class InitializationFiniteStateMachine extends FiniteStateMachine<VariableNameAndValuePair> {

    public InitializationFiniteStateMachine() {
        State<VariableNameAndValuePair> variable = new State<>(false,
                                                               new VariableNameStateAcceptor());
        State<VariableNameAndValuePair> equalSign = new State<>(false,
                                                                new RequiredCharacterStateAcceptorNameAndValuePair(
                                                                        '='));
        State<VariableNameAndValuePair> expression = new State<>(true,
                                                                 new ExpressionStateAcceptorNameAndValuePair());

        variable.addTransmission(equalSign);
        equalSign.addTransmission(expression);

        registerPossibleStartState(Collections.singletonList(variable));
    }

//    public Optional<Command> expression(CharacterIterator inputChain, List<Command> output) {
//
//        List<Command> commands = new ArrayList<>();
//        Status status = run(inputChain, commands);
//        if (status == Status.FINISHED) {
//            return Optional.of(new PushExpressionCommand(commands));
//        }
//        return Optional.empty();
//    }
}
