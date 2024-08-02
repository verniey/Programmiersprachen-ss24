package ps.calculator;

public class CalculatorContext {
    private CommandStream commandStream;
    private DataStack dataStack;
    private RegisterSet registerSet;
    private int operationMode;
    private OutputStream outputStream; // Add outputStream field


    public CalculatorContext() {
        this.commandStream = new CommandStream();
        this.dataStack = new DataStack();
        this.registerSet = new RegisterSet();
        this.operationMode = 0;
        this.outputStream = new OutputStream(); // Initialize the output stream

    }

    public CommandStream getCommandStream() {
        return commandStream;
    }

    public DataStack getDataStack() {
        return dataStack;
    }

    public RegisterSet getRegisterSet() {
        return registerSet;
    }

    public void setOperationMode(int operationMode) {
        this.operationMode = operationMode;
    }

    public int getOperationMode() {
        return operationMode;
    }

    public void increaseOperationMode() {
        operationMode++;
    }

    public void decreaseOperationMode() {
        operationMode--;
    }

    public void reset() {
        commandStream.reset();
        dataStack.clear();
        registerSet.reset();
        operationMode = 0;
    }

    public OutputStream getOutputStream() {
        return this.outputStream; // Provide a method to access the output stream
    }
}
