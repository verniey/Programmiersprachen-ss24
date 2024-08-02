package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;

import java.math.BigDecimal;

public class NullCheckOperation extends AbstractCheckedOperation {
    private static final BigDecimal EPSILON = new BigDecimal("0.000001"); // Epsilon value

    @Override
    protected void performOperation(CalculatorContext context) {
        Operand<?> operand = context.getDataStack().pop();
        boolean isNull;

        if (operand.getValue() instanceof String) {
            // Check for empty string
            isNull = ((String) operand.getValue()).isEmpty();
        } else if (operand.getValue() instanceof Integer) {
            // Check for integer zero
            isNull = ((Integer) operand.getValue()) == 0;
        } else if (operand.getValue() instanceof BigDecimal) {
            // Check for floating-point near zero (within epsilon range)
            BigDecimal value = (BigDecimal) operand.getValue();
            isNull = (value.compareTo(BigDecimal.ZERO) == 0 ||
                    value.abs().compareTo(EPSILON) <= 0);
        } else {
            // Treat unsupported types as non-null
            isNull = false;
        }

        // Push 1 if null-like, otherwise 0
        context.getDataStack().push(new Operand<>(isNull ? 1 : 0, Integer.class));
    }
}
