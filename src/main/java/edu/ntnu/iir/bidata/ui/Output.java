package edu.ntnu.iir.bidata.ui;

import java.util.Scanner;
public class Output {
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";

  public void clear() {
    for (int i = 0; i < 25; i++) {
      System.out.print("\n");
    }
  }

  public void line() {
    redln("--------------------------------------------------------------");
  }

  public void redln(String text) {
    System.out.println(ANSI_RED + text + ANSI_RESET);
  }

  public void red(String text) {
    System.out.print(ANSI_RED + text + ANSI_RESET);
  }

  public String prompt(Scanner sc, String prompt) {
    clear();
    line();
    redln(prompt);
    System.out.print("> ");
    return sc.nextLine();
  }

  public void print(String message) {
    System.out.print(message);
  }

  public void println(String message) {
    System.out.println(message);
  }

  public void message(Scanner sc, String message) {
    red(message);
    sc.nextLine();
  }

}
