package ps.calculator.commands.operations;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;

import java.math.BigDecimal;

public class WriteOutputOperation implements Operation {


    @Override
    public void execute(CalculatorContext context) {
        if (!context.getDataStack().isEmpty()) {
            Operand<?> top = context.getDataStack().pop();
            context.getOutputStream().write(top.getValue());
            context.getOutputStream().print();
        } else {
            System.out.println("Error: Stack is empty.");
        }
    }
}
