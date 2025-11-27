package edu.ntnu.iir.bidata.utils;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Methods to return the distinct object variables from a collection of DiaryEntries.
 */
public class DiaryDistinct {

  /**
   * Returns a list of the unique authors from a collection of Diary Entries.
   *
   * @param originalCollection the collection to get authors from
   * @return the list of unique authors
   */
  public static List<String> getDistinctAuthors(Collection<DiaryEntry> originalCollection) {
    // use TreeSet to ensure uniqueness and alphabetical sorting
    return originalCollection.stream()
        .map(DiaryEntry::getAuthor)
        .distinct()
        .sorted()
        .collect(Collectors.toList());
  }

  /**
   * Returns a list of the unique activities from a collection of Diary Entries.
   *
   * @param originalCollection the collection to get activities from
   * @return the list of unique activities
   */
  public static List<String> getDistinctActivities(Collection<DiaryEntry> originalCollection) {
    return originalCollection.stream()
        .map(DiaryEntry::getActivity)
        .distinct()
        .sorted()
        .collect(Collectors.toList());
  }

  /**
   * Returns a list of the unique destinations from a collection of Diary Entries.
   *
   * @param originalCollection the collection to get destinations from
   * @return the list of unique destinations
   */
  public static List<String> getDistinctDestinations(Collection<DiaryEntry> originalCollection) {
    return originalCollection.stream()
        .map(DiaryEntry::getDestination)
        .distinct()
        .sorted()
        .collect(Collectors.toList());
  }
}
