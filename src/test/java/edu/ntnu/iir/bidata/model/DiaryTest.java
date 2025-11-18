package edu.ntnu.iir.bidata.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
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
  void addEntryDoesNotAllowDuplicateTitles() {
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0", 0, "title0", "text0");
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1", 1, "title0", "text1");

    Diary diary = new Diary();

    diary.addDiaryEntry(entry0);
    assertThrows(IllegalArgumentException.class, () -> diary.addDiaryEntry(entry1));
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
  void addEntriesDoesNotAllowDuplicateTitles() {
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0", 0, "title0", "text0");
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1", 1, "title0", "text1");
    ArrayList<DiaryEntry> entries = new ArrayList<>();
    entries.add(entry0);
    entries.add(entry1);

    Diary diary = new Diary();

    assertThrows(IllegalArgumentException.class, () -> diary.addDiaryEntries(entries));
  }


}
