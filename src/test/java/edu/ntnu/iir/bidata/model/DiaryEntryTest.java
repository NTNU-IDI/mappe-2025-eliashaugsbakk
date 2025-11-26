package edu.ntnu.iir.bidata.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DiaryEntryTest {

  @Test
  void should_UpdateAllFields_When_SettersAreCalled() {
    // Arrange: Create Entry with old values
    DiaryEntry diaryEntry = new DiaryEntry("OldAuthor", "OldDestination",
        "OldActivity", 5.5, "OldTitle", "Old text");

    // Act: Set new values using the setters
    diaryEntry.setAuthor("NewAuthor");
    diaryEntry.setDestination("NewDestination");
    diaryEntry.setActivity("NewActivity");
    diaryEntry.setRating(9);
    diaryEntry.setTitle("NewTitle");
    diaryEntry.setText("New text");

    // Assert: Confirm that all fields were updated correctly
    assertEquals("NewAuthor", diaryEntry.getAuthor());
    assertEquals("NewDestination", diaryEntry.getDestination());
    assertEquals("NewActivity", diaryEntry.getActivity());
    assertEquals(9, diaryEntry.getRating());
    assertEquals("NewTitle", diaryEntry.getTitle());
    assertEquals("New text", diaryEntry.getText());
  }

  @Test
  void should_AcceptLowerBoundary_When_RatingIsZero() {
    // Arrange: Create Entry
    DiaryEntry diaryEntry = new DiaryEntry("A", "D", "Act",
        5.5, "Title", "text");

    // Act: Set rating
    diaryEntry.setRating(0);

    // Assert: Zero is accepted
    assertEquals(0, diaryEntry.getRating(), "Rating should accept 0.");
  }

  @Test
  void should_AcceptLowerBoundary_When_RatingIsTen() {
    // Arrange: Create Entry
    DiaryEntry diaryEntry = new DiaryEntry("A", "D", "Act",
        5.5, "Title", "text");

    // Act: Set rating
    diaryEntry.setRating(10);

    // Assert: Ten is accepted
    assertEquals(10, diaryEntry.getRating(), "Rating should accept 10.");
  }

  @Test
  void should_ThrowException_When_RatingIsBelowZero() {
    // Arrange: Create Entry and define the invalid action (Act)
    DiaryEntry diaryEntry = new DiaryEntry("A", "D", "Act",
        5.5, "Title", "text");

    // Act: Set rating under 0 should fail
    Executable action = () -> diaryEntry.setRating(-1);

    // Assert: Verify that the action throws the expected exception (negative case)
    assertThrows(IllegalArgumentException.class, action,
        "Should throw exception when rating is -1.");
  }

  @Test
  void should_ThrowException_When_RatingIsAboveTen() {
    // Arrange: Create Entry and define the invalid action (Act)
    DiaryEntry diaryEntry = new DiaryEntry("A", "D", "Act",
        5.5, "Title", "text");

    // Act: Set rating over 10 should fail
    Executable action = () -> diaryEntry.setRating(11);

    // Assert: Verify that the action throws the expected exception (negative case)
    assertThrows(IllegalArgumentException.class, action,
        "Should throw exception when rating is 11.");
  }

  @Test
  void should_SetWrittenAndEditedTime_OnCreation() {
    // Arrange: Capture time before and after creation
    LocalDateTime before = LocalDateTime.now();

    // Act: Create Entry
    DiaryEntry diaryEntry = new DiaryEntry("A", "D", "Act",
        5.5, "Title", "text");

    // Capture time immediately after creation
    LocalDateTime after = LocalDateTime.now();

    // Assert: Check that times fall within the captured bounds
    assertFalse(diaryEntry.getTimeWritten().isBefore(before),
        "TimeWritten should not be before the start time.");
    assertFalse(diaryEntry.getTimeWritten().isAfter(after),
        "TimeWritten should not be after the end time.");
    assertFalse(diaryEntry.getTimeEdited().isBefore(before),
        "TimeEdited should not be before the start time.");
    assertFalse(diaryEntry.getTimeEdited().isAfter(after),
        "TimeEdited should not be after the end time.");
  }

  @Test
  void should_UpdateEditedTime_When_SetTimeEditedIsCalled() throws InterruptedException {
    // Arrange: Create Entry and store the original time
    DiaryEntry diaryEntry = new DiaryEntry("A", "D", "Act", 5.5,
        "Title", "text");
    LocalDateTime beforeEdit = diaryEntry.getTimeEdited();

    // Act: Ensure a time difference, then call setTimeEdited
    Thread.sleep(5);
    diaryEntry.setTimeEdited();

    // Assert: Verify that the new time is indeed later than the original time
    assertTrue(diaryEntry.getTimeEdited().isAfter(beforeEdit),
        "getTimeEdited should be updated to a later time.");
  }

  @Test
  void should_UpdateEditedTime_When_AnySetterIsCalled() throws InterruptedException {
    // Arrange: Create Entry and store the original time
    DiaryEntry diaryEntry = new DiaryEntry("A", "D", "Act",
        5.5, "Title", "text");
    LocalDateTime beforeEdit = diaryEntry.getTimeEdited();

    // Act: Ensure a time difference, then call setText
    Thread.sleep(5);
    diaryEntry.setText("new Text");

    // Assert: Verify that the new time is indeed later than the original time
    assertTrue(diaryEntry.getTimeEdited().isAfter(beforeEdit),
        "Time edited should be updated to a later time when a setter is called.");
  }
}
