package edu.ntnu.iir.bidata.utils;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides functionality to sort a list of {@link DiaryEntry} objects
 * based on a selected property, such as rating or time created.
 */
public class DiarySort {

  /**
   * Sorts a collection of {@link DiaryEntry} objects into a new list using the provided comparator.
   * This method can sort the entries based on any property defined by the comparator.
   *
   * @param originalCollection the collection of diary entries to sort
   * @param comparator the comparator defining the sorting order
   * @return a new list containing the sorted diary entries
   */

  public static List<DiaryEntry> sort(
      Collection<DiaryEntry> originalCollection, Comparator<DiaryEntry> comparator) {
    return originalCollection.stream()
        .sorted(comparator)
        .collect(Collectors.toCollection(ArrayList::new));
  }
}
