package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Used to format information to be ready for printing to the terminal.
 *
 * <p>This includes creating a readable list, with and without an index or adding color. Formatting.
 */
public class Formatter {
  // Defining ANSI codes
  private static final String COLOR_RED = "\u001B[31m";
  private static final String COLOR_GREEN = "\u001b[32m";
  private static final String COLOR_RESET = "\u001B[0m";

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
   * Returns the string encapsulated by the ANSI codes for green and back to normal.
   *
   * @param string the string to turn green
   * @return the green string
   */
  public String greenString(String string) {
    return COLOR_GREEN + string + COLOR_RESET;
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
    for (int i = 0; i < list.size(); i++) {
      sb
          .append(greenString(String.valueOf(i + 1)))
          .append(" : ")
          .append(list.get(i))
          .append("\n");
    }
    return sb.toString();
  }

  /**
   * Formats a single diary entry for display.
   *
   * @param entry the entry to format
   * @return a formatted string representation
   */
  public String formatDiaryEntry(DiaryEntry entry) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    String formattedWritten = entry.getTimeWritten().format(formatter);
    String formattedEdited = entry.getTimeEdited().format(formatter);
    return """
        Written: %s
        Last edit: %s
        
        Author: %s
        Destination: %s
        Activity: %s
        Rating: %.1f
        Title: %s
        
        %s
        """.formatted(formattedWritten, formattedEdited, entry.getAuthor(),
        entry.getDestination(), entry.getActivity(), entry.getRating(),
        entry.getTitle(), entry.getText());
  }

  /**
   * Helper to color the rating of a diary from green at 10, and red at 0. Yellow in between.
   * This color is using RGB values.
   *
   * @param entry the diary entry to color the rating of
   * @param text the text to color
   * @return the colored text
   */
  private String colorRating(DiaryEntry entry, String text) {
    int red = (int) Math.round(255 - entry.getRating() * 25.5);
    int green = (int) Math.round(entry.getRating() * 25.5);
    int blue = 50;
    String colored = "\u001B[38;2;%d;%d;%dm".formatted(red, green, blue);
    return colored + text + COLOR_RESET;
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

      // formatt the rating
      String formattedRating = String.format("%-10s", entry.getRating());

      // color the rating
      String coloredRating = colorRating(entry, formattedRating);

      sb.append(String.format(
          rowFormat,
          coloredRating,
          entry.getAuthor(),
          entry.getTitle(),
          entry.getDestination(),
          entry.getActivity(),
          entry.getTimeWritten().format(formatter)
      ));
    }
    sb.append(line(105));

    sb.append(greenString(String.format(
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
    String rowFormat = "%-10s %-10s %-20s %-20s %-20s %-15s %-16s%n";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    StringBuilder sb = new StringBuilder();

    sb.append(line(105));

    for (int i = 0; i < entries.size(); i++) {
      DiaryEntry entry = entries.get(i);

      // formatt the index and rating
      String formattedIndex = String.format("%-10s", (i + 1));
      String formattedRating = String.format("%-10s", entry.getRating());

      // color the index and rating
      String coloredIndex = redString(formattedIndex);
      String coloredRating = colorRating(entry, formattedRating);

      sb.append(String.format(
          rowFormat,
          coloredIndex,
          coloredRating,
          entry.getAuthor(),
          entry.getTitle(),
          entry.getDestination(),
          entry.getActivity(),
          entry.getTimeWritten().format(formatter)
      ));
    }
    sb.append(line(105));

    // formatt the index
    String formattedIndex = redString(String.format("%-10s", "Index"));

    sb.append(String.format(
        rowFormat,
        formattedIndex,
        "Rating", "Author", "Title", "Destination", "Activity", "Written"
    ));
    return sb.toString();
  }
}
