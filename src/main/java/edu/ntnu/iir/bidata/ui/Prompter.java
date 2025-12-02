package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Helper for console interaction. Provides methods to show messages and
 * warnings. Also used to take user input using the prompt methods.
 *
 * <p>Everything that gets written to or read from the terminal should go through this class.
 */
public class Prompter {
  private final Scanner sc = new Scanner(System.in);
  private final Formatter formatter;

  /**
   * Constructor to assign formatter.
   *
   * @param formatter to format ready for printing
   */
  public Prompter(Formatter formatter) {
    this.formatter = formatter;
  }

  /**
   * Prints a string to the terminal.
   *
   * @param string The string to print
   */
  private void print(String string) {
    System.out.print(string);
  }

  /**
   * Prints a string to the terminal with a newline after.
   *
   * @param string The string to print
   */
  public void println(String string) {
    System.out.println(string);
  }

  /**
   * Prints the string in green color then a new line.
   *
   * @param string the string to print in green
   */
  public void printlnGreen(String string) {
    println(formatter.greenString(string));
  }

  /**
   * Prints the string in green color.
   *
   * @param string the string to print in green
   */
  public void printGreen(String string) {
    println(formatter.greenString(string));
  }

  /**
   * Shows a message in a framed box and waits for the user to press ENTER.
   *
   * @param message the message to display
   */
  public void message(String message) {
    println(formatter.greenString("X=========================X"));
    println(message);
    println(formatter.greenString("X=========================X"));
    print("Hit ENTER to continue...");
    sc.nextLine();
  }

  /**
   * Shows a warning in a framed box and waits for the user to press ENTER.
   *
   * @param message the warning text to display
   */
  public void warning(String message) {
    println(formatter.redString("!=============================!"));
    println(message);
    println(formatter.redString("!=============================!"));
    prompt("Hit ENTER to continue...");
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
   * Prompts the user for a double.
   *
   * @param message the prompt the user gets when choosing a double
   * @return the double chosen by the user
   */
  public double promptDouble(String message) {
    while (true) {
      println(message);
      print("> ");

      try {
        double choice = sc.nextDouble();
        sc.nextLine(); // consume the rest of the line
        return choice;
      } catch (InputMismatchException e) {
        sc.nextLine(); // consume invalid token
        warning("Input must be a double. Please try again.");
      }
    }
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
      print(String.format("%3d : ", line));
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
   * Asks the user for confirmation before performing a potentially destructive action.
   *
   * @param message the confirmation message to display to the user
   * @return true if the user confirms, false otherwise
   */
  public boolean confirmAction(String message) {
    println("X=========================X");
    println(message);
    println("X=========================X");
    print("Type \"");
    printGreen("yes");
    print("\" to confirm, or hit \"RETURN\" to cancel.\n> ");
    String answer = sc.nextLine();
    return answer.trim().equalsIgnoreCase("yes");
  }

  /**
   * Prompts the user to enter an integer.
   *
   * @param options the prompt message to display to the user
   * @return the integer entered by the user
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

  /**
   * Displays a list of options and prompts the user to select one by index or by typing the
   * name of the option.
   *
   * @param message a message to display before the list of options
   * @param options the list of options to choose from
   * @return the option chosen by the user
   */
  public String chooseFromList(String message, List<String> options) {
    while (true) {

      print("""
        %s
        Select an option by typing the index, or typing the name.
        %s
        >\s""".formatted(message, formatter.formatStringList(options)));


      int choiceInt;
      String choiceString = null;

      if (sc.hasNextInt()) {
        choiceInt = sc.nextInt();
        sc.nextLine(); // to soak up any extra input, after the int

        try {
          choiceString = options.get(choiceInt - 1);
        } catch (IndexOutOfBoundsException e) {
          warning("Not a valid choice");
        }
      } else {
        choiceString = sc.nextLine();
      }
      for (String option : options) {
        if (option.equalsIgnoreCase(choiceString)) {
          return choiceString;
        }
      }
      warning("Invalid input");
    }
  }

  /**
   * Displays a list of options and prompts the user to either select one or write a new one.
   *
   * @param message a message to display before the list of options
   * @param options the list of options to choose from
   * @return the option chosen by the user or the new option they wrote
   */
  public String chooseFromListOrWriteNew(String message, List<String> options) {
    print("""
    %s
    Select an option by typing the index, or typing the name.
    Create a new one by typing a new name.
    %s
    >\s""".formatted(message, formatter.formatStringList(options)));

    while (true) {

      int choiceInt;
      String choiceString;
      if (sc.hasNextInt()) {
        choiceInt = sc.nextInt();
        sc.nextLine(); // to soak up any extra input, after the int

        try {
          choiceString = options.get(choiceInt - 1);
          return choiceString;
        } catch (IndexOutOfBoundsException e) {
          warning("Not a valid choice");
        }
      } else {
        return sc.nextLine();
      }
    }
  }

  /**
   * Prompts the user to enter a date and time.
   *
   * @param prompt the prompt to give the user
   * @return the LocalDateTime object created from the user's input
   */
  public LocalDateTime chooseTime(String prompt) {
    while (true) {
      print(prompt);
      println(" (dd/MM/yyyy HH:mm)");
      print(">");
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
      String input = sc.nextLine();

      try {
        return LocalDateTime.parse(input, dateTimeFormatter);
      } catch (DateTimeException e) {
        warning("Invalid date");
      }
    }
  }

  /**
   * Prints a formatted list of diary entries to terminal.
   *
   * @param entries the list of entries to print
   */
  public void printListOfEntries(List<DiaryEntry> entries) {
    println(formatter.formatDiaryEntryList(entries));
    prompt("Hit return to continue");
  }

  /**
   * Displays a list of diary entries and prompts the user to select one by index or by title.
   *
   * @param entries the list of entries to choose from
   * @return the DiaryEntry the user has chosen
   */
  public DiaryEntry chooseFromListOfEntries(List<DiaryEntry> entries) {
    while (true) {
      println(formatter.formatDiaryEntryIndexedList(entries));
      println("Choose an entry by typing the Index or title");
      print("> ");
      DiaryEntry choiceEntry;
      int choiceInt;
      String choiceString;
      if (sc.hasNextInt()) {
        choiceInt = sc.nextInt();
        sc.nextLine(); // to soak up any extra input, after the int
        try {
          choiceEntry = entries.get(choiceInt - 1);
          return choiceEntry;
        } catch (IndexOutOfBoundsException e) {
          warning("Not a valid choice");
        }
      } else {
        choiceString = sc.nextLine();
        for (DiaryEntry entry : entries) {
          if (entry.getTitle().equalsIgnoreCase(choiceString)) {
            return entry;
          }
        }
        warning("Invalid input");
      }
    }
  }
}
