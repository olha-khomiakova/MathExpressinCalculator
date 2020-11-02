package io.javaclasses.mathcalculator.math.binaryoperator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BinaryOperatorFactory {

    private final Map<Character, BinaryOperator> binaryOperatorMap = new HashMap<>();

    public BinaryOperatorFactory() {
        binaryOperatorMap.put('+', new Creator() {
            private BinaryOperator create() {
                return new AdditionBinaryOperator(BinaryOperator.priority.LOW);
            }
        }.create());
        binaryOperatorMap.put('-', new Creator() {
            private BinaryOperator create() {
                return new SubtractionBinaryOperator(BinaryOperator.priority.LOW);
            }
        }.create());
        binaryOperatorMap.put('*', new Creator() {
            private BinaryOperator create() {
                return new MultiplicationBinaryOperator(BinaryOperator.priority.MEDIUM);
            }
        }.create());
        binaryOperatorMap.put('/', new Creator() {
            private BinaryOperator create() {
                return new DivisionBinaryOperator(BinaryOperator.priority.MEDIUM);
            }
        }.create());
    }

    public Optional<BinaryOperator> getRequiredBinaryOperator(char requiredOperator) {
        return Optional.ofNullable(binaryOperatorMap.get(requiredOperator));
    }

    private interface Creator {

    }
}
