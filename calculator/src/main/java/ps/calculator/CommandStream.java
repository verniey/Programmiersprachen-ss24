package ps.calculator;

import ps.calculator.commands.Command;

import java.util.LinkedList;
import java.util.Queue;

public class CommandStream {
    private Queue<Command> commands;

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

    public boolean hasNext() {
        return !commands.isEmpty();
    }

    public void reset() {
        commands.clear();
    }
}
