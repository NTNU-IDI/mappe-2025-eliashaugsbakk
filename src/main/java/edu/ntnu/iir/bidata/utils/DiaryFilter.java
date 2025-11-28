package edu.ntnu.iir.bidata.utils;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Methods to return a filtered list of DiaryEntries by a specific object variable
 * from a collection of DiaryEntries.
 */
public class DiaryFilter {

  /**
   * Filters the given collection of diary entries using a custom Predicate.
   *
   * @param originalCollection the collection of DiaryEntry objects to filter
   * @param filter the Predicate that defines the filtering condition
   * @return a List of DiaryEntry objects that satisfy the given Predicate
   */
  public static List<DiaryEntry> filter(
      Collection<DiaryEntry> originalCollection,
      Predicate<DiaryEntry> filter) {
    return originalCollection.stream()
        .filter(filter)
        .collect(Collectors.toList());
  }

  /**
   * Filters the given collection of diary entries by a time interval.
   *
   * @param originalCollection the collection of DiaryEntry objects to filter
   * @param timeStart the start of the time interval (exclusive)
   * @param timeStop the end of the time interval (exclusive)
   * @return a List of DiaryEntry objects whose timeWritten is after timeStart and before timeStop
   */
  public static List<DiaryEntry> filterByTimeInterval(
      Collection<DiaryEntry> originalCollection,
      LocalDateTime timeStart,
      LocalDateTime timeStop) {
    return originalCollection.stream()
        .filter(entry -> entry.getTimeWritten().isAfter(timeStart)
            && entry.getTimeWritten().isBefore(timeStop))
        .collect(Collectors.toList());
  }

  /**
   * Filters the given collection of diary entries by a search term in their text.
   * The search is case-insensitive and ignores whitespace differences.
   *
   * @param originalCollection the collection of DiaryEntry objects to filter
   * @param searchTerm the term to search for in the text of each DiaryEntry
   * @return a List of DiaryEntry objects whose text contains the given search term
   */
  public static List<DiaryEntry> filterByContent(
      Collection<DiaryEntry> originalCollection, String searchTerm) {
    // normalize the searchTerm
    String normalizedSearchTerm = searchTerm.toLowerCase().replaceAll("\\s+", "");

    return originalCollection.stream().filter(
            entry -> entry.getText().toLowerCase()
                .replaceAll("\\s+", "")
                .contains(normalizedSearchTerm))
        .collect(Collectors.toList());
  }
}
