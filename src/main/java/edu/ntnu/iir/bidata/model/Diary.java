package edu.ntnu.iir.bidata.model;

import java.util.ArrayList;

/**
 * The Diary class represents a collection of diary entries. It provides functionality to manage and
 * retrieve these entries.
 */
public class Diary {
  private ArrayList<DiaryEntry> entries = new ArrayList<>();

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
