package io.javaclasses.mathCalculator.fsm.acceptors;

import io.javaclasses.mathCalculator.IncorrectMathExpressionException;
import io.javaclasses.mathCalculator.fsm.FunctionFiniteStateMachine;
import io.javaclasses.mathCalculator.fsm.FunctionParametersFiniteStateMachine;
import io.javaclasses.mathCalculator.fsm.base.FiniteStateMachine;
import io.javaclasses.mathCalculator.fsm.base.StateAcceptor;
import io.javaclasses.mathCalculator.math.FunctionDataStructure;
import io.javaclasses.mathCalculator.math.ShuntingYard;

import java.text.CharacterIterator;

/**
 * Implementation of {@link StateAcceptor}.
 * It service defines whether the transition from one state to letter state is possible,
 * and if possible move an iterator forward in an inputChain.
 */
public class FunctionStateAcceptor implements StateAcceptor<ShuntingYard> {
    @Override
    public boolean accept(CharacterIterator inputChain, ShuntingYard outputChain) throws IncorrectMathExpressionException {
        FiniteStateMachine<FunctionDataStructure> functionNameFiniteStateMachine =
                new FunctionFiniteStateMachine();
        FunctionDataStructure functionDataStructure = new FunctionDataStructure();
        if (functionNameFiniteStateMachine.run(inputChain, functionDataStructure) == FiniteStateMachine.Status.FINISHED) {

            FunctionParametersFiniteStateMachine functionParametersFSM =
                    new FunctionParametersFiniteStateMachine();

            if (functionParametersFSM.run(inputChain, functionDataStructure) == FiniteStateMachine.Status.FINISHED) {
                outputChain.pushOperand(functionDataStructure.calculate());
                return true;
            }
        }
        return false;
    }

}
