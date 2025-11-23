package edu.ntnu.iir.bidata.storage;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiaryEntryStorageDtoTest {

  @Test
  void should_MapAllFieldsCorrectly_When_ConvertingFromEntryToDto() {
    // Arrange: Create a standard DiaryEntry
    DiaryEntry entry = new DiaryEntry("Author", "Dest", "Activity",
        5, "Title", "Text");

    // Act: Create the DTO
    DiaryEntryStorageDto dto = new DiaryEntryStorageDto(entry);

    // Assert: Verify that simple fields match exactly
    assertEquals(entry.getAuthor(), dto.getAuthor(), "Author should match.");
    assertEquals(entry.getDestination(), dto.getDestination(), "Destination should match.");
    assertEquals(entry.getActivity(), dto.getActivity(), "Activity should match.");
    assertEquals(entry.getRating(), dto.getRating(), "Rating should match.");
    assertEquals(entry.getTitle(), dto.getTitle(), "Title should match.");
    assertEquals(entry.getText(), dto.getText(), "Text should match.");
  }

  @Test
  void should_ConvertDateTimesToStrings_When_CreatingDto() {
    // Arrange: Create an entry (which automatically sets timeWritten and timeEdited)
    DiaryEntry entry = new DiaryEntry("A", "D", "A",
        5, "T", "Text");

    // Act: Create DTO
    DiaryEntryStorageDto dto = new DiaryEntryStorageDto(entry);

    // Assert: Verify that the DTO's string timestamps match the Entry's
    // LocalDateTime toString() representation
    assertEquals(entry.getTimeWritten().toString(), dto.getTimeWritten(),
        "TimeWritten should be converted to String.");
    assertEquals(entry.getTimeEdited().toString(), dto.getTimeEdited(),
        "TimeEdited should be converted to String.");
  }
}
