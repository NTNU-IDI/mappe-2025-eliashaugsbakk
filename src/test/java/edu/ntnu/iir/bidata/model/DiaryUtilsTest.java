package edu.ntnu.iir.bidata.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiaryUtilsTest {
  @Test
  void sortByRatingSortsByRating() {
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0", 2, "title0", "text0");
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1", 1, "title1", "text1");
    DiaryEntry entry2 = new DiaryEntry("author0", "dest2", "act2", 3, "title2", "text2");
    ArrayList<DiaryEntry> entries = new ArrayList<>();
    entries.add(entry0);
    entries.add(entry1);
    entries.add(entry2);

    Diary diary = new Diary();
    diary.addDiaryEntries(entries);
    assertEquals(3, diary.getAllDiaryEntries().size());

    DiaryUtils diaryUtils = new DiaryUtils();

    ArrayList<DiaryEntry> sortedByRating = diary.getAllDiaryEntries();
    diaryUtils.sortByRating(sortedByRating);
    assertEquals(3, sortedByRating.size());
    assertTrue(sortedByRating.get(0).getRating() <= sortedByRating.get(1).getRating());
    assertTrue(sortedByRating.get(1).getRating() <= sortedByRating.get(2).getRating());
  }

  @Test
  void sortByRatingSortsByTime() throws InterruptedException {
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0", 1, "title0", "text0");
    Thread.sleep(5); // small delay to ensure time changes
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1", 2, "title1", "text1");
    Thread.sleep(5); // small delay to ensure time changes
    DiaryEntry entry2 = new DiaryEntry("author0", "dest2", "act2", 3, "title2", "text2");
    ArrayList<DiaryEntry> entries = new ArrayList<>();
    entries.add(entry1);
    entries.add(entry0);
    entries.add(entry2);

    Diary diary = new Diary();
    diary.addDiaryEntries(entries);
    assertEquals(3, diary.getAllDiaryEntries().size());

    DiaryUtils diaryUtils = new DiaryUtils();

    ArrayList<DiaryEntry> sortedByTime = diary.getAllDiaryEntries();
    diaryUtils.sortByTime(sortedByTime);
    assertEquals(3, sortedByTime.size());
    assertTrue(sortedByTime.get(0).getTimeWritten().isBefore(sortedByTime.get(1).getTimeWritten()));
    assertTrue(sortedByTime.get(1).getTimeWritten().isBefore(sortedByTime.get(2).getTimeWritten()));
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

    DiaryUtils diaryUtils = new DiaryUtils();

    ArrayList<DiaryEntry> filteredListByAuthor = diary.getAllDiaryEntries();
    diaryUtils.filterByAuthor(filteredListByAuthor, "author0");
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

    DiaryUtils diaryUtils = new DiaryUtils();

    ArrayList<DiaryEntry> filteredListByDestination = diary.getAllDiaryEntries();
    diaryUtils.filterByDestination(filteredListByDestination, "dest0");
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

    DiaryUtils diaryUtils = new DiaryUtils();

    ArrayList<DiaryEntry> filteredListByActivity = diary.getAllDiaryEntries();
    diaryUtils.filterByActivity(filteredListByActivity, "act0");
    assertEquals(2, filteredListByActivity.size());
    assertEquals(entry0, filteredListByActivity.get(0));
    assertEquals(entry2, filteredListByActivity.get(1));
  }

  @Test
  void filterByTimeIntervalFiltersByTimeInterval() throws InterruptedException {
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0", 1, "title0", "text0");
    Thread.sleep(5); // small delay to ensure time changes

    LocalDateTime timeStart = LocalDateTime.now();

    Thread.sleep(5); // small delay to ensure time changes
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1", 2, "title1", "text1");
    Thread.sleep(5); // small delay to ensure time changes
    DiaryEntry entry2 = new DiaryEntry("author2", "dest2", "act2", 3, "title2", "text2");
    Thread.sleep(5); // small delay to ensure time changes

    LocalDateTime timeStop = LocalDateTime.now();

    Thread.sleep(5); // small delay to ensure time changes
    DiaryEntry entry3 = new DiaryEntry("author3", "dest3", "act3", 3, "title3", "text3");
    ArrayList<DiaryEntry> entries = new ArrayList<>();
    entries.add(entry0);
    entries.add(entry1);
    entries.add(entry2);
    entries.add(entry3);

    Diary diary = new Diary();
    diary.addDiaryEntries(entries);

    DiaryUtils diaryUtils = new DiaryUtils();

    ArrayList<DiaryEntry> filteredListByTimeInterval = new ArrayList<>(entries);
    diaryUtils.filterByTimeCreated(filteredListByTimeInterval, timeStart, timeStop);

    assertEquals(2, filteredListByTimeInterval.size());
    assertEquals(filteredListByTimeInterval.get(0), entry1);
    assertEquals(filteredListByTimeInterval.get(1), entry2);
  }
}
