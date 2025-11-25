package edu.ntnu.iir.bidata.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * General class to do operations on collections of Diary Entries.
 * This mutates the inputted list directly.
 */
public class DiaryUtils {

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

  /**
   * Filters the inputted list of diary entries by the specified author.
   *
   * @param originalCollection the unfiltered collection of entries
   * @param author the author whose diary entries are to be retrieved.
   * @return a list of entries with the specified author
   */
  public static List<DiaryEntry> filterByAuthor(
      Collection<DiaryEntry> originalCollection, String author) {
    List<DiaryEntry> filteredList = new ArrayList<>();
    for (DiaryEntry entry : originalCollection) {
      if (entry.getAuthor().equals(author)) {
        filteredList.add(entry);
      }
    }
    return filteredList;
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
    List<DiaryEntry> filteredList = new ArrayList<>();
    for (DiaryEntry entry : originalCollection) {
      if (entry.getActivity().equalsIgnoreCase(activity)) {
        filteredList.add(entry);
      }
    }
    return filteredList;
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
    List<DiaryEntry> filteredList = new ArrayList<>();
    for (DiaryEntry entry : originalCollection) {
      if (entry.getDestination().equalsIgnoreCase(destination)) {
        filteredList.add(entry);
      }
    }
    return filteredList;
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
    List<DiaryEntry> filteredList = new ArrayList<>();
    for (DiaryEntry entry : originalCollection) {
      if (entry.getTimeWritten().isAfter(timeStart) && entry.getTimeWritten().isBefore(timeStop)) {
        filteredList.add(entry);
      }
    }
    return filteredList;
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
    searchTerm = searchTerm.toLowerCase().replaceAll("\\s+", "");

    List<DiaryEntry> filteredList = new ArrayList<>();
    for (DiaryEntry entry : originalCollection) {
      // normalize the entry text
      String text = entry.getText().toLowerCase().replaceAll("\\s+", "");
      if (text.contains(searchTerm)) {
        filteredList.add(entry);
      }
    }
    return filteredList;
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
