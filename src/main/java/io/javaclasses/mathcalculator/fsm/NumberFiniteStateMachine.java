package io.javaclasses.mathcalculator.fsm;

import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;

import java.io.StringWriter;

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
}
