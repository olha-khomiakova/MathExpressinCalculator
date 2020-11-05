package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.api.CompilerElement;
import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.DoubleValueHolder;
import io.javaclasses.mathcalculator.runtime.PushOperandCommand;

import java.io.StringWriter;
import java.text.CharacterIterator;
import java.util.Optional;

import static java.util.Arrays.asList;

/**
 * Implementation of {@link FiniteStateMachine} for parsing number from string.
 * Numbers are integer or decimal, positive or negative.
 */
public class NumberFiniteStateMachine extends FiniteStateMachine<StringWriter> {

    public NumberFiniteStateMachine() {
        State<StringWriter> negativeSign = new State<>(false,
                                                       new RequiredCharacterStateAcceptorStringWriter(
                                                               '-'));
        State<StringWriter> intDigit = new State<>(true, new DigitCharacterStateAcceptor());
        State<StringWriter> point = new State<>(false,
                                                new RequiredCharacterStateAcceptorStringWriter(
                                                        '.'));
        State<StringWriter> decimalDigit = new State<>(true, new DigitCharacterStateAcceptor());

        negativeSign.addTransmission(intDigit);
        intDigit.addTransmission(intDigit);
        intDigit.addTransmission(point);
        point.addTransmission(decimalDigit);
        decimalDigit.addTransmission(decimalDigit);

        registerPossibleStartState(asList(negativeSign, intDigit));
    }


    public Optional<Command> number(CharacterIterator input) {
        StringWriter output = new StringWriter();
        Status status = run(input, output);
        if (status == Status.FINISHED) {
            return Optional.of(new PushOperandCommand(
                    new DoubleValueHolder(Double.parseDouble(output.toString()))));
        }

        return Optional.empty();
    }
}
