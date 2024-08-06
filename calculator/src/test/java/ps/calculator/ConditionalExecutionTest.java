package ps.calculator;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ps.calculator.Calculator;
import ps.calculator.commands.operands.Operand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConditionalExecutionTest {
    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testConditionalExecutionTruePath() {
        // Program simulating a true path (stack starts with 1)
        String program = "1 (8) (9~) (4!4$_1+$@)@";

        // Load the program into a register and execute
        calculator.getContext().getRegisterSet().setRegisterValue('H', new Operand<>(program, String.class));
        calculator.getContext().getCommandStream().addCommands("H@");
        calculator.execute();

        // Verify the result: '8' should be on the stack as true path
        Operand<?> result = calculator.getContext().getDataStack().pop();
        assertEquals("8", result.getValue());
    }

    @Test
    public void testConditionalExecutionFalsePath() {
        // Program simulating a false path (stack starts with 0)
        String program = "0 (8) (9~) (4!4$_1+$@)@";

        // Load the program into a register and execute
        calculator.getContext().getRegisterSet().setRegisterValue('H', new Operand<>(program, String.class));
        calculator.getContext().getCommandStream().addCommands("H@");
        calculator.execute();

        // Verify the result: '9~' should be executed and negated to '-9'
        Operand<?> result = calculator.getContext().getDataStack().pop();
        assertEquals("-9", result.getValue());
    }


    // Example Test Case
    public void testAddition() {
        Calculator calculator = new Calculator();
        calculator.getContext().getCommandStream().addCommands("5 7 +");

        // Expected result on the stack is 12
        //calculator.executeCommands();
        //Operand<?> result = calculator.context.getDataStack().peek();
        //assert result.getValue().equals(12) : "Test failed, expected 12 but got " + result.getValue();
    }

}
