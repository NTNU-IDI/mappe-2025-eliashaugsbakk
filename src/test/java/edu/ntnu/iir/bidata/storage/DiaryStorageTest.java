package edu.ntnu.iir.bidata.storage;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DiaryStorageTest {

  private static final String TEST_FILEPATH = "register/test-data.json";

  @BeforeEach
  void cleanUp() throws IOException {
    File file = new File(TEST_FILEPATH);
    if (file.exists()) {
      Files.delete(file.toPath());
    }
  }

  @Test
  void loadEntriesCreatesFileAndReturnsEmptyListWhenFileMissing() throws IOException {
    File file = new File(TEST_FILEPATH);

    DiaryStorage storage = new DiaryStorage(TEST_FILEPATH);

    ArrayList<DiaryEntry> entries = storage.loadEntries();

    assertNotNull(entries);
    assertTrue(entries.isEmpty()); // entries should be empty
    assertTrue(file.exists()); // file should have been created by ensureFileInitialized
  }

  @Test
  void storeEntriesStoresEntriesCorrectlyAndLoadEntriesLoadsEntriesCorrectly() throws IOException {
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0", 0, "title0", "text0");
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1", 1, "title1", "text1");
    ArrayList<DiaryEntry> entries = new ArrayList<>();
    entries.add(entry0);
    entries.add(entry1);

    DiaryStorage storage = new DiaryStorage(TEST_FILEPATH);

    storage.loadEntries();
    storage.writeToFile(entries);

    ArrayList<DiaryEntry> entriesLoaded = storage.loadEntries();

    assertEquals(2, entriesLoaded.size());

    DiaryEntry loaded0 = entriesLoaded.get(0);
    DiaryEntry loaded1 = entriesLoaded.get(1);

    assertEquals("author0", loaded0.getAuthor());
    assertEquals("dest0", loaded0.getDestination());
    assertEquals("act0", loaded0.getActivity());
    assertEquals(0, loaded0.getRating());
    assertEquals("title0", loaded0.getTitle());
    assertEquals("text0", loaded0.getText());

    assertEquals("author1", loaded1.getAuthor());
    assertEquals("dest1", loaded1.getDestination());
    assertEquals("act1", loaded1.getActivity());
    assertEquals(1, loaded1.getRating());
    assertEquals("title1", loaded1.getTitle());
    assertEquals("text1", loaded1.getText());
  }
}
