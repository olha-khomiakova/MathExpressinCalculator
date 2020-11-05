//package io.javaclasses.mathcalculator.math;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//public class BooleanOperatorFactory {
//
//    private final Map<Character, Creator> binaryOperatorMap = new HashMap<>();
//
//    public BooleanOperatorFactory() {
//        binaryOperatorMap.put('<', () -> new LessBooleanOperator());
//        binaryOperatorMap.put('>', GreaterBooleanOperator::new);
//
//    }
//
//    public Optional<BooleanOperator> getBooleanOperator(char requiredOperator) {
//        return Optional.ofNullable(binaryOperatorMap.get(requiredOperator)
//                                                    .create());
//    }
//
//    private interface Creator {
//
//        BooleanOperator create();
//    }
//}
