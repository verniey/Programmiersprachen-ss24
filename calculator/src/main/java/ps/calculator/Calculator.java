package ps.calculator;

import ps.calculator.commands.Command;
import ps.calculator.commands.operands.Operand;

public class Calculator {
    private CalculatorContext context;
    private InputStream inputStream;
    private OutputStream outputStream;
    public Calculator() {
        this.context = new CalculatorContext();
        this.inputStream = new InputStream();
        this.outputStream = new OutputStream();
        // initialize();
    }

    private void initialize() {
        // TODO: Fix initialization with the welcome message and a number in register 'a'
        Operand<?> initialContent = context.getRegisterSet().getRegisterValue('a');
        if (initialContent != null && initialContent.getValue() instanceof String) {
            String initialCommands = (String) initialContent.getValue();
            context.getCommandStream().addCommands(initialCommands);
        }
    }

    public void execute() {
        while (true) {
            // Output the entire stack using the output stream
            for (Operand<?> operand : context.getDataStack().getStack()) {
                outputStream.write(operand.getValue());
            }
            outputStream.print();

            // TODO: Should be register a
            System.out.println("Enter your input (type 'exit' to quit):");
            String input = inputStream.readLine();
            context.setOperationMode(0);
            if (input == null || input.equalsIgnoreCase("exit")) {
                break;
            }

            context.getCommandStream().addCommands(input);

            while (context.getCommandStream().hasNext()) {
                Command command = context.getCommandStream().getNextCommand();
                command.execute(context);
            }
        }
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.execute();
    }
}
