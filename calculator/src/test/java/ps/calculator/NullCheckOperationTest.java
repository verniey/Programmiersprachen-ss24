package ps.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ps.calculator.CalculatorContext;
import ps.calculator.commands.operands.Operand;
import ps.calculator.commands.operations.NullCheckOperation;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NullCheckOperationTest {

    private CalculatorContext context;

    @BeforeEach
    public void setup() {
        context = new CalculatorContext();
    }

    @Test
    public void testNullCheckOperation() {
        NullCheckOperation nullCheck = new NullCheckOperation();

        // Test empty string
        context.getDataStack().push(new Operand<>("", String.class));
        nullCheck.execute(context);
        assertEquals(1, context.getDataStack().pop().getValue());

        // Test non-empty string
        context.getDataStack().push(new Operand<>("hello", String.class));
        nullCheck.execute(context);
        assertEquals(0, context.getDataStack().pop().getValue());

        // Test zero integer
        context.getDataStack().push(new Operand<>(0, Integer.class));
        nullCheck.execute(context);
        assertEquals(1, context.getDataStack().pop().getValue());

        // Test non-zero integer
        context.getDataStack().push(new Operand<>(5, Integer.class));
        nullCheck.execute(context);
        assertEquals(0, context.getDataStack().pop().getValue());

        // Test zero BigDecimal
        context.getDataStack().push(new Operand<>(BigDecimal.ZERO, BigDecimal.class));
        nullCheck.execute(context);
        assertEquals(1, context.getDataStack().pop().getValue());

        // Test near-zero BigDecimal
        context.getDataStack().push(new Operand<>(new BigDecimal("0.0000001"), BigDecimal.class));
        nullCheck.execute(context);
        assertEquals(1, context.getDataStack().pop().getValue());

        // Test non-zero BigDecimal
        context.getDataStack().push(new Operand<>(new BigDecimal("0.1"), BigDecimal.class));
        nullCheck.execute(context);
        assertEquals(0, context.getDataStack().pop().getValue());
    }
}
