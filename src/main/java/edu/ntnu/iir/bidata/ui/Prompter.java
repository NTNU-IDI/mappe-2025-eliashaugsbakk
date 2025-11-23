package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
   * Prints the string in red color.
   *
   * @param string the string to print in red
   */
  public void printlnRed(String string) {
    println(formatter.redString(string));
  }

  /**
   * Shows a message in a framed box and waits for the user to press ENTER.
   *
   * @param message the message to display
   */
  public void message(String message) {
    println(formatter.redString("X=========================X"));
    println(message);
    println(formatter.redString("X=========================X"));
    print("Hit ENTER to continue...");
    sc.nextLine();
  }

  /**
   * Shows a warning in a framed box and waits for the user to press ENTER.
   *
   * @param message the warning text to display
   */
  public void warning(String message) {
    println(formatter.redString("""
        !=============================!
        %s
        !=============================!
        """).formatted(message));
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
   * Prints out an indexed list for the user to choose an option from. Eather by typing the index
   * or by writing the option.
   * The user can also create a new option, i.e., write something that is not an option. This
   * will also return a valid result. This makes it easy for the user to choose diary entry fields
   * and also use previous ones. This lets the user avoid unnecessary duplicates
   * if they don't want the distinction. (i.e., Bathing and Swimming)
   *
   * @param message the prompt the user gets when choosing a response
   * @param options the options the user can choose from (if they want to)
   * @return the String the user has chocen
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
   * Lets the user create a LocalDateTime object.
   *
   * @param prompt the prompt to give the user
   * @return the time chosen by the user
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
   * Pints out a list of diary entries. Makes it easy for the user to get an overview over
   * all the diary entries in a list.
   *
   * @param entries the entries to print
   */
  public void printListOfEntries(List<DiaryEntry> entries) {
    println(formatter.formatDiaryEntryList(entries));
    prompt("Hit return to continue");
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
