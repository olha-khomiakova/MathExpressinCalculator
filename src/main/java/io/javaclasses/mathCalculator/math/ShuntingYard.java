package io.javaclasses.mathCalculator.math;

import java.util.*;

/**
 * This is implementation of shunting yard algorithm
 * You can read about at {@link "https://en.wikipedia.org/wiki/Shunting-yard_algorithm#:~:text=In%20computer%20science%2C%20the%20shunting,abstract%20syntax%20tree%20(AST)."}
 *
 */
public class ShuntingYard {
    private Deque<Double> operands = new ArrayDeque<>();
    private Deque<BinaryOperator> operators = new ArrayDeque<>();

    public void pushBinaryOperator(BinaryOperator operation) {
        this.operators.addLast(operation);
    }

    public void pushNumber(double number) {
        if (operators.size() > 1) {
            BinaryOperator operation = operators.pollLast();
            BinaryOperator previousOperation = operators.peekLast();
            if (operation.compareTo(previousOperation)==-1) {
                this.operators.addLast(operation);
                this.operands.addLast(number);
            } else {
                double secondOperand = number;
                double firstOperand = operands.pollLast();
                //calculate(firstOperand, secondOperand, operation);
                this.operands.addLast(operation.calculate(firstOperand,secondOperand));
            }
        } else
            this.operands.addLast(number);

    }

    public Double popAllOperators() {
        while (!operators.isEmpty()){
            double secondOperand = operands.pollLast();
            double firstOperand = operands.pollLast();
            BinaryOperator currentBinaryOperator = operators.pollLast();
            this.operands.addLast(currentBinaryOperator.calculate(firstOperand,secondOperand));
        }
        return operands.peekLast();
    }
}
