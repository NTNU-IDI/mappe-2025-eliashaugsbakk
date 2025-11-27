package edu.ntnu.iir.bidata.model;

import java.util.HashMap;
import java.util.Map;

/**
 * The Diary class represents a collection of diary entries. It provides functionality to manage and
 * retrieve these entries.
 */
public class Diary {

  // store the entries in a hash map to enforce no duplicates and for fast comparisons
  private final Map<String, DiaryEntry> diaryEntries = new HashMap<>();

  /**
   * Adds a new diary entry to the diary.
   *
   * @param entry The diary entry to be added. This should be a valid instance of
   *     {@code DiaryEntry}, containing details such as the author, destination, activity,
   *     rating, title, and main content. The title must be unique. It is case-sensitive:
   *     EntryTitle and entryTitle will be treated as two separate titles.
   *
   * @throws IllegalArgumentException if an entry with the same title already exists
   */
  public void addDiaryEntry(DiaryEntry entry) {
    String key = entry.getTitle();
    if (diaryEntries.containsKey(key)) {
      throw new IllegalArgumentException("duplicate titles are not allowed");
    }
    diaryEntries.put(key, entry);
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
  public void addDiaryEntries(Map<String, DiaryEntry> entriesToAdd) {
    // Local set to check for duplicates within the incoming batch itself

    for (DiaryEntry entry : entriesToAdd.values()) {
      String title = entry.getTitle();

      // Check if the title exists in storage
      if (diaryEntries.containsKey(title)) {
        throw new IllegalArgumentException("duplicate titles are not allowed");
      }
    }

    // validations passed, add them to the map ensuring keys match titles
    for (DiaryEntry entry : entriesToAdd.values()) {
      diaryEntries.put(entry.getTitle(), entry);
    }
  }

  /**
   * Retrieves all diary entries stored in the diary.
   *
   * @return A copy map of all diary entries.
   */
  public Map<String, DiaryEntry> getAllDiaryEntries() {
    return new HashMap<>(diaryEntries);
  }

  /**
   * Method for deleting a diary entry from the map stored in Diary.
   *
   * @param entry the entry to delete
   */
  public void deleteEntry(DiaryEntry entry) {
    diaryEntries.remove(entry.getTitle());
  }

  /**
   * Deletes all diary entries in from the map stored in Diary.
   */
  public void deleteAllEntries() {
    diaryEntries.clear();
  }
}
