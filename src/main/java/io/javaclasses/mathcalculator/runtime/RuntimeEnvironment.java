package io.javaclasses.mathcalculator.runtime;

import java.util.ArrayDeque;
import java.util.Deque;

public class RuntimeEnvironment {

    private final Deque<ShuntingYard> stack = new ArrayDeque<>();
    //private final Map<String, Double> memory = new HashMap<>();
    //private final PrintStream output = new PrintStream(new ByteArrayOutputStream());

    public RuntimeEnvironment() {
        startStack();
    }

    public final void startStack() {
        stack.push(new ShuntingYard());
    }

    public void closeStack() {
        stack.poll();
    }

    public ShuntingYard stack() {
        return stack.peek();
    }

//    public Map<String, Double> memory() {
//        return memory;
//    }

//    public PrintStream output() {
//        return output;
//    }
}
