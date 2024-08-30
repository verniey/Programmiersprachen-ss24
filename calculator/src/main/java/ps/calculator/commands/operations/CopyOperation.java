package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;



public class CopyOperation implements Operation {
    @Override
    public void execute(CalculatorContext context) {
        // Debug: Print the stack before execution
        System.out.println("Stack before CopyOperation: " + context.getDataStack());

        // Check if stack has at least one element
        if (context.getDataStack().isEmpty()) {
            return; // Do nothing if the stack is empty
        }

        // Pop the index from the stack
        Operand<?> indexOperand = context.getDataStack().pop();

        // Debug: Print the popped index
        System.out.println("Popped index operand: " + indexOperand.getValue());

        // Verify if the popped value is an integer
        if (!(indexOperand.getValue() instanceof Integer)) {
            // Push back if not an integer
            context.getDataStack().push(indexOperand);

            // Debug: Print reason for skipping
            System.out.println("Index is not an integer, pushing back onto stack: " + context.getDataStack());
            return;
        }

        int index = (Integer) indexOperand.getValue();

        // Check if index is in range (1 to stack size)
        if (index < 1 || index > context.getDataStack().size()) {
            // Push the index back onto the stack if it's out of range
            context.getDataStack().push(indexOperand);

            // Debug: Print reason for skipping
            System.out.println("Index is out of range, pushing back onto stack: " + context.getDataStack());
            return;
        }

        // Perform the copy operation: get element from stack using the index
        Operand<?> elementToCopy = context.getDataStack().get(context.getDataStack().size() - index);
        context.getDataStack().push(elementToCopy);

        // Debug: Print the stack after execution
        System.out.println("Stack after CopyOperation: " + context.getDataStack());
    }
}
