package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;

import java.math.BigDecimal;

public class WriteOutputOperation implements Operation {
    @Override
    public void execute(CalculatorContext context) {
        // Pop the top entry from the data stack
        Operand<?> operand = context.getDataStack().pop();
        Object value = operand.getValue();
        String output;

        if (value instanceof String) {
            // Write string directly to output
            output = (String) value;
        } else if (value instanceof Integer) {
            // Convert integer to string
            output = Integer.toString((Integer) value);
        } else if (value instanceof BigDecimal) {
            // Format floating-point number, avoiding unnecessary digits
            BigDecimal number = (BigDecimal) value;
            output = number.stripTrailingZeros().toPlainString();
        } else {
            // Handle unexpected type
            output = "Unsupported type";
        }

        // Write to the output stream
        context.getOutputStream().write(output);
        context.getOutputStream().print();  // Ensure it gets printed immediately
    }
}
