package io.javaclasses.monkey;

import io.javaclasses.fsm.api.CompilerElement;
import io.javaclasses.fsm.api.FSMFactory;
import io.javaclasses.fsm.impl.FSMFactoryImpl;
import io.javaclasses.runtime.Command;
import io.javaclasses.runtime.IncorrectStatementException;
import io.javaclasses.runtime.RuntimeEnvironment;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Optional;

/**
 * This service compiles and interprets program presented as a character iterator.
 * The program may contain:
 * - initialization of variables with numbers, other variables, mathematical
 * and boolean expressions;
 * - procedure for outputting some variables.
 * Each statement ends with a semicolon.
 * <p>
 * For example, program may be like these:
 * 1) "a=5;b=a;delete(a);print(b);"
 * 2) "a=min(2,5)<4;"
 */

class Monkey {

    /**
     * This is API that compiles a program, gets {@link Command} and execute it.
     *
     * @param stringProgram is character iterator that stores program code
     * @param environment   is data structure that stores, output stream, system stack
     */
    public void interpret(String stringProgram, RuntimeEnvironment environment) throws
                                                                                IncorrectStatementException {

        CharacterIterator program = new StringCharacterIterator(stringProgram);
        FSMFactory factory = new FSMFactoryImpl();

        CompilerElement compiler = factory.create(FSMFactory.TypeFSM.PROGRAM);

        Optional<Command> command = compiler.compile(program);

        if (!command.isPresent()) {

            throw new IncorrectStatementException("Incorrectly entered statement in position " +
                    program.getIndex() + '.',
                    program.getIndex());
        }
        environment.startStack();
        command.get().execute(environment);
        environment.closeStack();

        if (environment.stack() != null) {

            throw new IllegalStateException("Shunting Yard stack is not closed.");
        }
    }
}
