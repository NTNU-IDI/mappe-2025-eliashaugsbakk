package edu.ntnu.iir.bidata.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class DiaryTest {
  @Test
  void newDiaryIsEmpty() {
    Diary diary = new Diary();

    ArrayList<DiaryEntry> entries = diary.getAllDiaryEntries();

    assertNotNull(entries);
    assertTrue(entries.isEmpty());
  }

  @Test
  void addEntryAddsEntry() {
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0", 0, "title0", "text0");
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1", 1, "title1", "text1");

    Diary diary = new Diary();

    diary.addDiaryEntry(entry0);
    diary.addDiaryEntry(entry1);

    ArrayList<DiaryEntry> entries = diary.getAllDiaryEntries();

    assertEquals(2, entries.size());
    assertEquals(entry0, entries.get(0));
    assertEquals(entry1, entries.get(1));
  }

  @Test
  void addEntriesAddsEntries() {
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0", 0, "title0", "text0");
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1", 1, "title1", "text1");
    ArrayList<DiaryEntry> e = new ArrayList<>();
    e.add(entry0);
    e.add(entry1);

    Diary diary = new Diary();

    diary.addDiaryEntries(e);

    ArrayList<DiaryEntry> entries = diary.getAllDiaryEntries();

    assertEquals(entry0, entries.get(0));
    assertEquals(entry1, entries.get(1));
  }

  @Test
  void filterByAuthorFiltersByAuthor() {
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0", 0, "title0", "text0");
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1", 1, "title1", "text1");
    DiaryEntry entry2 = new DiaryEntry("author0", "dest2", "act2", 2, "title2", "text2");
    ArrayList<DiaryEntry> entries = new ArrayList<>();
    entries.add(entry0);
    entries.add(entry1);
    entries.add(entry2);

    Diary diary = new Diary();
    diary.addDiaryEntries(entries);
    assertEquals(3, diary.getAllDiaryEntries().size());

    ArrayList<DiaryEntry> filteredListByAuthor = diary.filterByAuthor("author0");
    assertEquals(2, filteredListByAuthor.size());
    assertEquals(entry0, filteredListByAuthor.get(0));
    assertEquals(entry2, filteredListByAuthor.get(1));
  }

  @Test
  void filterByDestinationFiltersByDestination() {
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0", 0, "title0", "text0");
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1", 1, "title1", "text1");
    DiaryEntry entry2 = new DiaryEntry("author2", "dest0", "act2", 2, "title2", "text2");
    ArrayList<DiaryEntry> entries = new ArrayList<>();
    entries.add(entry0);
    entries.add(entry1);
    entries.add(entry2);

    Diary diary = new Diary();
    diary.addDiaryEntries(entries);
    assertEquals(3, diary.getAllDiaryEntries().size());

    ArrayList<DiaryEntry> filteredListByDestination = diary.filterByDestination("dest0");
    assertEquals(2, filteredListByDestination.size());
    assertEquals(entry0, filteredListByDestination.get(0));
    assertEquals(entry2, filteredListByDestination.get(1));
  }

  @Test
  void filterByActivityFiltersByActivity() {
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0", 0, "title0", "text0");
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1", 1, "title1", "text1");
    DiaryEntry entry2 = new DiaryEntry("author2", "dest2", "act0", 2, "title2", "text2");
    ArrayList<DiaryEntry> entries = new ArrayList<>();
    entries.add(entry0);
    entries.add(entry1);
    entries.add(entry2);

    Diary diary = new Diary();
    diary.addDiaryEntries(entries);
    assertEquals(3, diary.getAllDiaryEntries().size());

    ArrayList<DiaryEntry> filteredListByActivity = diary.filterByActivity("act0");
    assertEquals(2, filteredListByActivity.size());
    assertEquals(entry0, filteredListByActivity.get(0));
    assertEquals(entry2, filteredListByActivity.get(1));
  }
}
