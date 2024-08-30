package ps.calculator.commands.operations;




import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;
import ps.calculator.commands.operations.AbstractCheckedOperation;

public class DeleteOperation extends AbstractCheckedOperation {

    @Override
    protected int getRequiredStackSize() {
        return 1; // Requires at least one element on the stack
    }

    @Override
    protected void performOperation(CalculatorContext context) {
        Operand<?> top = context.getDataStack().pop();
        if (top.getValue() instanceof Integer) {
            int index = (Integer) top.getValue();
            if (index >= 1 && index <= context.getDataStack().size()) {
                int position = context.getDataStack().size() - index;
                context.getDataStack().remove(position);
            }
        } else {
            // No valid integer at top, no action required
        }
    }
}
