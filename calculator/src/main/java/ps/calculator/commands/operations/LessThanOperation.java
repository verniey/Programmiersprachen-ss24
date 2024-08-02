package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;

import java.math.BigDecimal;

public class LessThanOperation extends AbstractCheckedOperation {
    @Override
    protected void performOperation(CalculatorContext context) {
        Operand<?> b = context.getDataStack().pop();
        Operand<?> a = context.getDataStack().pop();
        boolean result;

        if (a.getValue() instanceof BigDecimal && b.getValue() instanceof BigDecimal) {
            result = ((BigDecimal) a.getValue()).compareTo((BigDecimal) b.getValue()) < 0;
        } else if (a.getValue() instanceof Integer && b.getValue() instanceof Integer) {
            result = ((Integer) a.getValue()).compareTo((Integer) b.getValue()) < 0;
        } else if (a.getValue() instanceof String && b.getValue() instanceof String) {
            result = ((String) a.getValue()).compareTo((String) b.getValue()) < 0;
        } else {
            throw new IllegalArgumentException("Cannot compare different or unsupported types");
        }

        context.getDataStack().push(new Operand<>(result ? 1 : 0, Integer.class));
    }
}
