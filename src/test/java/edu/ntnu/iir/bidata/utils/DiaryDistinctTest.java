package edu.ntnu.iir.bidata.utils;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryDistinctTest {

  @Test
  void shouldReturnDistinctAuthors_When_DistinctAuthorsIsCalled() {
    // Arrange: Create three diary entries with two distinct authors
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0", 0, "title0", "text0");
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1", 1, "title1", "text1");
    DiaryEntry entry2 = new DiaryEntry("author0", "dest2", "act2", 2, "title2", "text2");
    List<DiaryEntry> entries = new ArrayList<>();
    entries.add(entry0);
    entries.add(entry1);
    entries.add(entry2);

    // Act
    List<String> distinct = DiaryDistinct.getDistinctAuthors(entries);

    // Assert
    assertEquals(2, distinct.size());
    assertEquals(entries.getFirst().getAuthor(), distinct.getFirst());
    assertEquals(entries.get(1).getAuthor(), distinct.getLast());
  }

  @Test
  void shouldReturnDistinctActivity_When_DistinctActivityIsCalled() {
    // Arrange: Create three diary entries with two distinct authors
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0", 0, "title0", "text0");
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1", 1, "title1", "text1");
    DiaryEntry entry2 = new DiaryEntry("author2", "dest2", "act0", 2, "title2", "text2");
    List<DiaryEntry> entries = new ArrayList<>();
    entries.add(entry0);
    entries.add(entry1);
    entries.add(entry2);

    // Act
    List<String> distinct = DiaryDistinct.getDistinctActivities(entries);

    // Assert
    assertEquals(2, distinct.size());
    assertEquals(entries.getFirst().getActivity(), distinct.getFirst());
    assertEquals(entries.get(1).getActivity(), distinct.getLast());
  }

  @Test
  void shouldReturnDistinctDestination_When_DistinctDestinationIsCalled() {
    // Arrange: Create three diary entries with two distinct authors
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0", 0, "title0", "text0");
    DiaryEntry entry1 = new DiaryEntry("author1", "dest1", "act1", 1, "title1", "text1");
    DiaryEntry entry2 = new DiaryEntry("author2", "dest0", "act2", 2, "title2", "text2");
    List<DiaryEntry> entries = new ArrayList<>();
    entries.add(entry0);
    entries.add(entry1);
    entries.add(entry2);

    // Act
    List<String> distinct = DiaryDistinct.getDistinctDestinations(entries);

    // Assert
    assertEquals(2, distinct.size());
    assertEquals(entries.getFirst().getDestination(), distinct.getFirst());
    assertEquals(entries.get(1).getDestination(), distinct.getLast());
  }
}
