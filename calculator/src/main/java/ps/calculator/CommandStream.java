package ps.calculator;

import ps.calculator.commands.Command;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class CommandStream {
    private Deque<Command> commands;

    public CommandStream() {
        this.commands = new LinkedList<>();
    }

    public Command getNextCommand() {
        return commands.poll();
    }

    public void addCommands(String commands) {
        for (char c : commands.toCharArray()) {
            this.commands.add(new Command(c));
        }
    }

    public void addCommandsAtFront(String commands) {
        for (int i = commands.length() - 1; i >= 0; i--) {
            char c = commands.charAt(i);
            this.commands.addFirst(new Command(c));
        }
    }

    public boolean hasNext() {
        return !commands.isEmpty();
    }

    public void reset() {
        commands.clear();
    }
}
