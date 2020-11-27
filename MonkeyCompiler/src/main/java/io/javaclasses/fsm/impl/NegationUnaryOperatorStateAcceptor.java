package io.javaclasses.fsm.impl;

import io.javaclasses.fsm.api.Compiler;
import io.javaclasses.fsm.api.CompilerFactory;
import io.javaclasses.fsm.api.CompilerType;
import io.javaclasses.fsm.base.StateAcceptor;
import io.javaclasses.runtime.Command;
import io.javaclasses.runtime.PushNegationUnaryOperatorCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.CharacterIterator;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link StateAcceptor} that defines
 * whether the transition from one state to unary negation operator state is possible.
 * And if possible adds it to the outputChain and moves an iterator forward in an inputChain.
 */
public class NegationUnaryOperatorStateAcceptor implements StateAcceptor<List<Command>> {

    private final CompilerFactory factory;
    private final CompilerType type;

    public NegationUnaryOperatorStateAcceptor(CompilerFactory factory,
                                              CompilerType FSMtype) {
        this.factory = factory;
        this.type = FSMtype;
    }

    /**
     * This API creates unary negation operator, adds it to the command list
     * and moves an iterator forward in an inputChain.
     *
     * @param inputChain
     *         is an iterable string with input data
     * @param outputChain
     *         is an command list to which will be added command with operator.
     * @return true if it was possible to create a unary negation operator
     *         and add it to the outputChain, otherwise it returns false
     */
    @Override
    public boolean accept(CharacterIterator inputChain, List<Command> outputChain) {
        final Logger logger = LoggerFactory.getLogger(NegationUnaryOperatorStateAcceptor.class);
        char currentCharacter = inputChain.current();
        int index = inputChain.getIndex();
        if ('!' == currentCharacter) {

            inputChain.next();

            Compiler compiler = factory.create(type);
            Optional<Command> command = compiler.compile(inputChain);
            if (command.isPresent()) {
                outputChain.add(new PushNegationUnaryOperatorCommand(command.get()));
                if (logger.isInfoEnabled()) {
                    logger.info(this.getClass()
                                    .getSimpleName() + " add " + '!');
                }
                return true;
            }
        }
        inputChain.setIndex(index);
        return false;
    }

    @Override
    public boolean isLexeme() {
        return true;
    }
}
