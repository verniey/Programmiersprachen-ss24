package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;

import java.math.BigDecimal;

public class SubtractOperation extends AbstractCheckedOperation {
    @Override
    protected void performOperation(CalculatorContext context) {
        Operand<?> b = context.getDataStack().pop();
        Operand<?> a = context.getDataStack().pop();
        Operand<?> result;

        if (a.getValue() instanceof Integer && b.getValue() instanceof Integer) {
            int difference = (Integer) a.getValue() - (Integer) b.getValue();
            result = new Operand<>(difference, Integer.class);
        } else if (a.getValue() instanceof BigDecimal && b.getValue() instanceof BigDecimal) {
            BigDecimal difference = ((BigDecimal) a.getValue()).subtract((BigDecimal) b.getValue());
            result = new Operand<>(difference, BigDecimal.class);
        } else if (a.getValue() instanceof Integer && b.getValue() instanceof BigDecimal) {
            BigDecimal difference = new BigDecimal((Integer) a.getValue()).subtract((BigDecimal) b.getValue());
            result = new Operand<>(difference, BigDecimal.class);
        } else if (a.getValue() instanceof BigDecimal && b.getValue() instanceof Integer) {
            BigDecimal difference = ((BigDecimal) a.getValue()).subtract(new BigDecimal((Integer) b.getValue()));
            result = new Operand<>(difference, BigDecimal.class);
        } else if (a.getValue() instanceof String && b.getValue() instanceof String) {
            result = new Operand<>("", String.class); // Default case for strings
        } else {
            result = new Operand<>("", String.class); // Default case
        }

        context.getDataStack().push(result);
    }
}
