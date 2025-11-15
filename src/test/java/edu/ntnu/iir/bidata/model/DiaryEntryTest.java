package edu.ntnu.iir.bidata.model;

import edu.ntnu.iir.bidata.storage.DiaryEntryStorageDto;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DiaryEntryTest {

  @Test
  void testDiaryEntryToDtoBackTODiaryEntry() {
    DiaryEntry original =
        new DiaryEntry("AuthorName", "Destination", "Activity", 5.5, "EntryTitle", "Some text");
    DiaryEntryStorageDto dto = new DiaryEntryStorageDto(original);
    DiaryEntry recreated = new DiaryEntry(dto);

    assertEquals(original.getTimeWritten(), recreated.getTimeWritten());
    assertEquals(original.getTimeEdited(), recreated.getTimeEdited());
    assertEquals(original.getAuthor(), recreated.getAuthor());
    assertEquals(original.getDestination(), recreated.getDestination());
    assertEquals(original.getActivity(), recreated.getActivity());
    assertEquals(original.getRating(), recreated.getRating());
    assertEquals(original.getTitle(), recreated.getTitle());
    assertEquals(original.getText(), recreated.getText());
  }

  @Test
  void testSettersUpdateFields() {
    DiaryEntry diaryEntry =
        new DiaryEntry("AuthorName", "Destination", "Activity", 5.5, "EntryTitle", "Some text");

    diaryEntry.setAuthor("NewAuthor");
    diaryEntry.setDestinationString("NewDestination");
    diaryEntry.setActivity("NewActivity");
    diaryEntry.setRating(9);
    diaryEntry.setTitle("NewTitle");
    diaryEntry.setText("New text");

    assertEquals("NewAuthor", diaryEntry.getAuthor());
    assertEquals("NewDestination", diaryEntry.getDestination());
    assertEquals("NewActivity", diaryEntry.getActivity());
    assertEquals(9, diaryEntry.getRating());
    assertEquals("NewTitle", diaryEntry.getTitle());
    assertEquals("New text", diaryEntry.getText());
  }

  @Test
  void testSetRatingAcceptsBounds() {
    DiaryEntry diaryEntry =
        new DiaryEntry("AuthorName", "Destination", "Activity", 5.5, "EntryTitle", "text");

    diaryEntry.setRating(0);
    assertEquals(0, diaryEntry.getRating());

    diaryEntry.setRating(10);
    assertEquals(10, diaryEntry.getRating());
  }

  @Test
  void testSetRatingRejectsBelowZero() {
    DiaryEntry diaryEntry =
        new DiaryEntry("AuthorName", "Destination", "Activity", 5.5, "EntryTitle", "Some text");

    assertThrows(IllegalArgumentException.class, () -> diaryEntry.setRating(-1));
  }

  @Test
  void testSetRatingRejectsAboveTen() {
    DiaryEntry diaryEntry =
        new DiaryEntry("AuthorName", "Destination", "Activity", 5.5, "EntryTitle", "Some text");

    assertThrows(IllegalArgumentException.class, () -> diaryEntry.setRating(11));
  }

  @Test
  void testTimeWrittenAndTimeEdited() {
    LocalDateTime before = LocalDateTime.now();
    DiaryEntry diaryEntry =
        new DiaryEntry("AuthorName", "Destination", "Activity", 5.5, "EntryTitle", "Some text");
    LocalDateTime after = LocalDateTime.now();

    assertFalse(diaryEntry.getTimeWritten().isBefore(before));
    assertFalse(diaryEntry.getTimeWritten().isAfter(after));
    assertFalse(diaryEntry.getTimeEdited().isBefore(before));
    assertFalse(diaryEntry.getTimeEdited().isAfter(after));
  }

  @Test
  void testSetTimeEditedUpdates() throws InterruptedException {
    DiaryEntry diaryEntry =
        new DiaryEntry("AuthorName", "Destination", "Activity", 5.5, "EntryTitle", "Some text");
    LocalDateTime beforeEdit = diaryEntry.getTimeEdited();

    Thread.sleep(5); // small delay to ensure time changes
    diaryEntry.setTimeEdited();

    assertTrue(diaryEntry.getTimeEdited().isAfter(beforeEdit));
  }

  @Test
  void testToStringContainsMainFields() {
    DiaryEntry diaryEntry =
        new DiaryEntry("AuthorName", "Destination", "Activity", 5.5, "EntryTitle", "Some text");

    String result = diaryEntry.toString();

    assertTrue(result.contains("AuthorName"));
    assertTrue(result.contains("Destination"));
    assertTrue(result.contains("Activity"));
    assertTrue(result.contains("5.5"));
    assertTrue(result.contains("EntryTitle"));
    assertTrue(result.contains("Some text"));
  }
}
