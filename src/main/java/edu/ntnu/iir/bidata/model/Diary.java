package edu.ntnu.iir.bidata.model;

import java.util.ArrayList;

/**
 * The Diary class represents a collection of diary entries. It provides functionality to manage and
 * retrieve these entries.
 */
public class Diary {
  private ArrayList<DiaryEntry> entries = new ArrayList<>();

  /**
   * Filters the diary entries by the specified author.
   *
   * @param author the author whose diary entries are to be retrieved.
   * @return a list of diary entries written by the specified author. If no entries are found,
   * returns an empty list.
   */
  public ArrayList<DiaryEntry> filterByAuthor(String author) {
    ArrayList<DiaryEntry> filteredList = new ArrayList<>();
    for (DiaryEntry entry : entries) {
      if (entry.getAuthor().equals(author)) {
        filteredList.add(entry);
      }
    }
    return filteredList;
  }

  /**
   * Filters the diary entries by the specified activity.
   *
   * @param activity the activity to filter entries by.
   * @return a list of diary entries with the specified activity. If no entries are found, returns
   * an empty list.
   */
  public ArrayList<DiaryEntry> filterByActivity(String activity) {
    ArrayList<DiaryEntry> filteredList = new ArrayList<>();
    for (DiaryEntry entry : entries) {
      if (entry.getActivity().equals(activity)) {
        filteredList.add(entry);
      }
    }
    return filteredList;
  }

  /**
   * Filters the diary entries by the specified destination.
   *
   * @param destination the destination to filter entries by.
   * @return a list of diary entries with the specified destination. If no entries are found,
   * returns an empty list.
   */
  public ArrayList<DiaryEntry> filterByDestination(String destination) {
    ArrayList<DiaryEntry> filteredList = new ArrayList<>();
    for (DiaryEntry entry : entries) {
      if (entry.getDestination().equals(destination)) {
        filteredList.add(entry);
      }
    }
    return filteredList;
  }

  /**
   * Adds a new diary entry to the diary.
   *
   * @param e The diary entry to be added. This should be a valid instance of {@code DiaryEntry},
   *          containing details such as the author, destination, activity, rating, title, and main
   *          content.
   */
  public void addDiaryEntry(DiaryEntry e) {
    entries.add(e);
  }

  /**
   * Adds a collection of new diary entries to the diary.
   *
   * @param entriesToAdd the list of diary entries to be added.
   */
  public void addDiaryEntries(ArrayList<DiaryEntry> entriesToAdd) {
    entries.addAll(entriesToAdd);
  }

  /**
   * Retrieves all diary entries stored in the diary.
   *
   * @return A list of all diary entries.
   */
  public ArrayList<DiaryEntry> getAllDiaryEntries() {
    return entries;
  }

}
