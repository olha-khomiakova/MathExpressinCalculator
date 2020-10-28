package io.javaclasses.mathcalculator.fsm;

import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.runtime.FunctionDataStructure;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.PushFunctionCommand;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.Optional;

/**
 * Implementation of {@link FiniteStateMachine} for parsing and evaluating function from string.
 * Function may be min(x,y) or max(x,y).
 */
class FunctionFiniteStateMachine extends FiniteStateMachine<FunctionDataStructure> {

    FunctionFiniteStateMachine() {
        State<FunctionDataStructure> name = new State<>(false, new FunctionNameStateAcceptor());
        State<FunctionDataStructure> openingBrackets = new State<>(false,
                                                                   new FunctionSingleCharacterStateAcceptor(
                                                                           '('));
        State<FunctionDataStructure> expression = new State<>(false,
                                                              new ExpressionStateForFunctionAcceptor());
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

    public Optional<Command> function(CharacterIterator inputChain,
                                      FunctionDataStructure dataStructure) {
        Status status = run(inputChain, dataStructure);
        if (status == Status.FINISHED) {
            return Optional.of(new PushFunctionCommand(dataStructure));
        }
        return Optional.empty();
    }
}
