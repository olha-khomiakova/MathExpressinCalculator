package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.api.CompilerElement;
import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.PushVariableCommand;
import io.javaclasses.mathcalculator.runtime.NameAndValuePair;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.Optional;

/**
 * Implementation of {@link FiniteStateMachine} for parsing
 * any statement from string.
 * For example, statement may be like these:
 * <p>
 * 1) "a=5;"
 * 2) "print(a);"
 */
public class InitializationFiniteStateMachine extends FiniteStateMachine<NameAndValuePair> implements CompilerElement {

    public InitializationFiniteStateMachine(FSMFactory factory) {
        State<NameAndValuePair> variable = new State<>(false,
                                                       new NameStateAcceptor());
        State<NameAndValuePair> equalSign = new State<>(false,
                                                        new RequiredCharacterStateAcceptorNameAndValuePair(
                                                                        '='));
        State<NameAndValuePair> expression = new State<>(true,
                                                         new ExpressionStateAcceptorNameAndValuePair(factory));

        variable.addTransmission(equalSign);
        equalSign.addTransmission(expression);

        registerPossibleStartState(Collections.singletonList(variable));
    }

    @Override
    public Optional<Command> compile(CharacterIterator input) {
        NameAndValuePair pair = new NameAndValuePair();
        Status status = run(input, pair);
        if (status == Status.FINISHED) {
            return Optional.of(new PushVariableCommand(pair));
        }
        return Optional.empty();
    }

}
