package io.javaclasses.fsm.impl;

import io.javaclasses.fsm.api.CompilerFactory;
import io.javaclasses.fsm.api.CompilerType;
import io.javaclasses.fsm.base.FiniteStateMachine;
import io.javaclasses.fsm.base.State;
import io.javaclasses.runtime.Command;
import io.javaclasses.runtime.FunctionFactory;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.Optional;

/**
 * Implementation of {@link FiniteStateMachine} for parsing function and procedure from string.
 * It may be like that:
 * 1) min(x,y)
 * 2) max(x,y)
 * 3) print(x)
 */
class FunctionFiniteStateMachine extends FiniteStateMachine<StringAndCommandsDataStructure> {

    FunctionFiniteStateMachine(CompilerFactory factory) {
        State<StringAndCommandsDataStructure> name = new State<>(false, new NameStateAcceptor());
        State<StringAndCommandsDataStructure> openingBrackets = new State<>(false,
                                                                            new RequiredCharacterStateAcceptorFunction(
                                                                                    '('));
        State<StringAndCommandsDataStructure> expression = new State<>(false,
                                                                       new ExpressionStateAcceptorDataStructure(
                                                                               factory,
                                                                               CompilerType.EXPRESSION));
        State<StringAndCommandsDataStructure> booleanExpression = new State<>(false,
                                                                              new ExpressionStateAcceptorDataStructure(
                                                                                      factory,
                                                                                      CompilerType.BOOLEAN_EXPRESSION));
        State<StringAndCommandsDataStructure> closingBrackets = new State<>(true,
                                                                            new RequiredCharacterStateAcceptorFunction(
                                                                                    ')'));
        State<StringAndCommandsDataStructure> comma = new State<>(false,
                                                                  new RequiredCharacterStateAcceptorFunction(
                                                                          ','));
        State<StringAndCommandsDataStructure> negativeBooleanExpression = new State<>(false,
                                                                                      new ExpressionStateAcceptorDataStructure(
                                                                                              factory,
                                                                                              CompilerType.NEGATIVE_BOOLEAN));

        name.addTransmission(openingBrackets);
        openingBrackets.addTransmission(negativeBooleanExpression);
        openingBrackets.addTransmission(booleanExpression);
        openingBrackets.addTransmission(expression);
        negativeBooleanExpression.addTransmission(comma);
        booleanExpression.addTransmission(comma);
        expression.addTransmission(comma);
        comma.addTransmission(negativeBooleanExpression);
        comma.addTransmission(booleanExpression);
        comma.addTransmission(expression);
        negativeBooleanExpression.addTransmission(closingBrackets);
        booleanExpression.addTransmission(closingBrackets);
        expression.addTransmission(closingBrackets);

        registerPossibleStartState(Collections.singletonList(name));
    }

    /**
     * This api starts the machine and gets the parsing interrupt status.
     * If the status is FINISHED, it returns the {@link Optional<Command>}
     * in which the parsed commands are stored, else return Optional.empty();
     *
     * @param input
     *         is an iterable string with input data
     * @return {@link Optional<Command>}, if the status of run is FINISHED,
     *         else return Optional.empty()
     */
    public Optional<Command> compile(CharacterIterator input) {
        int index = input.getIndex();
        StringAndCommandsDataStructure nameAndParameters = new StringAndCommandsDataStructure();
        Status status = run(input, nameAndParameters);
        if (status == Status.FINISHED) {
            return Optional.of(new FunctionFactory(nameAndParameters.name(),
                                                   nameAndParameters.parameters()).create());
        }
        input.setIndex(index);
        return Optional.empty();
    }
}
