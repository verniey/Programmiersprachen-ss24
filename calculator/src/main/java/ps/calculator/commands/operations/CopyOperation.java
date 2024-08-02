package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;

public class CopyOperation implements Operation {
    @Override
    public void execute(CalculatorContext context) {
        // Pop the top entry, which should be an integer n
        Operand<?> topOperand = context.getDataStack().pop();

        // Check if the top entry is an integer
        if (topOperand.getValue() instanceof Integer) {
            int n = (Integer) topOperand.getValue();
            int stackSize = context.getDataStack().size();

            // Check if n is within the valid range
            if (n > 0 && n <= stackSize) {
                // Get the nth entry from the stack (1-based index)
                Operand<?> operandToCopy = context.getDataStack().get(stackSize - n);

                // Push a copy of the nth entry onto the stack
                context.getDataStack().push(operandToCopy);
            }
        }
    }
}
