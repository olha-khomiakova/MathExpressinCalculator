package io.javaclasses.mathcalculator.runtime;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Deque;

public class RuntimeEnvironment {

    private final Deque<ShuntingYard> stack = new ArrayDeque<>();
    private final Memory memory = new Memory();
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    //private final Deque<ValueHolder> systemStack = new ArrayDeque<>();

    public RuntimeEnvironment() {
        startStack();
    }

    final void startStack() {
        stack.push(new ShuntingYard());
    }

    void closeStack() {
         stack.poll();
    }

    public ShuntingYard stack() {
        return stack.peek();
    }

    Memory memory() {
        return this.memory;
    }
    public ByteArrayOutputStream output()
    {
        return this.output;
    }
}
