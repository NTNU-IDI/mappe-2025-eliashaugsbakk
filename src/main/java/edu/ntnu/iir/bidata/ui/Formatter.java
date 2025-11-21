package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to format something ready for printing to the terminal. Usually a list.
 */
public class Formatter {

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
          .append(i + 1)
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
    sb.append("X----------------------------------------------------"
        + "-----------------------------------------------------X\n");
    sb.append(String.format(
        rowFormat,
        "Rating", "Author", "Title", "Destination", "Activity", "Written"
    ));

    return sb.toString();
  }

  /**
   * Formats the list of diary entries into an indexed list in the form of a String.
   *
   * @param entries the list of diary entries to list
   * @return the String containing the formatted list with indexes
   */
  public String formatDiaryEntryIndexedList(List<DiaryEntry> entries) {
    String rowFormat = "%-6s %-10s %-15s %-20s %-20s %-15s %-16s%n";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < entries.size(); i++) {
      DiaryEntry entry = entries.get(i);
      sb.append(String.format(
          rowFormat,
          i + 1, // the index
          entry.getRating(),
          entry.getAuthor(),
          entry.getTitle(),
          entry.getDestination(),
          entry.getActivity(),
          entry.getTimeWritten().format(formatter)
      ));
    }
    sb.append("------------------------------------------------------"
        + "------------------------------------------------------\n");
    sb.append(String.format(
        rowFormat,
        "Index", "Rating", "Author", "Title", "Destination", "Activity", "Written"
    ));

    return sb.toString();
  }
}
