package io.javaclasses.mathCalculator.math;

import java.util.Stack;

/**
 * This is implementation of shunting yard algorithm
 * You can read about at {@link "https://en.wikipedia.org/wiki/Shunting-yard_algorithm#:~:text=In%20computer%20science%2C%20the%20shunting,abstract%20syntax%20tree%20(AST)."}
 *
 */
public class ShuntingYard {
    private final Stack<Double> operands = new Stack<>();
    private final Stack<BinaryOperator> operators = new Stack<>();

    public void pushBinaryOperator(BinaryOperator operation) {
        this.operators.push(operation);
    }

    public void pushNumber(double number) {
        if (operators.size() >= 2) {
            BinaryOperator operation = operators.pop();
            BinaryOperator previousOperation = operators.peek();
            if (operation.compareTo(previousOperation) < 0) {
                this.operators.push(operation);
                this.operands.push(number);
            } else {
                double firstOperand = operands.pop();
                //calculate(firstOperand, secondOperand, operation);
                this.operands.push(operation.calculate(firstOperand, number));
            }
        } else
            this.operands.push(number);

    }

    public Double popAllOperators() {
        while (!operators.isEmpty()){
            double secondOperand = operands.pop();
            double firstOperand = operands.pop();
            BinaryOperator currentBinaryOperator = operators.pop();
            this.operands.push(currentBinaryOperator.calculate(firstOperand,secondOperand));
        }
        return operands.peek();
    }
}
