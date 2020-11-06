package io.javaclasses.mathcalculator.runtime;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

import static io.javaclasses.mathcalculator.runtime.DoubleValueReader.readDouble;

/**
 * This is implementation of shunting yard algorithm
 * You can read about it at {@link "https://en.wikipedia.org/wiki/Shunting-yard_algorithm#:~:text=In%20computer%20science%2C%20the%20shunting,abstract%20syntax%20tree%20(AST)."}
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

        if (operators.peekLast() != null) {
            if (operator.priority()
                    .ordinal() <=
                    Objects.requireNonNull(operators.peekLast())
                            .priority()
                            .ordinal()) {
                calculatePreviousOperator();
                pushBinaryOperator(operator);
                return;
            }
        }
        this.operators.addLast(operator);
    }

    private void calculatePreviousOperator() {
        BinaryOperator operator = operators.pollLast();
        ValueHolder secondOperand = Objects.requireNonNull(operands.pollLast());
        ValueHolder firstOperand = Objects.requireNonNull(operands.pollLast());
        this.operands.addLast(operator.apply(readDouble(firstOperand),
                readDouble(secondOperand)));
    }

    public void pushOperand(ValueHolder number) {
        operands.addLast(number);
    }

}
