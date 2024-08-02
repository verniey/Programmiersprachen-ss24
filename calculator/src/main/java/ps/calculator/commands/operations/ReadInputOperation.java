package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;

import java.math.BigDecimal;
import java.util.Scanner;

public class ReadInputOperation implements Operation {
    @Override
    public void execute(CalculatorContext context) {
        // Read a line of input from the input stream
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter input: ");
        String inputLine = scanner.nextLine().trim();

        // Attempt to parse as integer
        try {
            int intValue = Integer.parseInt(inputLine);
            context.getDataStack().push(new Operand<>(intValue, Integer.class));
            return;
        } catch (NumberFormatException e) {
            // Not an integer, try next
        }

        // Attempt to parse as floating-point
        try {
            BigDecimal floatValue = new BigDecimal(inputLine);
            context.getDataStack().push(new Operand<>(floatValue, BigDecimal.class));
            return;
        } catch (NumberFormatException e) {
            // Not a floating-point number, treat as string
        }

        // Default to treating as a string
        context.getDataStack().push(new Operand<>(inputLine, String.class));
    }
}
