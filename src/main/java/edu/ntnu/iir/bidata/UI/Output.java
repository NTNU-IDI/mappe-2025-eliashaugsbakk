package main.java.edu.ntnu.iir.bidata.UI;

public class Output {
    public void clear() {
        String output = "";
        int lines = 10;
        for (int i = 0; i < lines; i++) {
            System.out.println("\n");
        }
        line();
    }
    public void line() {
        red("---------------------------------------------");
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public void reset() {
        System.out.print(ANSI_RESET);
    }
    public static final String ANSI_RED = "\u001B[31m";
    public void red(String text) {
        System.out.println(ANSI_RED
                + text
                + ANSI_RESET);
    }
}
