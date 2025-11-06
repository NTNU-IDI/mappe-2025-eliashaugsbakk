package edu.ntnu.iir.bidata.database;

import edu.ntnu.iir.bidata.utils.DiaryEntry;
import java.util.ArrayList;

/**
 * DiaryDatabase class to store the Array with diary objects. Array with author variables. Array
 * with categories variable.
 */
public class DiaryDatabase {
  // Creating a static list to hold the diary entries.
  public static ArrayList<DiaryEntry> diaryEntries = new ArrayList<>();
  public static ArrayList<String> authors = new ArrayList<>();
  public static ArrayList<String> categories = new ArrayList<>();
}
