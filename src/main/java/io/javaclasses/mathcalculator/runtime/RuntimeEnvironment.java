package io.javaclasses.mathcalculator.runtime;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * This service is data structure for storing {@link Memory}, {@link java.util.Deque<ShuntingYard>},
 * {@link java.io.ByteArrayOutputStream} and processing them.
 */
public class RuntimeEnvironment {

    private final Deque<ShuntingYard> stack = new ArrayDeque<>();
    private final Memory memory = new Memory();
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    private final PrintStream outputFacade = new PrintStream(output);

    public RuntimeEnvironment() {

        startStack();
    }

    /**
     * This API starts new stack for evaluation some expression.
     */
    final void startStack() {
        stack.push(new ShuntingYard());
    }

    /**
     * This API closes stack when expression was evaluated.
     */
    void closeStack() {
        stack.pop();
    }

    public ShuntingYard stack() {
        return stack.peek();
    }

    Memory memory() {
        return this.memory;
    }

    public PrintStream output() {
        return this.outputFacade;
    }

    public String outputAsText() {

        return output.toString();
    }
}
