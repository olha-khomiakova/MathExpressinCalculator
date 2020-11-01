package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;

import java.io.StringWriter;

import static java.util.Arrays.asList;

/**
 * Implementation of {@link FiniteStateMachine} for parsing number from string.
 * Numbers are integer or decimal, positive or negative.
 */
public class NumberFiniteStateMachine extends FiniteStateMachine<StringWriter> {

    public NumberFiniteStateMachine(FSMFactory factory) {

    }

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

//    public Optional<Command> create(CharacterIterator input, StringWriter output) {
//        Status status = run(input, output);
//        if (status == Status.FINISHED) {
//            return Optional.of(new PushOperandCommand(Double.parseDouble(output.toString())));
//        }
//
//        return Optional.empty();
//    }
}
