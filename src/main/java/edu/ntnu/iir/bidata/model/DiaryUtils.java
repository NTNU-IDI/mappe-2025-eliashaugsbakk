package edu.ntnu.iir.bidata.model;

import java.time.LocalDateTime;
import java.util.*;

/**
 * General class to do operations on collections of Diary Entries.
 * This mutates the inputted list directly.
 */
public class DiaryUtils {

  /**
   * Sorts a list of diary entries by the time it was created. The oldest entry first.
   *
   * @param entriesToSort the list of Diary Entries to sort
   */
  public static void sortByTime(List<DiaryEntry> entriesToSort) {
    entriesToSort.sort(Comparator.comparing(DiaryEntry::getTimeWritten));
  }

  /**
   * Sorts a list of diary entries by the rating it assigned to it.
   *
   * @param entriesToSort the list of Diary Entries to sort
   */
  public static void sortByRating(List<DiaryEntry> entriesToSort) {
    entriesToSort.sort(Comparator.comparing(DiaryEntry::getRating));
  }

  /**
   * Filters the inputted list of diary entries by the specified author.
   *
   * @param originalCollection the unfiltered collection of entries
   * @param author the author whose diary entries are to be retrieved.
   */
  public static void filterByAuthor(Collection<DiaryEntry> originalCollection, String author) {
    ArrayList<DiaryEntry> filteredList = new ArrayList<>();
    for (DiaryEntry entry : originalCollection) {
      if (entry.getAuthor().equals(author)) {
        filteredList.add(entry);
      }
    }
    originalCollection.clear();
    originalCollection.addAll(filteredList);
  }

  /**
   * Filters the list of inputted diary entries by the specified activity.
   *
   * @param originalCollection the unfiltered collection of entries
   * @param activity the activity to filter entries by.
   */
  public static void filterByActivity(Collection<DiaryEntry> originalCollection, String activity) {
    ArrayList<DiaryEntry> filteredList = new ArrayList<>();
    for (DiaryEntry entry : originalCollection) {
      if (entry.getActivity().equalsIgnoreCase(activity)) {
        filteredList.add(entry);
      }
    }
    originalCollection.clear();
    originalCollection.addAll(filteredList);
  }

  /**
   * Filters the inputted list of diary entries by the specified destination.
   *
   * @param originalCollection the unfiltered collection of entries
   * @param destination the destination to filter entries by.
   */
  public static void filterByDestination(Collection<DiaryEntry> originalCollection, String destination) {
    List<DiaryEntry> filteredList = new ArrayList<>();
    for (DiaryEntry entry : originalCollection) {
      if (entry.getDestination().equalsIgnoreCase(destination)) {
        filteredList.add(entry);
      }
    }
    originalCollection.clear();
    originalCollection.addAll(filteredList);
  }

  /**
   * Filters the inputted list of diary entries by time interval.
   *
   * @param originalCollection the unfiltered collection of entries
   * @param timeStart the creation time from witch to allow entries through
   * @param timeStop the creation time to which entries are allowed
   */
  public static void filterByTimeCreated(Collection<DiaryEntry> originalCollection, LocalDateTime timeStart,
      LocalDateTime timeStop) {
    ArrayList<DiaryEntry> filteredList = new ArrayList<>();
    for (DiaryEntry entry : originalCollection) {
      if (entry.getTimeWritten().isAfter(timeStart) && entry.getTimeWritten().isBefore(timeStop)) {
        filteredList.add(entry);
      }
    }
    originalCollection.clear();
    originalCollection.addAll(filteredList);
  }

  /**
   * Returns a list of the unique authors from a collection of Diary Entries.
   *
   * @param originalCollection the collection to get authors from
   * @return the list of unique authors
   */
  public static List<String> getDistinctAuthors(Collection<DiaryEntry> originalCollection) {
    // use TreeSet to ensure uniqueness and alphabetical sorting
    Set<String> authors = new TreeSet<>();
    for (DiaryEntry entry : originalCollection) {
      authors.add(entry.getAuthor());
    }
    return new ArrayList<>(authors);
  }

  /**
   * Returns a list of the unique activities from a collection of Diary Entries.
   *
   * @param originalCollection the collection to get activities from
   * @return the list of unique activities
   */
  public static List<String> getDistinctActivities(Collection<DiaryEntry> originalCollection) {
    // use TreeSet to ensure uniqueness and alphabetical sorting
    Set<String> activities = new TreeSet<>();
    for (DiaryEntry entry : originalCollection) {
      activities.add(entry.getActivity());
    }
    return new ArrayList<>(activities);
  }

  /**
   * Returns a list of the unique destinations from a collection of Diary Entries.
   *
   * @param originalCollection the collection to get destinations from
   * @return the list of unique destinations
   */
  public static List<String> getDistinctDestinations(Collection<DiaryEntry> originalCollection) {
    // use TreeSet to ensure uniqueness and alphabetical sorting
    Set<String> destinations = new TreeSet<>();
    for (DiaryEntry entry : originalCollection) {
      destinations.add(entry.getDestination());
    }
    return new ArrayList<>(destinations);
  }
}
