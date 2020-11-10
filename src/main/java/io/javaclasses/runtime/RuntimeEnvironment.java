package io.javaclasses.runtime;

import java.io.StringWriter;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * This service is data structure for storing {@link Memory}, {@link java.util.Deque<ShuntingYard>},
 * {@link java.io.ByteArrayOutputStream} and processing them.
 */
public class RuntimeEnvironment {

    private final Deque<ShuntingYard> stack = new ArrayDeque<>();
    private final Memory memory = new Memory();
    //    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
//
//    private final PrintStream outputFacade = new PrintStream(output);
    private final StringWriter output = new StringWriter();

    /**
     * This API starts new stack for evaluation some expression.
     */
    public final void startStack() {
        stack.push(new ShuntingYard());
    }

    /**
     * This API closes stack when expression was evaluated.
     */
    public void closeStack() {
        stack.pop();
    }

    public ShuntingYard stack() {
        return stack.peek();
    }

    Memory memory() {
        return this.memory;
    }

    public StringWriter output() {
        return this.output;
    }

    public String outputAsText() {

        return output.toString();
    }
}
