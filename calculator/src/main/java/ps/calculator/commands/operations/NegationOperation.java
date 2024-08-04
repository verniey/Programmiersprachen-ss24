package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;
import java.math.BigDecimal;

/**
 * Operation to negate the top value of the stack.
 */
public class NegateOperation extends AbstractCheckedOperation {

    @Override
    protected int getRequiredStackSize() {
        return 1; // Requires at least one element on the stack
    }

    @Override
    protected void performOperation(CalculatorContext context) {
        Operand<?> operand = context.getDataStack().pop();

        if (operand.getValue() instanceof Integer) {
            int value = (Integer) operand.getValue();
            context.getDataStack().push(new Operand<>(-value, Integer.class));
            System.out.println("Negated Integer: " + -value);
        } else if (operand.getValue() instanceof BigDecimal) {
            BigDecimal value = (BigDecimal) operand.getValue();
            context.getDataStack().push(new Operand<>(value.negate(), BigDecimal.class));
            System.out.println("Negated BigDecimal: " + value.negate());
        } else {
            // Replace unsupported types with an empty string
            context.getDataStack().push(new Operand<>("", String.class));
            System.out.println("Replaced with empty string");
        }

        System.out.println("Stack after negation: " + context.getDataStack());
    }
}
