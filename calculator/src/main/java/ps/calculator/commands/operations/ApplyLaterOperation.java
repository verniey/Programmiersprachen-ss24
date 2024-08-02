package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;

public class ApplyLaterOperation implements Operation {
    @Override
    public void execute(CalculatorContext context) {
        // Pop the top entry from the data stack
        Operand<?> topOperand = context.getDataStack().pop();

        // Check if the top entry is a string
        if (topOperand.getValue() instanceof String) {
            String commandString = (String) topOperand.getValue();

            // Append the string's contents to the end of the command stream
            context.getCommandStream().addCommands(commandString);
        }
    }
}
