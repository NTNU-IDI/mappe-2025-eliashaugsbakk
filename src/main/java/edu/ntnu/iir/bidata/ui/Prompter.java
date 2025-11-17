package edu.ntnu.iir.bidata.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Helper for console interaction. Provides methods to show messages,
 * warnings, and prompts, and to read user input.
 */
public class Prompter {
  Scanner sc = new Scanner(System.in);

  private void print(String string) {
    System.out.print(string);
  }

  public void println(String string) {
    System.out.println(string);
  }

  /**
   * Shows a message in a framed box and waits for the user to press ENTER.
   *
   * @param message the message to display
   */
  public void message(String message) {
    println("X=========================X");
    println(message);
    println("X=========================X");
    print("Hit ENTER to continue...");
    sc.nextLine();
  }

  /**
   * Shows a warning in a framed box and waits for the user to press ENTER.
   *
   * @param message the warning text to display
   */
  public void warning(String message) {
    println("!=========================!");
    println(message);
    println("!=========================!");
    print("Hit ENTER to continue...");
    sc.nextLine();
  }

  /**
   * Prompts the user with a question and returns the entered line.
   *
   * @param prompt the prompt text
   * @return the full line entered by the user
   */
  public String prompt(String prompt) {
    println(prompt);
    print("> ");
    return sc.nextLine();
  }

  /**
   * Reads multiple lines of text until the user types "bye" on a line by itself.
   *
   * @param prompt the initial prompt shown before the multiline input
   * @return the collected text (without the terminating "bye" line)
   */
  public String multipleLinePrompt(String prompt) {
    println(prompt);
    StringBuilder text = new StringBuilder();
    println("You may write multiple lines. Type: \"bye\" on an empty line to exit.");
    int line = 0;

    while (true) {
      print(STR."\{String.format("%3d", line)}  ");
      line++;
      String inputText = sc.nextLine();
      if (inputText.equals("bye")) {
        break;
      }
      if (!inputText.isEmpty()) {
        text.append("\n");
      }
      text.append(inputText);
    }
    return text.toString();
  }

  /**
   * Shows options and reads an integer choice, repeating until a valid integer is entered.
   *
   * @param options the text with options to display
   * @return the integer the user entered
   */
  public int promptInt(String options) {
    while (true) {
      println(options);
      print("> ");

      try {
        int choice = sc.nextInt();
        sc.nextLine(); // consume the rest of the line
        return choice;
      } catch (InputMismatchException e) {
        sc.nextLine(); // consume invalid token
        warning("Input must be an integer. Please try again.");
      }
    }
  }
}
