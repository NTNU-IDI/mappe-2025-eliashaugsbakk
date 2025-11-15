package edu.ntnu.iir.bidata.storage;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DiaryEntryStorageDtoTest {
  @Test
  void testConstructor() {
    DiaryEntry diaryEntry =
        new DiaryEntry("AuthorName", "Destination", "Activity", 5.5, "EntryTitle", "Some text");

    DiaryEntryStorageDto dto = new DiaryEntryStorageDto(diaryEntry);
    assertEquals(diaryEntry.getTimeWritten().toString(), dto.getTimeWritten());
    assertEquals(diaryEntry.getTimeEdited().toString(), dto.getTimeEdited());
    assertEquals("AuthorName", dto.getAuthor());
    assertEquals("Destination", dto.getDestination());
    assertEquals("Activity", dto.getActivity());
    assertEquals(5.5, dto.getRating());
    assertEquals("EntryTitle", dto.getTitle());
    assertEquals("Some text", dto.getText());
  }

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
}
