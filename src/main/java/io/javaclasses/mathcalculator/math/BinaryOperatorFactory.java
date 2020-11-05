package io.javaclasses.mathcalculator.math;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BinaryOperatorFactory {

    private final Map<Character, BinaryOperator> binaryOperatorMap = new HashMap<>();

    public BinaryOperatorFactory() {
        binaryOperatorMap.put('+', new Creator() {
            @Override
            public AdditionBinaryOperator create() {
                return new AdditionBinaryOperator(BinaryOperator.priority.LOW);
            }
        }.create());
        binaryOperatorMap.put('-', new Creator() {
            @Override
            public BinaryOperator create() {
                return new SubtractionBinaryOperator(BinaryOperator.priority.LOW);
            }
        }.create());
        binaryOperatorMap.put('*', new Creator() {
            @Override
            public BinaryOperator create() {
                return new MultiplicationBinaryOperator(BinaryOperator.priority.MEDIUM);
            }
        }.create());
        binaryOperatorMap.put('/', new Creator() {
            @Override
            public BinaryOperator create() {
                return new DivisionBinaryOperator(BinaryOperator.priority.MEDIUM);
            }
        }.create());
        binaryOperatorMap.put('<', new Creator() {
            @Override
            public BinaryOperator create() {
                return new LessBooleanOperator(BinaryOperator.priority.MORE_THEN_LOW);
            }
        }.create());
        binaryOperatorMap.put('>', new Creator() {
            @Override
            public BinaryOperator create() {
                return new GreaterBooleanOperator(BinaryOperator.priority.MORE_THEN_LOW);
            }
        }.create());
    }

    public Optional<BinaryOperator> getBinaryOperator(char requiredOperator) {
        return Optional.ofNullable(binaryOperatorMap.get(requiredOperator));
    }

    private interface Creator {
        BinaryOperator create();
    }
}
