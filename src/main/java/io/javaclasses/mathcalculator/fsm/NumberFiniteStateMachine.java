package io.javaclasses.mathcalculator.fsm;

import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.PushOperandCommand;

import java.io.StringWriter;
import java.text.CharacterIterator;
import java.util.Optional;

import static java.util.Arrays.asList;

/**
 * Implementation of {@link FiniteStateMachine} for parsing number from string.
 * Numbers are integer or decimal, positive or negative.
 */
class NumberFiniteStateMachine extends FiniteStateMachine<StringWriter> {

    NumberFiniteStateMachine() {
        State<StringWriter> negativeSign = new State<>(false,
                                                       new SingleCharacterStateAcceptor('-'));
        State<StringWriter> intDigit = new State<>(true, new DigitCharacterStateAcceptor());
        State<StringWriter> point = new State<>(false, new SingleCharacterStateAcceptor('.'));
        State<StringWriter> decimalDigit = new State<>(true, new DigitCharacterStateAcceptor());

        negativeSign.addTransmission(intDigit);
        intDigit.addTransmission(intDigit);
        intDigit.addTransmission(point);
        point.addTransmission(decimalDigit);
        decimalDigit.addTransmission(decimalDigit);

        registerPossibleStartState(asList(negativeSign, intDigit));
    }

    public Optional<Command> number(CharacterIterator input, StringWriter output) {
        Status status = run(input, output);
        if (status == Status.FINISHED) {
            return Optional.of(new PushOperandCommand(Double.parseDouble(output.toString())));
        }

        return Optional.empty();
    }
}
