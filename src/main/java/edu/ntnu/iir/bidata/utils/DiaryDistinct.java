package edu.ntnu.iir.bidata.utils;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Methods to return the distinct object variables from a collection of DiaryEntries.
 */
public class DiaryDistinct {

  /**
   * Returns a sorted list of distinct values extracted from a collection of diary entries.
   * The mapper function is used to extract a specific String property from each DiaryEntry.
   * The resulting values are then deduplicated and sorted in natural order.
   *
   * @param diaryEntryCollection the collection of DiaryEntry objects to extract values from
   * @param mapper a Function that extracts the desired String property from a DiaryEntry
   * @return a List of distinct, sorted String values extracted from the collection
   */
  public static List<String> getDistinct(
      Collection<DiaryEntry> diaryEntryCollection,
      Function<DiaryEntry, String> mapper) {
    return diaryEntryCollection.stream()
        .map(mapper)
        .distinct()
        .sorted()
        .collect(Collectors.toList());
  }
}
