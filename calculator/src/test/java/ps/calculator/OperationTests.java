package ps.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ps.calculator.commands.operands.Operand;
import ps.calculator.commands.operations.CopyOperation;
import ps.calculator.commands.operations.DeleteOperation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OperationTests {

    private CalculatorContext context;

    @BeforeEach
    public void setup() {
        context = new CalculatorContext();
    }


    @Test
    public void testCopyOperation() {
        CopyOperation copyOp = new CopyOperation();

        // Setup stack [1, 2, 3] (3 is top)
        context.getDataStack().push(new Operand<>(1, Integer.class));
        context.getDataStack().push(new Operand<>(2, Integer.class));
        context.getDataStack().push(new Operand<>(3, Integer.class));

        // Push 2 to indicate we want to copy the second element from the top
        context.getDataStack().push(new Operand<>(2, Integer.class));
        copyOp.execute(context);

        // The stack should now be [1, 2, 3, 2] (2 is copied)
        assertEquals(4, context.getDataStack().size());
        assertEquals(2, context.getDataStack().pop().getValue());

        // If the top of the stack is not a valid integer or out of range
        // Copy should have no effect, let's test with invalid index
        context.getDataStack().push(new Operand<>(4, Integer.class));
        copyOp.execute(context);

        // The stack should remain unchanged [1, 2, 3]
        assertEquals(3, context.getDataStack().size());
        assertEquals(3, context.getDataStack().peek().getValue());
    }

    @Test
    public void testDeleteOperation() {
        DeleteOperation deleteOp = new DeleteOperation();

        // Setup stack [1, 2, 3] (3 is top)
        context.getDataStack().push(new Operand<>(1, Integer.class));
        context.getDataStack().push(new Operand<>(2, Integer.class));
        context.getDataStack().push(new Operand<>(3, Integer.class));

        // Push 2 to indicate we want to delete the second element from the top
        context.getDataStack().push(new Operand<>(2, Integer.class));
        deleteOp.execute(context);

        // The stack should now be [1, 3]
        assertEquals(2, context.getDataStack().size());
        assertEquals(3, context.getDataStack().pop().getValue());
        assertEquals(1, context.getDataStack().pop().getValue());

        // Push invalid index and check stack remains the same
        context.getDataStack().push(new Operand<>(1, Integer.class));
        context.getDataStack().push(new Operand<>(2, Integer.class));
        context.getDataStack().push(new Operand<>(3, Integer.class));
        context.getDataStack().push(new Operand<>(4, Integer.class));
        deleteOp.execute(context);

        // The stack should remain unchanged [1, 2, 3]
        assertEquals(3, context.getDataStack().size());
    }
}
