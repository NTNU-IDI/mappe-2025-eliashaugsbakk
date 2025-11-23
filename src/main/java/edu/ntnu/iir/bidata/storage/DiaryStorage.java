package edu.ntnu.iir.bidata.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.ntnu.iir.bidata.model.DiaryEntry;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading and saving {@link DiaryEntry} objects to a JSON file on disk.
 *
 * <p>The rest of the application should work with {@code Diary} / {@code DiaryEntry}
 * and call this class when it needs to load or store entries.
 */
public class DiaryStorage {
  private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
  private final String filepath;

  /**
   * Constructs a {@code DiaryStorage} instance for managing the storage of diary entries in a JSON
   * file located at the specified file path.
   *
   * @param filepath the file path where the diary entries will be stored or loaded from. Cannot be
   *                 {@code null} or empty
   */
  public DiaryStorage(String filepath) {
    this.filepath = filepath;
  }

  /**
   * Loads all diary entries from disk.
   *
   * <p>If the file does not exist or is empty, an empty list is returned.
   *
   * @return an {@link ArrayList} containing all the diary entries stored in the file
   * @throws IOException if the file cannot be created or read
   */
  public ArrayList<DiaryEntry> loadEntries() throws IOException {
    ensureFileInitialized();
    return readFromFile();
  }

  /**
   * Ensures that the storage file exists and contains at least an empty JSON array {@code []}.
   *
   * <p>If the file does not exist or is empty, it is created and initialized with {@code []}.
   *
   * @throws IOException if the file cannot be created or written to
   */
  private void ensureFileInitialized() throws IOException {
    File file = new File(filepath);
    if (!file.exists() || file.length() == 0) {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        writer.write("[]");
      }
    }
  }

  /**
   * Reads the JSON file and returns the list of diary entries.
   *
   * @return a list of diary entries, possibly empty. May be {@code null} if the file does not
   * contain a JSON array
   *
   * @throws IOException if the file cannot be read
   */
  private ArrayList<DiaryEntry> readFromFile() throws IOException {
    try (FileReader reader = new FileReader(filepath)) {
      Type listType = new TypeToken<ArrayList<DiaryEntryStorageDto>>() {
      }.getType();

      ArrayList<DiaryEntryStorageDto> dtoEntries = GSON.fromJson(reader, listType);
      ArrayList<DiaryEntry> entries = new ArrayList<>();

      if (dtoEntries != null) {
        for (DiaryEntryStorageDto dto : dtoEntries) {
          DiaryEntry entry = new DiaryEntry(dto);
          entries.add(entry);
        }
      }

      return entries;
    }
  }

  /**
   * Writes the given list of diary entries to the JSON file.
   *
   * @param entries the diary entries to persist; must not be {@code null}
   * @throws IOException if the file cannot be written
   */
  public void writeToFile(List<DiaryEntry> entries) throws IOException {
    ArrayList<DiaryEntryStorageDto> dtoEntries = new ArrayList<>();
    for (DiaryEntry entry : entries) {
      DiaryEntryStorageDto dto = new DiaryEntryStorageDto(entry);
      dtoEntries.add(dto);
    }
    try (FileWriter writer = new FileWriter(filepath)) {
      GSON.toJson(dtoEntries, writer);
    }
  }
}
