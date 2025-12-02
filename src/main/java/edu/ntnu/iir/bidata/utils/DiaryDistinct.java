package edu.ntnu.iir.bidata.utils;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Methods to return the distinct String properties from a collection of
 * {@link DiaryEntry} objects.
 *
 * <p>Uses {@link java.util.stream.Stream} to efficiently find distinct Strings.
 *   {@link Function} is used to allow different {@link DiaryEntry} variables.
 *   Sorts the list by alphabetical order.
 */
public class DiaryDistinct {

  /**
   * Returns a sorted list of distinct values extracted from a collection of diary entries.
   * The mapper function is used to extract a specific String property from each DiaryEntry.
   * The resulting values are then sorted in alphabetical order.
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
