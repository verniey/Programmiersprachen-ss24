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
        initialize();
    }

    private void initialize() {
        // Load initial content from register 'a'
        Operand<?> initialContent = context.getRegisterSet().getRegisterValue('a');
        if (initialContent != null && initialContent.getValue() instanceof String) {
            // Display the welcome message from register 'a'
            System.out.println((String) initialContent.getValue());
            // Do not add it to the command stream if it's just a message
            // context.getCommandStream().addCommands((String) initialContent.getValue());
        }
    }


    public void execute() {
        while (true) {
            // Prompt for user input
            System.out.print("> ");  // Indicate user can enter a command

            // Read the user input
            String input = inputStream.readLine();
            context.setOperationMode(0);

            // Exit condition
            if (input == null || input.equalsIgnoreCase("exit")) {
                break;
            }

            // Add the input commands to the command stream
            context.getCommandStream().addCommands(input);

            // Execute commands in the stream
            executeCommands();
        }
    }


    // Provide direct access to context components for testing
    public CalculatorContext getContext() {
        return context;
    }

    private void executeCommands() {
        while (context.getCommandStream().hasNext()) {
            Command command = context.getCommandStream().getNextCommand();
            command.execute(context);
        }
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.execute();
    }
}
