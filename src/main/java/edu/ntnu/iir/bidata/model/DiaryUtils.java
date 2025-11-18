package edu.ntnu.iir.bidata.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * General class to do operations on collections of Diary Entries.
 */
public class DiaryUtils {

  /**
   * Sorts a list of diary entries by the time it was created. The oldest entry first.
   *
   * @param entriesToSort the list of Diary Entries to sort
   */
  public void sortByTime(ArrayList<DiaryEntry> entriesToSort) {
    entriesToSort.sort(Comparator.comparing(DiaryEntry::getTimeWritten));
  }

  /**
   * Sorts a list of diary entries by the rating it assigned to it.
   *
   * @param entriesToSort the list of Diary Entries to sort
   */
  public void sortByRating(ArrayList<DiaryEntry> entriesToSort) {
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
  public void filterByActivity(ArrayList<DiaryEntry> originalList, String activity) {
    ArrayList<DiaryEntry> filteredList = new ArrayList<>();
    for (DiaryEntry entry : originalList) {
      if (entry.getActivity().equals(activity)) {
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
  public void filterByDestination(ArrayList<DiaryEntry> originalList, String destination) {
    ArrayList<DiaryEntry> filteredList = new ArrayList<>();
    for (DiaryEntry entry : originalList) {
      if (entry.getDestination().equals(destination)) {
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
  public void filterByTimeCreated(ArrayList<DiaryEntry> originalList, LocalDateTime timeStart,
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
  public List<String> getDistinctAuthors(ArrayList<DiaryEntry> originalList) {
    HashSet<String> authors = new HashSet<>();
    for (DiaryEntry entry : originalList) {
      authors.add(entry.getAuthor());
    }
    List<String> authorsList = new ArrayList<>(authors);
    authorsList.sort(String::compareTo);
    return  authorsList;
  }

  /**
   * Returns a list of the unique activities from a list of Diary Entries.
   *
   * @param originalList the list to get activities from
   * @return the list of unique authors
   */
  public List<String> getDistinctActivity(ArrayList<DiaryEntry> originalList) {
    HashSet<String> activities = new HashSet<>();
    for (DiaryEntry entry : originalList) {
      activities.add(entry.getActivity());
    }
    List<String> activitiesList = new ArrayList<>(activities);
    activitiesList.sort(String::compareTo);
    return activitiesList;
  }

  /**
   * Returns a list of the unique destinations from a list of Diary Entries.
   *
   * @param originalList the list to get destinations from
   * @return the list of unique destinations
   */
  public List<String> getDistinctDestinations(ArrayList<DiaryEntry> originalList) {
    HashSet<String> destinations = new HashSet<>();
    for (DiaryEntry entry : originalList) {
      destinations.add(entry.getDestination());
    }
    List<String> destinationsList = new ArrayList<>(destinations);
    destinationsList.sort(String::compareTo);
    return destinationsList;
  }
}
