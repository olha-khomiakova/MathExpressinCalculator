package io.javaclasses.fsm.impl;

import io.javaclasses.fsm.base.FiniteStateMachine;
import io.javaclasses.fsm.base.StateAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor} that defines
 * whether the transition from one state to name state is possible.
 * And if it is possible adds it to the outputChain and moves an iterator forward in an inputChain.
 */
public class NameStateAcceptor implements StateAcceptor<NameAndParametersOutputChain> {

    private final Logger logger = LoggerFactory.getLogger(NameStateAcceptor.class);

    /**
     * This API creates {@link StringWriter}, {@link NameBuilderFiniteStateMachine}, starts it,
     * adds it to the {@link NameAndParametersOutputChain} and moves an iterator forward in an inputChain.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an {@link NameAndParametersOutputChain} to which will be added something name.
     * @return true if it is possible to parse name and add it to the outputChain,
     *         otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, NameAndParametersOutputChain outputChain) {

        FiniteStateMachine<StringWriter> fsm =
                new NameBuilderFiniteStateMachine();
        StringWriter stringWriter = new StringWriter();
        if (fsm.run(inputChain, stringWriter) == FiniteStateMachine.Status.FINISHED) {
            outputChain.addFunctionName(stringWriter);
            if (logger.isInfoEnabled()) {
                logger.info(this.getClass()
                                .getSimpleName() + " finished: " + stringWriter);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isLexeme() {
        return true;
    }
}
