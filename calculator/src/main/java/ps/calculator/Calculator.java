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
        Operand<?> initialContent = context.getRegisterSet().getRegisterValue('a');
        context.getCommandStream().addCommands(initialContent.getValue().toString());
        while (context.getCommandStream().hasNext()) {
            Command command = context.getCommandStream().getNextCommand();
            command.execute(context);
        }
    }

    public void execute() {
        while (true) {
            // Output the entire stack using the output stream
            for (Operand<?> operand : context.getDataStack().getStack()) {
                outputStream.write(operand.getValue());
            }
            outputStream.print();

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
