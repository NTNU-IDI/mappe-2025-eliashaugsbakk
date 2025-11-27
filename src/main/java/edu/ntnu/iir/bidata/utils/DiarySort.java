package edu.ntnu.iir.bidata.utils;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Method to return a sorted collection of DiaryEntries by an object variable.
 */
public class DiarySort {

  /**
   * Sorts a collection into a new list using the provided comparator.
   *
   * @param originalCollection the original collection of diary entries to sort
   * @param comparator the object variable to sort by
   * @return the sorted list
   */
  public static List<DiaryEntry> sort(
      Collection<DiaryEntry> originalCollection, Comparator<DiaryEntry> comparator) {
    return originalCollection.stream()
        .sorted(comparator)
        .collect(Collectors.toList());
  }
}
