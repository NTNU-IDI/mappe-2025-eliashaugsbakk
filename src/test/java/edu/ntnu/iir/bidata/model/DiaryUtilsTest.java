package edu.ntnu.iir.bidata.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DiaryUtilsTest {

  @Test
  void should_SortAscending_When_SortByRatingIsCalled() {
    // Arrange: Create three new entries in unsorted order by rating
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0",
        2, "title0", "text0");
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1",
        1, "title1", "text1");
    DiaryEntry entry2 = new DiaryEntry("author0", "dest2", "act2",
        3, "title2", "text2");

    ArrayList<DiaryEntry> entries = new ArrayList<>();
    entries.add(entry0);
    entries.add(entry1);
    entries.add(entry2);

    // Arrange: Create a copy of the list to compare against
    List<DiaryEntry> sortedByRating = new ArrayList<>(entries);

    // Act: Call the sorting method
    sortedByRating = DiaryUtils.sortByRating(sortedByRating);

    // Assert: Asserts that the list is now ordered ascending by rating
    assertEquals(3, sortedByRating.size(), "List size should remain unchanged.");
    assertTrue(sortedByRating.get(0).getRating() <= sortedByRating.get(1).getRating(),
        "First two ratings should be sorted correctly.");
    assertTrue(sortedByRating.get(1).getRating() <= sortedByRating.get(2).getRating(),
        "Last two ratings should be sorted correctly.");
  }

  @Test
  void should_SortByTimeAscending_When_SortByTimeIsCalled() throws InterruptedException {
    // Arrange: Create the entries with a small time delay in between
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0",
        1, "title0", "text0");
    Thread.sleep(5); // Small delay to ensure time changes
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1",
        2, "title1", "text1");
    Thread.sleep(5); // Small delay to ensure time changes
    DiaryEntry entry2 = new DiaryEntry("author0", "dest2", "act2",
        3, "title2", "text2");

    // Add the entries in different order than creation
    List<DiaryEntry> entries = new ArrayList<>();
    entries.add(entry1);
    entries.add(entry0);
    entries.add(entry2);


    // Act: Sort the entries by time.
    entries = DiaryUtils.sortByTime(entries);

    // Assert: The list is now sorted by time written
    assertEquals(3, entries.size(), "Size should remain unchanged.");
    assertTrue(entries.get(0).getTimeWritten().isBefore(entries.get(1).getTimeWritten()),
        "First entry should have been created before the second.");
    assertTrue(entries.get(1).getTimeWritten().isBefore(entries.get(2).getTimeWritten()),
        "Second entry should have been created before the third.");
    assertEquals(entry0, entries.get(0), "Entry0 should be the oldest and first in the list.");
  }

  @Test
  void should_FilterListByAuthor_When_FilterByAuthorIsCalled() {
    // Arrange: Create three entries (two by the chosen author, one different)
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0",
        0, "title0", "text0");
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1",
        1, "title1", "text1");
    DiaryEntry entry2 = new DiaryEntry("author0", "dest2", "act2",
        2, "title2", "text2");

    List<DiaryEntry> entries = new ArrayList<>();
    entries.add(entry0);
    entries.add(entry1);
    entries.add(entry2);


    // Act: Filter the list
    entries = DiaryUtils.filterByAuthor(entries, "author0");

    // Assert: The remaining entries are the correct ones
    assertEquals(2, entries.size(), "Two entries should remain after filtering.");
    assertEquals(entry0, entries.get(0), "First remaining entry should be entry0.");
    assertEquals(entry2, entries.get(1), "Second remaining entry should be entry2.");
    assertFalse(entries.contains(entry1), "Entry1 should have been removed.");
  }

  @Test
  void should_FilterListByDestination_When_FilterByDestinationIsCalled() {
    // Arrange: Create three entries (two with the chosen destination, one different)
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0",
        0, "title0", "text0");
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1",
        1, "title1", "text1");
    DiaryEntry entry2 = new DiaryEntry("author2", "dest0", "act2",
        2, "title2", "text2");

    List<DiaryEntry> entries = new ArrayList<>();
    entries.add(entry0);
    entries.add(entry1);
    entries.add(entry2);


    // Act: Filter the list
    entries = DiaryUtils.filterByDestination(entries, "dest0");

    // Assert: The remaining entries are the correct ones
    assertEquals(2, entries.size(), "Two entries should remain after filtering.");
    assertEquals(entry0, entries.get(0), "First remaining entry should be entry0.");
    assertEquals(entry2, entries.get(1), "Second remaining entry should be entry2.");
  }

  @Test
  void should_FilterListByActivity_When_FilterByActivityIsCalled() {
    // Arrange: Create three entries (two with the chosen activity, one different)
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0",
        0, "title0", "text0");
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1",
        1, "title1", "text1");
    DiaryEntry entry2 = new DiaryEntry("author2", "dest2", "act0",
        2, "title2", "text2");

    List<DiaryEntry> entries = new ArrayList<>();
    entries.add(entry0);
    entries.add(entry1);
    entries.add(entry2);


    // Act: Filter the list
    entries = DiaryUtils.filterByActivity(entries, "act0");

    // Assert: The remaining entries are the correct ones
    assertEquals(2, entries.size(), "Two entries should remain after filtering.");
    assertEquals(entry0, entries.get(0), "First remaining entry should be entry0.");
    assertEquals(entry2, entries.get(1), "Second remaining entry should be entry2.");
  }

  @Test
  void should_FilterListByTimeCreated_When_FilterByTimeCreatedIsCalled() throws InterruptedException {
    // Arrange: Create entries before, inside, and after the interval
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0",
        1, "title0", "text0"); // BEFORE
    Thread.sleep(5);

    LocalDateTime timeStart = LocalDateTime.now(); // START

    Thread.sleep(5);
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1",
        2, "title1", "text1"); // INSIDE
    Thread.sleep(5);
    DiaryEntry entry2 = new DiaryEntry("author2", "dest2", "act2",
        3, "title2", "text2"); // INSIDE
    Thread.sleep(5);

    LocalDateTime timeStop = LocalDateTime.now(); // STOP

    Thread.sleep(5);
    DiaryEntry entry3 = new DiaryEntry("author3", "dest3", "act3",
        3, "title3", "text3"); // AFTER

    // Arrange: Setup list for filtering
    List<DiaryEntry> filteredListByTimeInterval = new ArrayList<>();
    filteredListByTimeInterval.add(entry0);
    filteredListByTimeInterval.add(entry1);
    filteredListByTimeInterval.add(entry2);
    filteredListByTimeInterval.add(entry3);


    // Act: Filter the list
    filteredListByTimeInterval = DiaryUtils.filterByTimeCreated(filteredListByTimeInterval, timeStart, timeStop);

    // Assert: Check that only the 2 correct elements remain
    assertEquals(2, filteredListByTimeInterval.size(),
        "Should find 2 entries within the time interval.");
    assertEquals(entry1, filteredListByTimeInterval.get(0),
        "First entry should be entry1.");
    assertEquals(entry2, filteredListByTimeInterval.get(1),
        "Second entry should be entry2.");

    // Assert: Check that entries outside the interval were removed
    assertFalse(filteredListByTimeInterval.contains(entry0),
        "Entry0 (before start) should have been removed.");
    assertFalse(filteredListByTimeInterval.contains(entry3),
        "Entry3 (after stop) should have been removed.");
  }

  @Test
  void should_FilterListByContent_When_FilterByContentIsCalled() {
    // Arrange: Create entries with text to filter by
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0",
        0, "title0", "This TEXT contains the word TEXT");
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1",
        1, "title1", "This text contains the word text");
    DiaryEntry entry2 = new DiaryEntry("author2", "dest2", "act2",
        2, "title2", "ThisShouldBeFilteredAway");
    List<DiaryEntry> filterByContent = new ArrayList<>(List.of(entry0, entry1, entry2));

    // Act: filter the list by content
    filterByContent = DiaryUtils.filterByContent(filterByContent, "text");

    // Assert: Check to see that the entries are the expected once.
    assertEquals(2, filterByContent.size());
    assertEquals(entry0, filterByContent.getFirst());
    assertEquals(entry1, filterByContent.getLast());
  }
}
