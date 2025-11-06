package edu.ntnu.iir.bidata.ui;

public class Output {
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";

  public static void clear() {
    for (int i = 0; i < 25; i++) {
      System.out.print("\n");
    }
  }

  public static void line() {
    redln("--------------------------------------------------------------");
  }

  public static void redln(String text) {
    System.out.println(ANSI_RED + text + ANSI_RESET);
  }

  public static void red(String text) {
    System.out.print(ANSI_RED + text + ANSI_RESET);
  }
}
