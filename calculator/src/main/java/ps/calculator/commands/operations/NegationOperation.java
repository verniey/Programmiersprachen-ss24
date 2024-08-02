package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;

import java.math.BigDecimal;

public class NegationOperation extends AbstractCheckedOperation {
    @Override
    protected void performOperation(CalculatorContext context) {
        Operand<?> operand = context.getDataStack().pop();

        if (operand.getValue() instanceof Integer) {
            // Negate integer
            Integer negatedValue = -((Integer) operand.getValue());
            context.getDataStack().push(new Operand<>(negatedValue, Integer.class));
        } else if (operand.getValue() instanceof BigDecimal) {
            // Negate floating-point number
            BigDecimal negatedValue = ((BigDecimal) operand.getValue()).negate();
            context.getDataStack().push(new Operand<>(negatedValue, BigDecimal.class));
        } else {
            // Replace non-numeric type with an empty string
            context.getDataStack().push(new Operand<>("", String.class));
        }
    }
}
