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
   * Sorts a list of diary entries by the time it was created. The oldest entry first.
   *
   * @param entriesToSort the collection of Diary Entries to sort
   * @return a new list sorted by time written
   */
  public static List<DiaryEntry> sortByTime(Collection<DiaryEntry> entriesToSort) {
    return entriesToSort.stream()
        .sorted(Comparator.comparing(DiaryEntry::getTimeWritten))
        .collect(Collectors.toList());
  }

  /**
   * Sorts a list of diary entries by the rating it assigned to it.
   *
   * @param entriesToSort the collection of Diary Entries to sort
   * @return a new list sorted by rating
   */
  public static List<DiaryEntry> sortByRating(Collection<DiaryEntry> entriesToSort) {
    return entriesToSort.stream()
        .sorted(Comparator.comparing(DiaryEntry::getRating))
        .collect(Collectors.toList());
  }
}
