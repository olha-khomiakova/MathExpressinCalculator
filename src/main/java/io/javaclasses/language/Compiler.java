package io.javaclasses.language;

import io.javaclasses.mathcalculator.fsm.api.CompilerElement;
import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.fsm.impl.FSMFactoryImpl;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.RuntimeEnvironment;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Optional;

/**
 * This service compiles and interprets some program presented as a character iterator.
 * The program may contain initialization of variables with numbers, other variables, mathematical
 * and boolean expressions, procedures for outputting and freeing memory of some variables.
 * Each statement ends with a semicolon.
 * <p>
 * For example, program may be like these:
 * 1) "a=5;b=a;delete(a);print(b);"
 * 2) "a=min(2,5)<4;"
 */

class Compiler {

    /**
     * This is API that compiles a program, gets {@link Command} and execute it.
     *
     * @param stringProgram
     *         is character iterator that stores program code
     * @param environment
     *         is data structure that stores, output stream, system stack
     */
    public void compile(String stringProgram, RuntimeEnvironment environment) {
        CharacterIterator program = new StringCharacterIterator(stringProgram);
        FSMFactory factory = new FSMFactoryImpl();
        CompilerElement compilerElement = factory.create(FSMFactory.TypeFSM.STATEMENT);
        Optional<Command> command = compilerElement.compile(program);
        if (program.getIndex() != program.getEndIndex() || !command.isPresent()) {
            throw new IncorrectStatementException("Incorrectly entered statement in position " +
                                                          program.getIndex() + '.',
                                                  program.getIndex());
        }
        command.get()
               .execute(environment);
    }
}
