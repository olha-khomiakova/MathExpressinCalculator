package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.PushExpressionCommand;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link FiniteStateMachine} for parsing expression in brackets from string.
 * The expression looks like this pattern: "("+"{@link ExpressionFiniteStateMachine}"+")".
 * For example:
 * 1) (5+2);
 * 2) (min(3.5,2)*0.1).
 */
class ExpressionWithBracketsFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    ExpressionWithBracketsFiniteStateMachine(FSMFactory factory) {
        State<List<Command>> openingBrackets = new State<>(false,
                                                           new RequiredCharacterStateAcceptorListCommands(
                                                                   '('));
        State<List<Command>> closingBrackets = new State<>(true,
                                                           new RequiredCharacterStateAcceptorListCommands(
                                                                   ')'));
        State<List<Command>> expression = new State<>(false,
                                                      new FSMStateAcceptor(factory,
                                                                           FSMFactory.TypeFSM.EXPRESSION));
        State<List<Command>> booleanExpression = new State<>(false,
                                                             new FSMStateAcceptor(factory,
                                                                                  FSMFactory.TypeFSM.BOOLEAN_EXPRESSION));

        expression.addTransmission(closingBrackets);
        openingBrackets.addTransmission(expression);
        booleanExpression.addTransmission(closingBrackets);
        openingBrackets.addTransmission(booleanExpression);

        registerPossibleStartState(Collections.singletonList(openingBrackets));
    }
    /**
     * This api starts the machine and gets the parsing interrupt status.
     * If the status is FINISHED, it returns the {@link Optional<Command>}
     * in which the parsed commands are stored, else return Optional.empty();
     *
     * @param input
     *         is an iterable string with input data
     * @return the {@link Optional<Command>}, if the status of run is FINISHED,
     *         else return Optional.empty()
     */
    Optional<Command> expressionWithBrackets(CharacterIterator input) {
        List<Command> commands = new ArrayList<>();
        Status status = run(input, commands);
        if (status == Status.FINISHED) {
            return Optional.of(new PushExpressionCommand(commands));
        }
        return Optional.empty();
    }
}
