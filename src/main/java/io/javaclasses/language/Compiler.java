package io.javaclasses.language;

import io.javaclasses.mathcalculator.fsm.impl.FSMFactoryImpl;
import io.javaclasses.mathcalculator.fsm.api.CompilerElement;
import io.javaclasses.mathcalculator.fsm.api.FSMFactory;
import io.javaclasses.mathcalculator.runtime.Command;
import io.javaclasses.mathcalculator.runtime.RuntimeEnvironment;

import java.io.PrintStream;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Optional;

class Compiler {

    public PrintStream compile(String code) {
        CharacterIterator stringNumber = new StringCharacterIterator(code);
        RuntimeEnvironment environment = new RuntimeEnvironment();
        FSMFactory factory = new FSMFactoryImpl();
        CompilerElement compilerElement = factory.create(
                FSMFactory.TypeFSM.STATEMENT);
        Optional<Command> command = compilerElement.compile(stringNumber);
        if (stringNumber.getIndex() != stringNumber.getEndIndex()||!command.isPresent()) {
            throw new IncorrectStatementException("Incorrectly entered statement in position " +
                                                          stringNumber.getIndex() + '.',
                                                  stringNumber.getIndex());
        }
        command.get().execute(environment);
        return environment.output();
    }
}
