package ps.calculator.commands;

import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;
import ps.calculator.commands.operations.AddOperation;
import ps.calculator.commands.operations.Operation;
import ps.calculator.commands.operations.SubtractOperation;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.lang.Character.isDigit;

public class Command {

    private final char c;

    public Command(char c) {
        this.c = c;
    }

    public void execute(CalculatorContext context) {
        if (context.getOperationMode() == 0) {
            executionMode(context);
        } else if (context.getOperationMode() == -1) {
            integerConstruction(context);
        } else if (context.getOperationMode() <= -2) {
            floatConstruction(context);
        }
    }

    private void integerConstruction(CalculatorContext context) {
        if (isDigit(c)) {
            // get the top entry from the stack, multiply by 10 and add the new digit
            Operand<?> topEntry = context.getDataStack().pop();
            int topValue = (Integer) topEntry.getValue();
            context.getDataStack().push(new Operand<>(topValue * 10 + Integer.parseInt(String.valueOf(this.c)), Integer.class));
        } else if (c == '.') {
            Operand<?> topEntry = context.getDataStack().pop();
            if (topEntry.getValue() instanceof Integer) {
                int topValue = (Integer) topEntry.getValue();
                context.getDataStack().push(new Operand<>(BigDecimal.valueOf(topValue), BigDecimal.class));
            }
            context.setOperationMode(-2);
        } else {
            context.setOperationMode(0);
        }
    }

    private void executionMode(CalculatorContext context) {
        if (isDigit(c)) {
            context.getDataStack().push(new Operand<>(Integer.parseInt(String.valueOf(this.c)), Integer.class));
            context.setOperationMode(-1);
        } else if (c == '.') {
            Operand<?> integerOperand = context.getDataStack().pop();
            if (integerOperand.getValue() instanceof Integer) {
                context.getDataStack().push(new Operand<>(BigDecimal.ZERO, BigDecimal.class));
            }
            context.setOperationMode(-2);
        } else if (isRegister(c)) {
            Operand<?> registerValue = context.getRegisterSet().getRegisterValue(c);
            if (registerValue != null) {
                context.getDataStack().push(registerValue);
            }
        } else if (isOperation(c)) {
            executeOperation(context);
        }
    }

    private void floatConstruction(CalculatorContext context) {
        if (Character.isDigit(c)) {
            int m = context.getOperationMode();
            Operand<?> topEntry = context.getDataStack().pop();

            // Get the floating point value of the digit character
            BigDecimal digitValue = new BigDecimal(Character.getNumericValue(c));

            // Compute 10^(m+1)
            BigDecimal factor;
            if (m + 1 >= 0) {
                factor = BigDecimal.TEN.pow(m + 1);
            } else {
                // For negative exponents, compute 10^(m+1) using division
                factor = BigDecimal.ONE.divide(BigDecimal.TEN.pow(-(m + 1)), MathContext.DECIMAL128);
            }

            // Get the value of the top entry and perform the calculation
            BigDecimal topValue = new BigDecimal(topEntry.getValue().toString());
            BigDecimal newValue = topValue.add(digitValue.multiply(factor));

            // Push the new value back onto the stack
            context.getDataStack().push(new Operand<>(newValue, BigDecimal.class));

            // Decrease the operation mode
            context.decreaseOperationMode();
        } else if (c == '.') {
            context.getDataStack().push(new Operand<>(BigDecimal.ZERO, BigDecimal.class));
            context.setOperationMode(-2);
        } else {
            context.setOperationMode(0);
        }
    }

    private void executeOperation(CalculatorContext context) {
        switch (c) {
            case '+':
                Operation addOperation = new AddOperation();
                addOperation.execute(context);
                break;
            case '-':
                Operation subtractOperation = new SubtractOperation();
                subtractOperation.execute(context);
                break;
            default:
                context.setOperationMode(0);
                break;
        }
    }

    private boolean isOperation(char c) {
        return "+-*/%&|_~!?$@\\#=<>".indexOf(c) >= 0;
    }

    private boolean isRegister(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }
}
