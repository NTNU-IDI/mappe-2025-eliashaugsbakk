package edu.ntnu.iir.bidata.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryTest {

  @Test
  void should_BeEmpty_When_NewDiaryIsCreated() {
    // Arrange: Create a new Diary
    Diary diary = new Diary();

    // Act: Get all entries
    ArrayList<DiaryEntry> entries = diary.getAllDiaryEntries();

    // Assert: The list is not null and is empty
    assertNotNull(entries, "The entry list should not be null.");
    assertTrue(entries.isEmpty(), "The new diary should be empty.");
  }

  @Test
  void should_AddEntry_When_ValidEntryIsProvided() {
    // Arrange: Create new DiaryEntries and Diary
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0",
        0, "title0", "text0");
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1",
        1, "title1", "text1");
    Diary diary = new Diary();

    // Act: Add entries to Diary
    diary.addDiaryEntry(entry0);
    diary.addDiaryEntry(entry1);
    ArrayList<DiaryEntry> entries = diary.getAllDiaryEntries();

    // Assert: The list is the correct size and contains the correct entries
    assertEquals(2, entries.size(), "Diary should contain exactly two entries.");
    assertEquals(entry0, entries.get(0));
    assertEquals(entry1, entries.get(1));
  }

  @Test
  void should_AddMultipleEntries_When_ListIsValid() {
    // Arrange: Create a list of valid entries
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0",
        0, "title0", "text0");
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1",
        1, "title1", "text1");
    ArrayList<DiaryEntry> entriesToAdd = new ArrayList<>();
    entriesToAdd.add(entry0);
    entriesToAdd.add(entry1);

    Diary diary = new Diary();

    // Act: Add the collection of entries
    diary.addDiaryEntries(entriesToAdd);
    ArrayList<DiaryEntry> entries = diary.getAllDiaryEntries();

    // Assert: Check that all entries were added correctly
    assertEquals(2, entries.size(), "Diary should contain two entries after bulk add.");
    assertEquals(entry0, entries.get(0));
    assertEquals(entry1, entries.get(1));
  }

  @Test
  void should_RemoveEntry_When_DeleteEntryIsCalled() {
    // Arrange: Setup diary with two entries
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0",
        0, "title0", "text0");
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1",
        1, "title1", "text1");

    var entries = new ArrayList<DiaryEntry>();
    entries.add(entry0);
    entries.add(entry1);

    Diary diary = new Diary();
    diary.addDiaryEntries(entries);

    // Act: Delete one entry
    diary.deleteEntry(entry0);

    // Assert: Verify size and that the remaining entry is correct
    assertEquals(1, diary.getAllDiaryEntries().size(), "Diary should contain only one entry after deletion.");
    assertEquals("title1", diary.getAllDiaryEntries().getFirst().getTitle(), "The correct entry should remain.");
  }


  @Test
  void should_ThrowException_When_AddingEntryWithDuplicateTitle() {
    // Arrange: Create a diary and add the first entry
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0",
        0, "title0", "text0");
    DiaryEntry duplicateEntry = new DiaryEntry("author1", "dest1", "act1",
        1, "title0", "text1");
    Diary diary = new Diary();
    diary.addDiaryEntry(entry0);

    // Act: The action that should fail
    Executable action = () -> diary.addDiaryEntry(duplicateEntry);

    // Assert: Verify that the action throws IllegalArgumentException
    assertThrows(IllegalArgumentException.class, action, "Adding a duplicate title should throw an exception.");
  }

  @Test
  void should_ThrowException_When_AddingListWithDuplicateTitles() {
    // Arrange: Create a list containing two entries with the same title
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0",
        0, "title0", "text0");
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1",
        1, "title0", "text1");
    ArrayList<DiaryEntry> entries = new ArrayList<>();
    entries.add(entry0);
    entries.add(entry1);

    Diary diary = new Diary();

    // Act: The bulk action that should fail
    Executable action = () -> diary.addDiaryEntries(entries);

    // Assert: Verify that the action throws IllegalArgumentException
    assertThrows(IllegalArgumentException.class, action,
        "Bulk adding entries with duplicate titles should fail.");
  }

  @Test
  void should_ThrowException_When_TitlesAreDuplicateCaseInsensitive() {
    // Arrange: Create a list with two titles that differ only by case
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0",
        0, "title0", "text0");
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1",
        1, "TITLE0", "text1");
    var entries = new ArrayList<DiaryEntry>();
    entries.add(entry0);
    entries.add(entry1);

    Diary diary = new Diary();

    // Act: The action that should fail due to case-insensitivity
    Executable action = () -> diary.addDiaryEntries(entries);

    // Assert: Verify that the action throws IllegalArgumentException
    assertThrows(IllegalArgumentException.class, action,
        "Title comparison should be case-insensitive.");
  }
}
