package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;

import java.math.BigDecimal;

/**
 * An operation that checks if the top stack element is "null-like".
 */
public class NullCheckOperation extends AbstractCheckedOperation {

    private static final BigDecimal EPSILON = new BigDecimal("0.000001"); // Epsilon value

    @Override
    protected int getRequiredStackSize() {
        return 1; // This operation only needs one element from the stack
    }

    @Override
    protected void performOperation(CalculatorContext context) {
        Operand<?> operand = context.getDataStack().pop();
        boolean isNull = isNullLike(operand.getValue());

        // Push 1 if null-like, otherwise 0
        context.getDataStack().push(new Operand<>(isNull ? 1 : 0, Integer.class));
    }

    private boolean isNullLike(Object value) {
        if (value == null) {
            return true;
        }

        if (value instanceof String) {
            // Check for an empty string
            return ((String) value).isEmpty();
        } else if (value instanceof Integer) {
            // Check for integer zero
            return (Integer) value == 0;
        } else if (value instanceof BigDecimal) {
            // Check for a floating-point number close to zero (within the epsilon range)
            BigDecimal decimalValue = (BigDecimal) value;
            return decimalValue.compareTo(BigDecimal.ZERO) == 0 ||
                    decimalValue.abs().compareTo(EPSILON) <= 0;
        }
        // Add more conditions for other types if necessary

        return false; // Default case for unsupported types
    }
}
