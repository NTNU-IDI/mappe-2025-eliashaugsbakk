package edu.ntnu.iir.bidata.model;

import java.time.LocalDateTime;
import java.util.*;

/**
 * General class to do operations on collections of Diary Entries.
 */
public class DiaryUtils {
  /**
   * Sorts a list of diary entries by the time it was created. The oldest entry first.
   *
   * @param entriesToSort the list of Diary Entries to sort
   */
  public void sortByTime(List<DiaryEntry> entriesToSort) {
    entriesToSort.sort(Comparator.comparing(DiaryEntry::getTimeWritten));
  }

  /**
   * Sorts a list of diary entries by the rating it assigned to it.
   *
   * @param entriesToSort the list of Diary Entries to sort
   */
  public void sortByRating(List<DiaryEntry> entriesToSort) {
    entriesToSort.sort(Comparator.comparing(DiaryEntry::getRating));
  }

  /**
   * Filters the inputted list of diary entries by the specified author.
   *
   * @param originalList the unfiltered ArrayList
   * @param author the author whose diary entries are to be retrieved.
   */
  public void filterByAuthor(ArrayList<DiaryEntry> originalList, String author) {
    ArrayList<DiaryEntry> filteredList = new ArrayList<>();
    for (DiaryEntry entry : originalList) {
      if (entry.getAuthor().equals(author)) {
        filteredList.add(entry);
      }
    }
    originalList.clear();
    originalList.addAll(filteredList);
  }

  /**
   * Filters the list of inputted diary entries by the specified activity.
   *
   * @param originalList the unfiltered ArrayList
   * @param activity the activity to filter entries by.
   */
  public void filterByActivity(Collection<DiaryEntry> originalList, String activity) {
    ArrayList<DiaryEntry> filteredList = new ArrayList<>();
    for (DiaryEntry entry : originalList) {
      if (entry.getActivity().equalsIgnoreCase(activity)) {
        filteredList.add(entry);
      }
    }
    originalList.clear();
    originalList.addAll(filteredList);
  }

  /**
   * Filters the inputted list of diary entries by the specified destination.
   *
   * @param originalList the unfiltered ArrayList
   * @param destination the destination to filter entries by.
   */
  public void filterByDestination(Collection<DiaryEntry> originalList, String destination) {
    List<DiaryEntry> filteredList = new ArrayList<>();
    for (DiaryEntry entry : originalList) {
      if (entry.getDestination().equalsIgnoreCase(destination)) {
        filteredList.add(entry);
      }
    }
    originalList.clear();
    originalList.addAll(filteredList);
  }

  /**
   * Filters the inputted list of diary entries by time interval.
   *
   * @param originalList the unfiltered ArrayList
   * @param timeStart the creation time from witch to allow entries through
   * @param timeStop the creation time to which entries are allowed
   */
  public void filterByTimeCreated(Collection<DiaryEntry> originalList, LocalDateTime timeStart,
      LocalDateTime timeStop) {
    ArrayList<DiaryEntry> filteredList = new ArrayList<>();
    for (DiaryEntry entry : originalList) {
      if (entry.getTimeWritten().isAfter(timeStart) && entry.getTimeWritten().isBefore(timeStop)) {
        filteredList.add(entry);
      }
    }
    originalList.clear();
    originalList.addAll(filteredList);
  }

  /**
   * Returns a list of the unique authors from a list of Diary Entries.
   *
   * @param originalList the list to get authors from
   * @return the list of unique authors
   */
  public List<String> getDistinctAuthors(Collection<DiaryEntry> originalList) {
    // use TreeSet to ensure uniqueness and alphabetical sorting
    Set<String> authors = new TreeSet<>();
    for (DiaryEntry entry : originalList) {
      authors.add(entry.getAuthor());
    }
    return new ArrayList<>(authors);
  }

  /**
   * Returns a list of the unique activities from a list of Diary Entries.
   *
   * @param originalList the list to get activities from
   * @return the list of unique authors
   */
  public List<String> getDistinctActivities(Collection<DiaryEntry> originalList) {
    // use TreeSet to ensure uniqueness and alphabetical sorting
    Set<String> activities = new TreeSet<>();
    for (DiaryEntry entry : originalList) {
      activities.add(entry.getActivity());
    }
    return new ArrayList<>(activities);
  }

  /**
   * Returns a list of the unique destinations from a list of Diary Entries.
   *
   * @param originalList the list to get destinations from
   * @return the list of unique destinations
   */
  public List<String> getDistinctDestinations(Collection<DiaryEntry> originalList) {
    // use TreeSet to ensure uniqueness and alphabetical sorting
    Set<String> destinations = new TreeSet<>();
    for (DiaryEntry entry : originalList) {
      destinations.add(entry.getDestination());
    }
    return new ArrayList<>(destinations);
  }
}
