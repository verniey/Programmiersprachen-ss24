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
        registers.put('a', new Operand<>("(Welcome to the calculator! Enter a command:)", String.class));
        registers.put('m', new Operand<>("# ((5! + 4$ 3!@ ) (2$) # 5 = 1+$ @ ) 0 3! @ 3!3$ /\"", String.class));
        registers.put('n', new Operand<>("# ((3! 6 +! + 3!3$ 1+ 3!3$ 4!@ ) (3$2$) 5! 8! = 1+$ @ ) 0 0.0 4! @ 3!3$ /", String.class));
        registers.put('v', new Operand<>("n@ # 1- ((3! 7 +! 7! - 2! * + 3!3$ 1+ 3!3$ 4!@ ) (3$2$) 5! 8! = 1+$ @ ) 0 0.0 4! @ 3!3$ / 2$", String.class));
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
