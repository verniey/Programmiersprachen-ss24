package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;

import java.math.BigDecimal;

public class ModulusOperation extends AbstractCheckedOperation {
    @Override
    protected void performOperation(CalculatorContext context) {
        Operand<?> b = context.getDataStack().pop();
        Operand<?> a = context.getDataStack().pop();
        Operand<?> result;

        if (b.getValue() instanceof Integer && ((Integer) b.getValue() == 0)) {
            result = new Operand<>("", String.class);
        } else if (b.getValue() instanceof BigDecimal) {
            result = new Operand<>("", String.class); // Modulus for floating-point numbers results in an empty string
        } else if (a.getValue() instanceof Integer && b.getValue() instanceof Integer) {
            int remainder = (Integer) a.getValue() % (Integer) b.getValue();
            result = new Operand<>(remainder, Integer.class);
        } else {
            result = new Operand<>("", String.class);
        }

        context.getDataStack().push(result);
    }

    @Override
    protected int getRequiredStackSize() {
        return 2; // This operation requires two elements on the stack
    }
}
