package edu.ntnu.iir.bidata.storage;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DiaryStorageTest {
  // Use a specific test file to avoid overwriting real data
  private static final String TEST_FILE_PATH = "register/test-data.json";

  @AfterEach
  void cleanup() {
    // Cleanup: Delete the test file after every test to ensure a clean state
    File file = new File(TEST_FILE_PATH);
    if (file.exists()) {
      file.delete();
    }
  }

  @Test
  void should_ReturnEmptyList_When_FileDoesNotExist() throws IOException {
    // Arrange: Ensure the file does not exist
    File file = new File(TEST_FILE_PATH);
    if (file.exists()) file.delete();
    
    DiaryStorage storage = new DiaryStorage(TEST_FILE_PATH);

    // Act: Load entries
    Map<String, DiaryEntry> result = storage.loadEntries();

    // Assert: The result should be an empty list (not null)
    assertNotNull(result, "Result should not be null.");
    assertTrue(result.isEmpty(), "Should return empty list for missing file.");
  }

  @Test
  void should_CreateNewFile_When_LoadingFromMissingFile() throws IOException {
    // Arrange: Storage pointing to a non-existent file
    DiaryStorage storage = new DiaryStorage(TEST_FILE_PATH);

    // Act: Trigger load (which calls ensureFileInitialized)
    storage.loadEntries();

    // Assert: The file should now exist on disk
    File file = new File(TEST_FILE_PATH);
    assertTrue(file.exists(), "File should be created if it did not exist.");
  }

  @Test
  void should_PersistEntries_When_WritingAndReadingBack() throws IOException {
    // Arrange: Create entries and storage
    DiaryEntry entry1 = new DiaryEntry("Auth1", "Dest1", "Act1",
        1, "Title1", "Text1");
    DiaryEntry entry2 = new DiaryEntry("Auth2", "Dest2", "Act2",
        2, "Title2", "Text2");
    Map<String, DiaryEntry> entriesToWrite = new HashMap<>();
    entriesToWrite.put(entry1.getTitle(), entry1);
    entriesToWrite.put(entry2.getTitle(), entry2);

    DiaryStorage storage = new DiaryStorage(TEST_FILE_PATH);

    // Act: Write to disk, then read back into a new list
    storage.writeToFile(entriesToWrite);
    Map<String, DiaryEntry> readEntries = storage.loadEntries();

    // Assert: Verify the data survived the round-trip
    assertEquals(2, readEntries.size(), "Should have read back 2 entries.");
    assertEquals("Title1", readEntries.get("Title1").getTitle(),
        "First entry title should match.");
    assertEquals("Title2", readEntries.get("Title2").getTitle(),
        "Second entry title should match.");
  }
}
