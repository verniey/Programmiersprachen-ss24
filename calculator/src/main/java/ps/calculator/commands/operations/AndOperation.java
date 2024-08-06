package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;

public class AndOperation extends AbstractCheckedOperation {
    @Override
    protected void performOperation(CalculatorContext context) {
        Operand<?> operand2 = context.getDataStack().pop();
        Operand<?> operand1 = context.getDataStack().pop();

        if (operand1.getValue() instanceof Integer && operand2.getValue() instanceof Integer) {
            int value1 = (Integer) operand1.getValue();
            int value2 = (Integer) operand2.getValue();
            boolean result = (value1 != 0) && (value2 != 0);
            context.getDataStack().push(new Operand<>(result ? 1 : 0, Integer.class));
        } else {
            // Push an empty string if any operand is not an integer
            context.getDataStack().push(new Operand<>("", String.class));
        }
    }

    @Override
    protected int getRequiredStackSize() {
        return 2; // This operation requires two elements on the stack
    }
}
