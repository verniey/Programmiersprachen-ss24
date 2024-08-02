package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;

import java.math.BigDecimal;

public class DivideOperation extends AbstractCheckedOperation {
    private static final BigDecimal EPSILON = new BigDecimal("0.000001");

    @Override
    protected void performOperation(CalculatorContext context) {
        Operand<?> b = context.getDataStack().pop();
        Operand<?> a = context.getDataStack().pop();
        Operand<?> result;

        if (b.getValue() instanceof Integer && ((Integer) b.getValue() == 0)) {
            result = new Operand<>("", String.class);
        } else if (b.getValue() instanceof BigDecimal && ((BigDecimal) b.getValue()).abs().compareTo(EPSILON) <= 0) {
            result = new Operand<>("", String.class);
        } else if (a.getValue() instanceof Integer && b.getValue() instanceof Integer) {
            int quotient = (Integer) a.getValue() / (Integer) b.getValue();
            result = new Operand<>(quotient, Integer.class);
        } else if (a.getValue() instanceof BigDecimal && b.getValue() instanceof BigDecimal) {
            BigDecimal quotient = ((BigDecimal) a.getValue()).divide((BigDecimal) b.getValue(), BigDecimal.ROUND_HALF_UP);
            result = new Operand<>(quotient, BigDecimal.class);
        } else if (a.getValue() instanceof Integer && b.getValue() instanceof BigDecimal) {
            BigDecimal quotient = new BigDecimal((Integer) a.getValue()).divide((BigDecimal) b.getValue(), BigDecimal.ROUND_HALF_UP);
            result = new Operand<>(quotient, BigDecimal.class);
        } else if (a.getValue() instanceof BigDecimal && b.getValue() instanceof Integer) {
            BigDecimal quotient = ((BigDecimal) a.getValue()).divide(new BigDecimal((Integer) b.getValue()), BigDecimal.ROUND_HALF_UP);
            result = new Operand<>(quotient, BigDecimal.class);
        } else {
            result = new Operand<>("", String.class);
        }

        context.getDataStack().push(result);
    }
}
