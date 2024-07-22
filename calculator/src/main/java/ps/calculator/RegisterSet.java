package ps.calculator;

import ps.calculator.commands.operands.Operand;

import java.util.HashMap;
import java.util.Map;

public class RegisterSet {
    private Map<Character, Operand<?>> registers;

    public RegisterSet() {
        this.registers = new HashMap<>();
        initializeRegisters();
    }

    private void initializeRegisters() {
        for (char c = 'A'; c <= 'Z'; c++) {
            registers.put(c, null);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            registers.put(c, null);
        }
        // Initialize register 'a' with a welcome message and a number
        registers.put('a', new Operand<>("(Welcome to the calculator!)", String.class));
        registers.put('b', new Operand<>(42, Integer.class));
    }

    public Operand<?> getRegisterValue(char register) {
        return registers.get(register);
    }

    public void setRegisterValue(char register, Operand<?> value) {
        registers.put(register, value);
    }

    public void reset() {
        registers.clear();
        initializeRegisters();
    }
}
