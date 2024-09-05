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
        registers.put('n', new Operand<>("# ((5! + 4$ 3!@ ) (2$) # 5 = 1+$ @ ) 0 3! @ 3!3$ /\"", String.class));
        // Mean of list
        registers.put('m', new Operand<>("# ((3! 6 +! + 3!3$ 1+ 3!3$ 4!@ ) (3$2$) 5! 8! = 1+$ @ ) 0 0.0 4! @ 3!3$ /", String.class));
        // Variance of list
        registers.put('v', new Operand<>("m@ # 1- ((3! 7 +! 7! - 2! * + 3!3$ 1+ 3!3$ 4!@ ) (3$2$) 5! 8! = 1+$ @ ) 0 0.0 4! @ 3!3$ / 2$", String.class));
        // Find minimum of list, appends it to the end
        registers.put('w', new Operand<>("# ((3! 6 +! 3!3! > 1+ $ 3!3$ 1+ 3!3$ 4!@ ) (3$2$) 5! 8! = 1+$ @ ) 1 5! 4! @ 2$", String.class));
        // Finds minimum of list, deletes it from the list
        registers.put('W', new Operand<>("# ((3! 6 +! 3!3! > 1+ $ 3!3$ 1+ 3!3$ 4!@ ) (3$2$) 5! 8! = 1+$ @ ) 1 5! 4! @ 2$ d@", String.class));
        // Helper: Finds the minimum of a list and appends it to the end -> [1 2 3 4 5] -> [1 2 3 4 5 1]
        registers.put('t', new Operand<>("((3! 8 + 6! +  ! 3!3! > 1+ $ 3!3$ 1+ 3!3$ 4!@ ) (3$2$) 5! 8! 10 + ! 9! - = 1+$ @ ) 1 4! 7 + ! 4! @ 3!3$", String.class));
        // Loop that sorts the list, then calculates the median
        registers.put('l', new Operand<>("# ((t@ 1+ 3!3$ d@ 3!3$ 2! 3 + !@ ) (2! 2 + $) 4! 2! 7 +! = 1+$ @ ) 0 3! @ 2! 2+ $ (2! 2/ 4+ 2! ! 3!3$ ! + 2/)(2! 2/ 3+ !) 4! 2 % 1+$@ 2$", String.class));
        // Helper: Deletes element of list based on last number -> [1 2 3 4 5 5] -> [1 2 3 4]
        registers.put('d', new Operand<>("(() (# 4! - 1 - 2+ $ 3$) # 6! - 2+ ! 5! = 1+ $@ 3!3$ 1+ 3!3$ 4!@ ) 1 4! 4! @ 1$ 1$", String.class));

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
