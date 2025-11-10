package edu.ntnu.iir.bidata.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * Class to write and read to and from the json file.
 */
public class DiaryStorage {
  public static final String filename = "register/data.json";
  static Gson gson = new GsonBuilder().setPrettyPrinting().create();

  /**
   * Init method for loading the diaryentries on startup.
   */
  public ArrayList<DiaryEntry> init() throws IOException {
    isfileenitialized();
    ArrayList<DiaryEntry> diaryEntries = readFromFile();
    return diaryEntries;
  }

  /**
   * Check to see if the file to store json exists or not, and if it is prepared correctly with [].
   * If not, then create it and write [] to it.
   */
  public void isfileenitialized() throws IOException {
    File file = new File(filename);
    if (!file.exists() || file.length() == 0) {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        writer.write("[]");
      }
    }
  }

  /**
   * Reading the json file.
   */
  public ArrayList<DiaryEntry> readFromFile() throws IOException {
    try (FileReader reader = new FileReader(filename)) {
      Type listtype = new TypeToken<ArrayList<DiaryEntry>>() {
      }.getType();
      ArrayList<DiaryEntry> entries = gson.fromJson(reader, listtype);
      return entries;
    }
  }

  /**
   * Writing the diaries entries to file.
   */
  public void writetofile(ArrayList<DiaryEntry> entries) {
    try (FileWriter writer = new FileWriter(filename)) {
      gson.toJson(entries, writer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
