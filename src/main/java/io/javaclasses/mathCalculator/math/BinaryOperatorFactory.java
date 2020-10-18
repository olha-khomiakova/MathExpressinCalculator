package io.javaclasses.mathCalculator.math;

public class BinaryOperatorFactory {
    public BinaryOperator getRequiredBinaryOperator(char requiredOperator) {
        switch (requiredOperator) {
            case '+': {
                return new AdditionalBinaryOperator();
            }
            case '-': {
                return new SubtractionBinaryOperator();
            }
            case '*': {
                return new MultiplicationBinaryOperator();
            }
            case '/': {
                return new DivisionBinaryOperator();
            }
        }
        return null;
    }

    public boolean isBinaryOperator(char requiredOperator) {
        switch (requiredOperator) {
            case '+':
            case '-':
            case '*':
            case '/': {
                return true;
            }
        }
        return false;
    }
}
