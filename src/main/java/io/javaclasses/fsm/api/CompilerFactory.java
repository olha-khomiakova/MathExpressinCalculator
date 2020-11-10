package io.javaclasses.fsm.api;

/**
 * This service is abstract factory that declares an interface for classes that implement a service
 * generating a specific {@link Compiler}.
 *
 * It creates a {@link Compiler} depending on what type of {@link CompilerType}
 * is passed in the parameter.
 */
public interface CompilerFactory {

    /**
     * This is API that creates a {@link Compiler} depending on what type of {@link CompilerType}
     * is passed in the parameter.
     *
     * @param compilerType
     *         the type of {@link Compiler}
     * @return {@link Compiler} certain type
     */
    Compiler create(CompilerType compilerType);
}
