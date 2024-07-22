package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;

public abstract class AbstractCheckedOperation implements Operation {
    @Override
    public void execute(CalculatorContext context) {
        if (context.getDataStack().size() < 2) {
            throw new IllegalStateException("The stack must contain at least two elements.");
        }
        performOperation(context);
    }
    protected abstract void performOperation(CalculatorContext context);
}
