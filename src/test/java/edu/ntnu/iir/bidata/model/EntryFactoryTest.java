package edu.ntnu.iir.bidata.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EntryFactoryTest {
  @Test
  void shouldReturnFabricatedEntries_When_EntryFactoryIsCalled() {
    // Arrange

    // Act
    Map<String, DiaryEntry> entries = new HashMap<>(EntryFactory.fabricateEntries());

    // Assert
    assertEquals(64, entries.size());

    assertFalse(entries.get("title10").getTitle().isEmpty());
  }
}
