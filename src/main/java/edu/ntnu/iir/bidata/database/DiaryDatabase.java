package edu.ntnu.iir.bidata.database;

import edu.ntnu.iir.bidata.utils.DiaryEntry;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DiaryDatabase {
  // Creating a static list to hold the diary entries.
  public static ArrayList<DiaryEntry> diaryEntries = new ArrayList<>();
  public static ArrayList<String> authors = new ArrayList<>();
  public static ArrayList<String> category = new ArrayList<>();


  public static void loadPreviousEntries() throws IOException {
    // loading all previous diary entries
    File file = new File(JsonReaderWriter.fileName);
    try {
      String content = "";
      if (file.exists()) { // does the data.json file exist?
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
          content = reader.lines().reduce("", (acc, line) -> acc + line).trim();
        }
      }
      // checks to see if file is empty or if contents are "null"
      // if it is, prepare it for Gson.
      if (content.isEmpty() || content.equals("null")) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
          writer.write("[]");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    JsonReaderWriter.loadDiaryEntries();

    for (DiaryEntry diaryEntry : diaryEntries) {
      authors.add(diaryEntry.getAuthor());
    }
    for (DiaryEntry diaryEntry : diaryEntries) {
      category.add(diaryEntry.getCategory());
    }

  }
}
