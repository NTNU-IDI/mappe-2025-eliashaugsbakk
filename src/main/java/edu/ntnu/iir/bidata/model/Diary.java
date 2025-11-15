package edu.ntnu.iir.bidata.model;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * The Diary class represents a collection of diary entries. It provides functionality to manage and
 * retrieve these entries.
 */
public class Diary {
  private final ArrayList<DiaryEntry> entries = new ArrayList<>();

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
   * @param entry The diary entry to be added. This should be a valid instance of
   *              {@code DiaryEntry}, containing details such as the author, destination, activity,
   *              rating, title, and main content.
   * @throws IllegalArgumentException if an entry with the same title already exists
   */
  public void addDiaryEntry(DiaryEntry entry) {
    for (DiaryEntry e : entries) {
      if (e.getTitle().equals(entry.getTitle())) {
        throw new IllegalArgumentException("duplicate titles are not allowed");
      }
    }
    entries.add(entry);
  }

  /**
   * Adds a collection of new diary entries to the diary.
   *
   * <p>The operation is atomic: if any of the entries has a duplicate title (either compared to
   * existing entries or within the batch itself), no entries are added and an
   * {@link IllegalArgumentException} is thrown.</p>
   *
   * @param entriesToAdd the list of diary entries to be added.
   * @throws IllegalArgumentException if duplicate titles are found
   */
  public void addDiaryEntries(ArrayList<DiaryEntry> entriesToAdd) {
    // Titles of entries already in the diary
    HashSet<String> existingTitles = new HashSet<>();
    for (DiaryEntry entry : entries) {
      existingTitles.add(entry.getTitle());
    }

    // Titles within this batch to catch duplicates inside entriesToAdd
    HashSet<String> entriesToAddTitles = new HashSet<>();

    // Checks for duplicates
    for (DiaryEntry entryToAdd : entriesToAdd) {
      String title = entryToAdd.getTitle();

      if (existingTitles.contains(title)) {
        throw new IllegalArgumentException("duplicate titles are not allowed");
      }

      // add() returns false if the title already is in entriesToAddTitles
      if (!entriesToAddTitles.add(title)) {
        throw new IllegalArgumentException("duplicate titles are not allowed");
      }
    }

    // Only add after validation passes for all
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
