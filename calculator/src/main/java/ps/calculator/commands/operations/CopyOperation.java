package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;



public class CopyOperation implements Operation {
    @Override
    public void execute(CalculatorContext context) {

        // Check if stack has at least one element
        if (context.getDataStack().isEmpty()) {
            return; // Do nothing if the stack is empty
        }

        // Pop the index from the stack
        Operand<?> indexOperand = context.getDataStack().peek();


        // Verify if the popped value is an integer
        if (!(indexOperand.getValue() instanceof Integer)) {
            // Push back if not an integer
            context.getDataStack().push(indexOperand);

            return;
        }

        int index = (Integer) indexOperand.getValue();

        // Check if index is in range (1 to stack size)
        if (index < 1 || index > context.getDataStack().size()) {
            // Push the index back onto the stack if it's out of range
            context.getDataStack().push(indexOperand);

            return;
        }

        // Perform the copy operation: get element from stack using the index
        Operand<?> elementToCopy = context.getDataStack().get(context.getDataStack().size() - index);
        context.getDataStack().pop();
        context.getDataStack().push(elementToCopy);

    }
}
