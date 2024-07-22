package ps.calculator;

public class OutputStream {
    private StringBuilder output;

    public OutputStream() {
        this.output = new StringBuilder();
    }

    public void write(Object value) {
        output.append(value.toString()).append(" ");
    }

    public String getOutput() {
        return output.toString().trim();
    }

    public void clear() {
        output.setLength(0);
    }

    public void print() {
        System.out.println(getOutput());
        clear();
    }
}
