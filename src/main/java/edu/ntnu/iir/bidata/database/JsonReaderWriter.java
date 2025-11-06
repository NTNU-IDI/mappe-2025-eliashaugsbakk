package edu.ntnu.iir.bidata.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.ntnu.iir.bidata.ui.Output;
import edu.ntnu.iir.bidata.utils.DiaryEntry;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static edu.ntnu.iir.bidata.database.DiaryDatabase.*;

/**
 * Class to write and read to and from the Json file.
 */
public class JsonReaderWriter {
  public static final String fileName = "register/data.json";
  static Gson gson = new GsonBuilder().setPrettyPrinting().create();

  /**
   * Method to make sure the Json file is not empty. If it is, write [].
   */
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
      categories.add(diaryEntry.getCategory());
    }
  }

  /**
   * Reading the Json file.
   */
  public static void loadDiaryEntries() throws IOException {
    try (FileReader reader = new FileReader(fileName)) {
      Type listType = new TypeToken<ArrayList<DiaryEntry>>() {
      }.getType();
      diaryEntries = gson.fromJson(reader, listType);
    }
  }

  /**
   * Writing the diaries entries to file.
   */
  public static void writeToFile() {

    try (FileWriter writer = new FileWriter(fileName)) {
      gson.toJson(diaryEntries, writer);
      Output.red("Entries are saved");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
