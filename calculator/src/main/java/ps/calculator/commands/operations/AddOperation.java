package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;

import java.math.BigDecimal;

public class AddOperation extends AbstractCheckedOperation {

    @Override
    protected void performOperation(CalculatorContext context) {
        Operand<?> b = context.getDataStack().pop();
        Operand<?> a = context.getDataStack().pop();
        Operand<?> result;

        if (a.getValue() instanceof Integer && b.getValue() instanceof Integer) {
            int sum = (Integer) a.getValue() + (Integer) b.getValue();
            result = new Operand<>(sum, Integer.class);
        } else if (a.getValue() instanceof BigDecimal && b.getValue() instanceof BigDecimal) {
            BigDecimal sum = ((BigDecimal) a.getValue()).add((BigDecimal) b.getValue());
            result = new Operand<>(sum, BigDecimal.class);
        } else if (a.getValue() instanceof Integer && b.getValue() instanceof BigDecimal) {
            BigDecimal sum = new BigDecimal((Integer) a.getValue()).add((BigDecimal) b.getValue());
            result = new Operand<>(sum, BigDecimal.class);
        } else if (a.getValue() instanceof BigDecimal && b.getValue() instanceof Integer) {
            BigDecimal sum = ((BigDecimal) a.getValue()).add(new BigDecimal((Integer) b.getValue()));
            result = new Operand<>(sum, BigDecimal.class);
        } else if (a.getValue() instanceof String && b.getValue() instanceof String) {
            String concat = a.getValue() + (String) b.getValue();
            result = new Operand<>(concat, String.class);
        } else {
            result = new Operand<>("", String.class); // Push empty string otherwise
        }

        context.getDataStack().push(result);
    }

    @Override
    protected int getRequiredStackSize() {
        return 2; // This operation requires two elements on the stack
    }
}
