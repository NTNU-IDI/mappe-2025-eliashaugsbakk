package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Helper for console interaction. Provides methods to show messages,
 * warnings, and prompts, and to read user input.
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
   * Prints string to terminal.
   *
   * @param string The string to println
   */
  private void print(String string) {
    System.out.print(string);
  }

  /**
   * Print string to terminal with newline after.
   *
   * @param string The string to print
   */
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
      print(String.format("%3d", line));
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
   * Method to take confirmation from the user when doing something potentially destructing.
   * Usually deleting a Diary Entry or abandoning a collection of entries.
   *
   * @param message the message containing the user's option
   * @return the users choise
   */
  public boolean confirmAction(String message) {
    println("X=========================X");
    println(message);
    println("X=========================X");
    println("Write yes to confirm, no to return");
    String answer = sc.nextLine();
    return answer.trim().equalsIgnoreCase("yes");
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

  /**
   * Lets the user choose an option from a list. Eather by writing
   * the option or picking a number assigned to each option.
   *
   * @param options the list of options to choose from
   * @return the option chosen
   */
  public String chooseFromList(List<String> options) {
    println(formatter.formatStringList(options));

    while (true) {

      int choiceInt;
      String choiceString = "";
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
        } else {
          warning("Invalid input");
        }
      }
    }
  }

  /**
   * Lets the user create a LocalDateTime object.
   *
   * @param prompt the prompt to give the user
   * @return the time chosen by the user
   */
  public LocalDateTime chooseTime(String prompt) {
    while (true) {
      print(prompt);
      println(" (yyyy-MM-dd HH:mm)");
      print(">");
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
      String input = sc.nextLine();

      try {
        return LocalDateTime.parse(input, formatter);
      } catch (DateTimeException e) {
        warning("Invalid date");
      }
    }
  }

  /**
   * Prints out a formatted collection of diary entries.
   *
   * @param entries the entries to format and print
   */
  public void printListOfDiaries(ArrayList<DiaryEntry> entries) {
    message(formatter.formatDiaryEntryList(entries));
  }

  /**
   * Lets the user choose one diary entry out of a list of entries.
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
      String choiceString = "";
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
            choiceEntry = entry;
            return choiceEntry;
          } else {
            warning("Invalid input");
          }
        }
      }
    }
  }
}
