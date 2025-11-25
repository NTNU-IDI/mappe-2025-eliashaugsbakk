package edu.ntnu.iir.bidata.utils;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

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
    List<DiaryEntry> sortedList = new ArrayList<>(entriesToSort);
    sortedList.sort(Comparator.comparing(DiaryEntry::getTimeWritten));
    return sortedList;
  }

  /**
   * Sorts a list of diary entries by the rating it assigned to it.
   *
   * @param entriesToSort the collection of Diary Entries to sort
   * @return a new list sorted by rating
   */
  public static List<DiaryEntry> sortByRating(Collection<DiaryEntry> entriesToSort) {
    List<DiaryEntry> sortedList = new ArrayList<>(entriesToSort);
    sortedList.sort(Comparator.comparing(DiaryEntry::getRating));
    return sortedList;
  }
}
