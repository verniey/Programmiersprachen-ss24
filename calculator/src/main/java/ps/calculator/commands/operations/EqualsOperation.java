package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;

import java.math.BigDecimal;

public class EqualsOperation extends AbstractCheckedOperation {
    @Override
    protected void performOperation(CalculatorContext context) {
        Operand<?> a = context.getDataStack().pop();
        Operand<?> b = context.getDataStack().pop();

        context.getDataStack().push(new Operand<>(areEqual(a, b) ? 1 : 0, Integer.class));
    }

    private boolean areEqual(Operand<?> operand1, Operand<?> operand2) {
        if (operand1.getValue() instanceof BigDecimal && operand2.getValue() instanceof BigDecimal) {
            return ((BigDecimal) operand1.getValue()).compareTo((BigDecimal) operand2.getValue()) == 0;
        } else if (operand1.getValue() instanceof Integer && operand2.getValue() instanceof Integer) {
            return operand1.getValue().equals(operand2.getValue());
        } else if (operand1.getValue() instanceof String && operand2.getValue() instanceof String) {
            return operand1.getValue().equals(operand2.getValue());
        } else {
            throw new IllegalArgumentException("Cannot compare different or unsupported types");
        }
    }
}
