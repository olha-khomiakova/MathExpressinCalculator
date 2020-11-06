package io.javaclasses.mathcalculator.fsm.impl;

import io.javaclasses.mathcalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathcalculator.fsm.base.State;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.DoubleValueHolder;
import io.javaclasses.mathcalculator.runtime.PushOperandCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    Optional<Command> number(CharacterIterator input) {

        final Logger logger = LoggerFactory.getLogger(NumberFiniteStateMachine.class);

        StringWriter output = new StringWriter();
        Status status = run(input, output);
        if (status == Status.FINISHED) {
            if (logger.isInfoEnabled()) {
                logger.info(this.getClass()
                                .getSimpleName() + " finished:  " + output);
            }
            return Optional.of(new PushOperandCommand(
                    new DoubleValueHolder(Double.parseDouble(output.toString()))));
        }

        return Optional.empty();
    }
}
