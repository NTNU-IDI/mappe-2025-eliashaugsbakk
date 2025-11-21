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

  /**
   * Methode for deleting a diary entry from the list stored in Diary. This deletes it permenantly
   * after the program exits normally.
   *
   * @param entry the entry to delete
   */
  public void deleteEntry(DiaryEntry entry) {
    entries.remove(entry);
  }
}
