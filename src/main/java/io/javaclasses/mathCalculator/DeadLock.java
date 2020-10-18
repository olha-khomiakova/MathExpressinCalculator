package io.javaclasses.mathCalculator;

/**
 * This is exception that appears if expression are parsed,
 * but expression does not look like a completed mathematical expression.
 */
public class DeadLock extends RuntimeException {
    public DeadLock(String message, int position)
    {
        super(message);
    }
}
