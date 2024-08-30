package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;

public class ApplyImmediatelyOperation extends AbstractCheckedOperation {

    @Override
    protected int getRequiredStackSize() {
        return 1; // Requires at least one element on the stack
    }

    @Override
    protected void performOperation(CalculatorContext context) {
        Operand<?> top = context.getDataStack().pop();

        if (top.getValue() instanceof String) {
            String commands = (String) top.getValue();
            // Use addCommandsAtFront to insert the command string at the start of the command stream
            context.getCommandStream().addCommandsAtFront(commands);
        }
    }
}
