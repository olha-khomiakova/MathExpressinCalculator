package io.javaclasses.mathCalculator.fsm;

/**
 * Implementation of {@link FiniteStateMachine} for parsing number from string.
 */
public class NumberFiniteStateMachine extends FiniteStateMachine {
    public NumberFiniteStateMachine() {
        State negativeSign = new State(false, new SingleCharacterStateAcceptor('-'));
        State intDigit = new State(true, new DigitCharacterStateAcceptor());
        State point = new State(false, new SingleCharacterStateAcceptor('.'));
        State decimalDigit = new State(true, new DigitCharacterStateAcceptor());

        negativeSign.addTransmission(intDigit);
        intDigit.addTransmission(intDigit);
        intDigit.addTransmission(point);
        point.addTransmission(decimalDigit);
        decimalDigit.addTransmission(decimalDigit);

        registerPossibleStartState(negativeSign, intDigit);
    }
}
