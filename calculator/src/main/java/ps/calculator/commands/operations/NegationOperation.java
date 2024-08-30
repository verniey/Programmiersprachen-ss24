package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;

import java.math.BigDecimal;

/**
 * Negation operation: Negates the top entry of the stack if it is a number,
 * or replaces it with an empty string if it's not.
 */
public class NegationOperation extends AbstractCheckedOperation {

    @Override
    protected int getRequiredStackSize() {
        return 1; // Requires at least one element on the stack
    }

    @Override
    protected void performOperation(CalculatorContext context) {
        Operand<?> top = context.getDataStack().pop();
        Object value = top.getValue();

        if (value instanceof Integer) {
            // Negate the integer value
            int negatedValue = -((Integer) value);
            context.getDataStack().push(new Operand<>(negatedValue, Integer.class));
        } else if (value instanceof BigDecimal) {
            // Negate the BigDecimal value
            BigDecimal negatedValue = ((BigDecimal) value).negate();
            context.getDataStack().push(new Operand<>(negatedValue, BigDecimal.class));
        } else {
            // Replace with an empty string if not a number
            context.getDataStack().push(new Operand<>("", String.class));
        }
    }
}
