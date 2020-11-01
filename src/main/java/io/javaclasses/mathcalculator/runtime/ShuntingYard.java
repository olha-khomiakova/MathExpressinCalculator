package io.javaclasses.mathcalculator.runtime;

import io.javaclasses.mathcalculator.math.BinaryOperator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.Optional;

/**
 * This is implementation of shunting yard algorithm
 * You can read about it at {@link "https://en.wikipedia.org/wiki/Shunting-yard_algorithm#:~:text=In%20computer%20science%2C%20the%20shunting,abstract%20syntax%20tree%20(AST)."}
 */
@SuppressWarnings("ClassWithTooManyDependents")
public class ShuntingYard {

    private final Deque<Double> operands = new ArrayDeque<>();
    private final Deque<BinaryOperator> operators = new ArrayDeque<>();

    /**
     * This API calculates expression that is in shunting yard.
     */
    private void popAllOperators() {
        while (!operators.isEmpty()) {
            calculatePreviousOperator();
        }
    }

    public Optional<Double> result() {
        popAllOperators();
        return Optional.ofNullable(operands.poll());
    }

    public void pushBinaryOperator(BinaryOperator operator) {
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
        Optional<Double> secondOperand = Optional.ofNullable(operands.pollLast());
        Optional<Double> firstOperand = Optional.ofNullable(operands.pollLast());
        if (firstOperand.isPresent() && secondOperand.isPresent()) {
            this.operands.addLast(Objects.requireNonNull(operator)
                                         .calculate(firstOperand.get(), secondOperand.get()));
        }
    }

    public void pushOperand(double number) {
        operands.addLast(number);
    }

}
