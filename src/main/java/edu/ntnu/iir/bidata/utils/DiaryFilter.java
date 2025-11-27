package edu.ntnu.iir.bidata.utils;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Methods to return a filtered list of DiaryEntries by a specific object variable
 * from a collection of DiaryEntries.
 */
public class DiaryFilter {
  /**
   * Filters the inputted list of diary entries by the specified author.
   *
   * @param originalCollection the unfiltered collection of entries
   * @param author the author whose diary entries are to be retrieved.
   * @return a list of entries with the specified author
   */
  public static List<DiaryEntry> filterByAuthor(
      Collection<DiaryEntry> originalCollection, String author) {
    return originalCollection.stream()
        .filter(entry -> entry.getAuthor().equals(author))
        .collect(Collectors.toList());
  }


  /**
   * Filters the list of inputted diary entries by the specified activity.
   *
   * @param originalCollection the unfiltered collection of entries
   * @param activity the activity to filter entries by.
   * @return a list of entries with the specified activity
   */
  public static List<DiaryEntry> filterByActivity(
      Collection<DiaryEntry> originalCollection, String activity) {
    return originalCollection.stream()
        .filter(entry -> entry.getActivity().equals(activity))
        .collect(Collectors.toList());
  }

  /**
   * Filters the inputted list of diary entries by the specified destination.
   *
   * @param originalCollection the unfiltered collection of entries
   * @param destination the destination to filter entries by.
   * @return a list of entries with the specified destination
   */
  public static List<DiaryEntry> filterByDestination(
      Collection<DiaryEntry> originalCollection, String destination) {
    return originalCollection.stream()
        .filter(entry -> entry.getDestination().equals(destination))
        .collect(Collectors.toList());
  }

  /**
   * Filters the inputted list of diary entries by time interval.
   *
   * @param originalCollection the unfiltered collection of entries
   * @param timeStart the creation time from witch to allow entries through
   * @param timeStop the creation time to which entries are allowed
   * @return a list of entries within the specified time interval
   */
  public static List<DiaryEntry> filterByTimeCreated(
      Collection<DiaryEntry> originalCollection, LocalDateTime timeStart,
      LocalDateTime timeStop) {
    return originalCollection.stream()
        .filter(entry -> entry.getTimeWritten().isAfter(timeStart)
        && entry.getTimeWritten().isBefore(timeStop))
        .collect(Collectors.toList());
  }

  /**
   * Filters the inputted list of diary entries by looking for matching
   * text in the main text of the diary entry.
   *
   * @param originalCollection the unfiltered collection
   * @param searchTerm the term to filter by
   * @return the filered list
   */
  public static List<DiaryEntry> filterByContent(
      Collection<DiaryEntry> originalCollection, String searchTerm) {
    // normalize the searchTerm
    String normasizedSearchTerm = searchTerm.toLowerCase().replaceAll("\\s+", "");

    return originalCollection.stream().filter(
        entry -> entry.getText().toLowerCase()
            .replaceAll("\\s+", "")
            .contains(normasizedSearchTerm))
            .collect(Collectors.toList());
  }
}
