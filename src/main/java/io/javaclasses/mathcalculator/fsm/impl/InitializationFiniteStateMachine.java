package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.DataStructure;
import io.javaclasses.mathcalculator.runtime.PushVariableCommand;

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
public class InitializationFiniteStateMachine extends FiniteStateMachine<DataStructure> {

    InitializationFiniteStateMachine(FSMFactory factory) {
        State<DataStructure> variable = new State<>(false,
                                                    new NameStateAcceptor());
        State<DataStructure> equalSign = new State<>(false,
                                                     new RequiredCharacterStateAcceptorFunction(
                                                             '='));
        State<DataStructure> expression = new State<>(true,
                                                      new ExpressionStateAcceptorDataStructure(
                                                              factory, FSMFactory.TypeFSM.EXPRESSION));

        State<DataStructure> booleanExpression = new State<>(true,
                                                             new ExpressionStateAcceptorDataStructure(
                                                                     factory,
                                                                     FSMFactory.TypeFSM.BOOLEAN_EXPRESSION));
        variable.addTransmission(equalSign);
        equalSign.addTransmission(expression);
        equalSign.addTransmission(booleanExpression);

        registerPossibleStartState(Collections.singletonList(variable));
    }

    public Optional<Command> initialization(CharacterIterator input) {
        DataStructure dataStructure = new DataStructure();
        Status status = run(input, dataStructure);
        if (status == Status.FINISHED) {
            return Optional.of(
                    new PushVariableCommand(dataStructure.name(), dataStructure.parameters()));
        }
        return Optional.empty();
    }

}
