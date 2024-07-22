package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;

public class ApplyImmediatelyOperation implements Operation {
    @Override
    public void execute(CalculatorContext context) {
        Operand<?> top = context.getDataStack().pop();
        if (top.getValue() instanceof String content) {
            context.getCommandStream().addCommandsAtFront(content);
        } else {
            context.getDataStack().push(top); // Push back if not a string
        }
    }
}
