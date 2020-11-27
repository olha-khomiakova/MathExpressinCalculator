package io.javaclasses.runtime;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/**
 * This is implementation of shunting yard algorithm
 * You can read about it at <a href = "https://en.wikipedia.org/wiki/Shunting-yard_algorithm#:~:text=In%20computer%20science%2C%20the%20shunting,abstract%20syntax%20tree%20(AST)."}></a>
 */
public class ShuntingYard {

    private final Deque<ValueHolder> operands = new ArrayDeque<>();
    private final Deque<BinaryOperator> operators = new ArrayDeque<>();

    /**
     * This API calculates expression that is in shunting yard.
     */
    private void popAllOperators() {
        while (!operators.isEmpty()) {
            calculatePreviousOperator();
        }
    }

    public ValueHolder result() {

        popAllOperators();
        return operands.peek();
    }

    void pushBinaryOperator(BinaryOperator operator) {

        if (operators.peek() != null) {
            if (operator.priority()
                        .ordinal() <=
                    Objects.requireNonNull(operators.peek())
                           .priority()
                           .ordinal()) {
                calculatePreviousOperator();
                pushBinaryOperator(operator);
                return;
            }
        }
        this.operators.push(operator);
    }

    private void calculatePreviousOperator() {
        BinaryOperator operator = operators.pop();
        ValueHolder secondOperand = Objects.requireNonNull(operands.pop());
        ValueHolder firstOperand = Objects.requireNonNull(operands.pop());
        this.operands.push(operator.apply(firstOperand,
                                          secondOperand));
    }

    public void pushOperand(ValueHolder number) {
        operands.push(number);
    }

}
