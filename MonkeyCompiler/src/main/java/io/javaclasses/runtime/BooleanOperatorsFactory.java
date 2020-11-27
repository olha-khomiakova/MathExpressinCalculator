package io.javaclasses.runtime;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This service is factory that contains binary operators.
 */
public class BooleanOperatorsFactory {

    private final Map<String, Creator> binaryOperatorMap = new HashMap<>();

    public BooleanOperatorsFactory() {
        binaryOperatorMap.put("<", LessBooleanOperator::new);
        binaryOperatorMap.put(">", GreaterBooleanOperator::new);
        binaryOperatorMap.put("<=", LessEqualsBooleanOperator::new);
        binaryOperatorMap.put(">=", GreaterEqualsBooleanOperator::new);
        binaryOperatorMap.put("!=", NotEqualsBooleanOperator::new);
        binaryOperatorMap.put("==", EqualsBooleanOperator::new);

    }

    /**
     * This method checks if the binary operator we need exists and returns an object of the needed
     * type or
     * Optional.empty().
     *
     * @return required binary operator
     */
    public Optional<BinaryOperator> getBinaryOperator(String requiredOperator) {
        if (binaryOperatorMap.containsKey(requiredOperator)) {
            return Optional.ofNullable(binaryOperatorMap.get(requiredOperator)
                                                        .create());
        }
        return Optional.empty();
    }

    private interface Creator {

        BinaryOperator create();
    }
}
