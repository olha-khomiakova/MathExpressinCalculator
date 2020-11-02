package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.api.CompilerElement;
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
 * Implementation of {@link FiniteStateMachine} for parsing
 * and evaluating mathematical expression with brackets from string.
 * The expression looks like this pattern: "("+"{@link ExpressionFiniteStateMachine}"+")".
 * For example:
 * 1) (5+2);
 * 2) (min(3.5,2)*0.1).
 */
public class ExpressionWithBracketsFiniteStateMachine extends FiniteStateMachine<List<Command>> implements CompilerElement {

    public ExpressionWithBracketsFiniteStateMachine(FSMFactory factory) {
        State<List<Command>> openingBrackets = new State<>(false,
                                                           new RequiredCharacterStateAcceptorListCommands(
                                                                   '('));
        State<List<Command>> closingParenthesis = new State<>(true,
                                                              new RequiredCharacterStateAcceptorListCommands(
                                                                      ')'));
        State<List<Command>> expression = new State<>(false,
                                                      new ExpressionStateAcceptorListCommands(factory));

        expression.addTransmission(closingParenthesis);
        openingBrackets.addTransmission(expression);

        registerPossibleStartState(Collections.singletonList(openingBrackets));
    }

    @Override
    public Optional<Command> compile(CharacterIterator input) {
        List<Command> commands = new ArrayList<>();
        Status status = run(input, commands);
        if (status == Status.FINISHED) {
            return Optional.of(new PushExpressionCommand(commands));
        }
        return Optional.empty();
    }
}
