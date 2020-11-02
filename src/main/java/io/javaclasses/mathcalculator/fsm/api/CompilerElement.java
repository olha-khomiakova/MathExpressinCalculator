package io.javaclasses.mathcalculator.fsm.api;

import io.javaclasses.mathcalculator.runtime.Command;

import java.text.CharacterIterator;
import java.util.Optional;

public interface CompilerElement {
    Optional<Command> compile(CharacterIterator input);
}
