package io.javaclasses.mathCalculator.fsm;

import static java.util.Arrays.asList;

/**
 * Implementation of {@link FiniteStateMachine} for parsing number from string.
 */
public class NumberFiniteStateMachine extends FiniteStateMachine<StringBuilder> {
    public NumberFiniteStateMachine() {
        State<StringBuilder> negativeSign = new State(false, new SingleCharacterStateAcceptor('-'));
        State<StringBuilder> intDigit = new State(true, new DigitCharacterStateAcceptor());
        State<StringBuilder> point = new State(false, new SingleCharacterStateAcceptor('.'));
        State<StringBuilder> decimalDigit = new State(true, new DigitCharacterStateAcceptor());

        negativeSign.addTransmission(intDigit);
        intDigit.addTransmission(intDigit);
        intDigit.addTransmission(point);
        point.addTransmission(decimalDigit);
        decimalDigit.addTransmission(decimalDigit);

        registerPossibleStartState(asList(negativeSign, intDigit));
    }
}
