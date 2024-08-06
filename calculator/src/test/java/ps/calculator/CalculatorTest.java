package ps.calculator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ps.calculator.commands.Command;
import ps.calculator.commands.operands.Operand;

import java.math.BigDecimal;

public class CalculatorTest {
    private Calculator calculator;
    private CalculatorContext context;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
        context = calculator.getContext();
    }

    @Test
    public void testAddition() {
        // Adjust command sequence to avoid unnecessary characters
        context.getCommandStream().addCommands("4 3 +");

        // Debug: Print stack before execution
        System.out.println("Stack before execution: " + context.getDataStack().toString());

        // Execute the commands
        executeCommands();

        // Debug: Print stack after execution
        System.out.println("Stack after execution: " + context.getDataStack());

        // Ensure the stack is not empty
        assertFalse(context.getDataStack().isEmpty(), "Data stack should not be empty after executing commands");

        // Check if the result is correct
        assertEquals(7, context.getDataStack().pop().getValue(), "The result of 4 + 3 should be 7");
    }

    @Test
    public void testSubtraction() {
        context.getCommandStream().addCommands("10 5 -");
        executeCommands();
        assertEquals(5, context.getDataStack().pop().getValue());
    }

    @Test
    public void testMultiplication() {
        context.getCommandStream().addCommands("2 3 *");
        executeCommands();
        assertEquals(6, context.getDataStack().pop().getValue());
    }

    @Test
    public void testDivision() {
        context.getCommandStream().addCommands("10 2 /");
        executeCommands();
        assertEquals(5, context.getDataStack().pop().getValue());
    }

    @Test
    public void testNegation() {
        context.getCommandStream().addCommands("5~");
        executeCommands();
        assertEquals(-5, context.getDataStack().pop().getValue());
    }




    @Test
    public void testNegationWithDecimal() {
        context.getCommandStream().addCommands("3.14~");
        executeCommands();
        assertEquals(new BigDecimal("-3.14"), context.getDataStack().pop().getValue());
    }

    @Test
    public void testLogicalAnd() {
        context.getCommandStream().addCommands("1 0 &");
        executeCommands();
        assertEquals(0, context.getDataStack().pop().getValue());
    }

    @Test
    public void testLogicalOr() {
        context.getCommandStream().addCommands("0 1 |");
        executeCommands();
        assertEquals(1, context.getDataStack().pop().getValue());
    }

    @Test
    public void testComparisonEqual() {
        context.getCommandStream().addCommands("4 4 =");
        executeCommands();
        assertEquals(1, context.getDataStack().pop().getValue());
    }

    @Test
    public void testComparisonLessThan() {
        context.getCommandStream().addCommands("3 4 <");
        executeCommands();
        assertEquals(1, context.getDataStack().pop().getValue());
    }

    @Test
    public void testComparisonGreaterThan() {
        context.getCommandStream().addCommands("5 4 >");
        executeCommands();
        assertEquals(1, context.getDataStack().pop().getValue());
    }

    @Test
    public void testStringHandling() {
        context.getCommandStream().addCommands("(hello)");
        executeCommands();
        assertEquals("hello", context.getDataStack().pop().getValue());
    }

    @Test
    public void testConditionalExecution() {
        context.getCommandStream().addCommands("1 (8) (9~) (4!4$_1+$@)@");
        executeCommands();
        assertEquals(8, context.getDataStack().pop().getValue());
    }

    private void executeCommands() {
        while (context.getCommandStream().hasNext()) {
            Command command = context.getCommandStream().getNextCommand();
            System.out.println("Executing command: " + command);
            command.execute(context);
            printStackAndCommands(); // Add more visibility into each step
        }
    }



    @Test
    public void testCopyOperationValidIndex() {
        context.getCommandStream().addCommands("5 6 7 3 !");
        executeCommands();

        // After executing '3 !', we expect to copy the 3rd element from the top (5)
        // Stack before '!' should be [5, 6, 7, 3]
        // Stack after '!' should be [5, 6, 7, 5]

        assertEquals(5, context.getDataStack().pop().getValue(), "Expected the top element to be a copy of the third element from the top.");
        assertEquals(7, context.getDataStack().pop().getValue(), "Second from the top should be the original second element.");
        assertEquals(6, context.getDataStack().pop().getValue(), "Third from the top should be the original third element.");
        assertEquals(5, context.getDataStack().pop().getValue(), "Fourth from the top should be the original fourth element.");
    }

    @Test
    public void testCopyOperationOutOfRangeIndex() {
        context.getCommandStream().addCommands("5 6 7 4 !");
        executeCommands();

        // After executing '4 !', the stack should remain unchanged because index 4 is out of range
        assertEquals(4, context.getDataStack().pop().getValue(), "The out-of-range index (4) should remain on top of the stack.");
        assertEquals(7, context.getDataStack().pop().getValue(), "Third element (7) should remain unchanged.");
        assertEquals(6, context.getDataStack().pop().getValue(), "Second element (6) should remain unchanged.");
        assertEquals(5, context.getDataStack().pop().getValue(), "First element (5) should remain unchanged.");
    }


    private void printStackAndCommands() {
        System.out.println("Current Stack: " + context.getDataStack());
        System.out.println("Current Commands: " + context.getCommandStream());
    }

}
