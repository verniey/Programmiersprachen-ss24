package ps.calculator.commands.operands;

import java.math.BigDecimal;

public class Operand<T> {
    private final Object value;
    private final Class<T> type;

    public Operand(T value, Class<T> type) {
        if (!type.equals(String.class) && !type.equals(Integer.class) && !type.equals(BigDecimal.class)) {
            throw new IllegalArgumentException("Unsupported type: " + type);
        }
        this.value = value;
        this.type = type;
    }
    public T getValue() {
        return type.cast(value);
    }

    public Class<T> getType() {
        return type;
    }
}
