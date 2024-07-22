package ps.calculator;

import java.util.Scanner;

public class InputStream {
    private Scanner scanner;

    public InputStream() {
        this.scanner = new Scanner(System.in);
    }

    public String readLine() {
        return scanner.nextLine();
    }
}
