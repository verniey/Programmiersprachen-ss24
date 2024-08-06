package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;

import java.math.BigDecimal;

public class MultiplyOperation extends AbstractCheckedOperation {
    @Override
    protected void performOperation(CalculatorContext context) {
        Operand<?> b = context.getDataStack().pop();
        Operand<?> a = context.getDataStack().pop();
        Operand<?> result;

        if (a.getValue() instanceof Integer && b.getValue() instanceof Integer) {
            int product = (Integer) a.getValue() * (Integer) b.getValue();
            result = new Operand<>(product, Integer.class);
        } else if (a.getValue() instanceof BigDecimal && b.getValue() instanceof BigDecimal) {
            BigDecimal product = ((BigDecimal) a.getValue()).multiply((BigDecimal) b.getValue());
            result = new Operand<>(product, BigDecimal.class);
        } else if (a.getValue() instanceof Integer && b.getValue() instanceof BigDecimal) {
            BigDecimal product = new BigDecimal((Integer) a.getValue()).multiply((BigDecimal) b.getValue());
            result = new Operand<>(product, BigDecimal.class);
        } else if (a.getValue() instanceof BigDecimal && b.getValue() instanceof Integer) {
            BigDecimal product = ((BigDecimal) a.getValue()).multiply(new BigDecimal((Integer) b.getValue()));
            result = new Operand<>(product, BigDecimal.class);
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
