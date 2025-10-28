package edu.ntnu.iir.bidata.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.ntnu.iir.bidata.utils.DiaryEntry;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonReaderWriter {

  public static final String fileName = "register/data.json";
  static Gson gson = new GsonBuilder().setPrettyPrinting().create();

  public static void loadDiaryEntries() throws IOException {
    try (FileReader reader = new FileReader(fileName)) {
      Type listType = new TypeToken<ArrayList<DiaryEntry>>() {
      }.getType();
      DiaryDatabase.diaryEntries = gson.fromJson(reader, listType);
    }
  }

  public static void writeToFile() {

    try (FileWriter writer = new FileWriter(fileName)) {
      gson.toJson(DiaryDatabase.diaryEntries, writer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
