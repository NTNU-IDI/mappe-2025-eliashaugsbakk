package edu.ntnu.iir.bidata.database;

import edu.ntnu.iir.bidata.utils.DiaryEntry;
import java.util.ArrayList;

/**
 * DiaryDatabase class to store the Array with diary objects. Array with author variables. Array
 * with categories variable.
 */
public class Diary {
  private ArrayList<DiaryEntry> entries = new ArrayList<>();

  public void addEntry(DiaryEntry diaryEntry) {
    entries.add(diaryEntry);
  }

  public DiaryEntry getEntry(int index) {
    if (index >= 0 || index <= entries.size()) {
      return entries.get(index);
    } else {
      return null;
    }
  }

  /**
   * Geetter to return the ArrayList of diayr entries. Return copy for safety.
   *
   * @return
   */
  public ArrayList<DiaryEntry> getEntries() {
    ArrayList<DiaryEntry> entriesCopy = new ArrayList<>(entries);
    return entriesCopy;
  }
}
