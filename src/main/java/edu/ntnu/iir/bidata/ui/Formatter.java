package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Used to format something ready for printing to the terminal. Usually a list.
 */
public class Formatter {
  private static final String COLOR_RED = "\u001B[31m";
  private static final String COLOR_RESET = "\u001B[0m";
  private static final String CLEAR_SCREEN = "\u001B[H\u001B[2J";

  /**
   * Clears the screen by printing a number of newlines.
   * NOTE: May not work in all terminals.
   *
   * @return the String containing the newlines
   */
  public String clear() {
    return CLEAR_SCREEN;
  }

  /**
   * Returns the string encapsulated by the ANSI codes for red and back to normal.
   *
   * @param string the string to turn red
   * @return the red string
   */
  public String redString(String string) {
    return COLOR_RED + string + COLOR_RESET;
  }

  /**
   * Creates line to print.
   *
   * @param length the length of the line
   * @return the line
   */
  public String line(int length) {
    return "-".repeat(length) + "\n";
  }

  /**
   * Creates line to print, the same length of a message.
   *
   * @param message the message to give the length of the line
   * @return the line
   */
  public String line(String message) {
    return "-".repeat(message.length()) + "\n";
  }

  /**
   * Creates an indexed list of strings.
   *
   * @param list the list to format
   * @return the formatted string
   */
  public String formatStringList(List<String> list) {
    StringBuilder sb = new StringBuilder();
    sb.append(clear());
    for (int i = 0; i < list.size(); i++) {
      sb
          .append(redString(String.valueOf(i + 1)))
          .append(" : ")
          .append(list.get(i))
          .append("\n");
    }
    return sb.toString();
  }

  /**
   * Creates a string representation of a collection of Diary Entries.
   *
   * @param entries list of Diary Entries
   * @return the formatted string
   */
  public String formatDiaryEntryList(List<DiaryEntry> entries) {
    String rowFormat = "%-10s %-15s %-20s %-20s %-15s %-16s%n";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    StringBuilder sb = new StringBuilder();

    sb.append(line(105));

    for (DiaryEntry entry : entries) {
      sb.append(String.format(
          rowFormat,
          entry.getRating(),
          entry.getAuthor(),
          entry.getTitle(),
          entry.getDestination(),
          entry.getActivity(),
          entry.getTimeWritten().format(formatter)
      ));
    }
    sb.append(line(105));
    sb.append(redString(String.format(
        rowFormat,
        "Rating", "Author", "Title", "Destination", "Activity", "Written"
    )));

    return sb.toString();
  }

  /**
   * Formats the list of diary entries into an indexed list in the form of a String.
   *
   * @param entries the list of diary entries to list
   * @return the String containing the formatted list with indexes
   */
  public String formatDiaryEntryIndexedList(List<DiaryEntry> entries) {
    String rowFormat = "%s %-10s %-15s %-20s %-20s %-15s %-16s%n";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    StringBuilder sb = new StringBuilder();

    sb.append(clear());
    sb.append(line(105));

    for (int i = 0; i < entries.size(); i++) {
      DiaryEntry entry = entries.get(i);

      // formatt the index
      String formattedIndex = String.format("%-6s", (i + 1));

      // color the index
      String coloredIndex = redString(formattedIndex);

      sb.append(String.format(
          rowFormat,
          coloredIndex,
          entry.getRating(),
          entry.getAuthor(),
          entry.getTitle(),
          entry.getDestination(),
          entry.getActivity(),
          entry.getTimeWritten().format(formatter)
      ));
    }
    sb.append(line(105));
    sb.append(String.format(
        rowFormat,
        redString("Index"), "Rating", "Author", "Title", "Destination", "Activity", "Written"
    ));

    return sb.toString();
  }
}
