package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;

public class StackSizeOperation implements Operation {
    @Override
    public void execute(CalculatorContext context) {
        // Get the current stack size
        int stackSize = context.getDataStack().size();

        // Push the stack size onto the stack as an integer
        context.getDataStack().push(new Operand<>(stackSize, Integer.class));
    }
}
