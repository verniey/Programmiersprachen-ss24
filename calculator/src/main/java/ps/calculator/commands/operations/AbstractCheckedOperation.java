package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;

/**
 * An abstract operation that checks if the stack has enough elements before execution.
 */
public abstract class AbstractCheckedOperation implements Operation {

    @Override
    public void execute(CalculatorContext context) {
        // Check if the stack has enough elements
        if (context.getDataStack().size() < getRequiredStackSize()) {
            throw new IllegalStateException("The stack must contain at least " + getRequiredStackSize() + " elements.");
        }
        performOperation(context);
    }

    /**
     * Specifies the number of stack elements required by the operation.
     */
    protected abstract int getRequiredStackSize();

    /**
     * Perform the actual operation.
     */
    protected abstract void performOperation(CalculatorContext context);
}
