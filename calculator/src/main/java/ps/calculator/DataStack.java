package ps.calculator;

import ps.calculator.commands.operands.Operand;
import java.util.Stack;

public class DataStack {
    private final Stack<Operand<?>> stack;

    public DataStack() {
        this.stack = new Stack<>();
    }

    public void push(Operand<?> item) {
        stack.push(item);
    }

    public Operand<?> pop() {
        return stack.pop();
    }

    public Operand<?> peek() {
        return stack.peek();
    }

    public int size() {
        return stack.size();
    }

    public void clear() {
        stack.clear();
    }

    public Stack<Operand<?>> getStack() {
        return stack;
    }
}
