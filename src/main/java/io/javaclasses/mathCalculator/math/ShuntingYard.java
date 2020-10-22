package io.javaclasses.mathCalculator.math;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/**
 * This is implementation of shunting yard algorithm
 * You can read about at {@link "https://en.wikipedia.org/wiki/Shunting-yard_algorithm#:~:text=In%20computer%20science%2C%20the%20shunting,abstract%20syntax%20tree%20(AST)."}
 */
public class ShuntingYard {
    private final Deque<Double> operands = new ArrayDeque<>();

    private final Deque<BinaryOperator> operators = new ArrayDeque<>();

    public void pushBinaryOperator(BinaryOperator operator) {
        if (operators.peekLast() != null) {
            if (operator.priority().ordinal() <=
                    Objects.requireNonNull(operators.peekLast()).priority().ordinal()) {
                calculatePreviousOperator();
                pushBinaryOperator(operator);
                return;
            }
        }
        this.operators.addLast(operator);
    }

    private void calculatePreviousOperator() {
        BinaryOperator operator = operators.pollLast();
        double secondOperand = operands.pollLast();
        double firstOperand = operands.pollLast();
        this.operands.addLast(Objects.requireNonNull(operator).calculate(firstOperand, secondOperand));
    }

    public void pushOperand(double number) {
        operands.addLast(number);
    }

    public Double popAllOperators() {
        while (!operators.isEmpty()) {
            calculatePreviousOperator();
        }
        return operands.peekFirst();
    }
}
