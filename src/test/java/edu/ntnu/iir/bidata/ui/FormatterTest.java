package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; // Static import allows using assertEquals directly

import java.util.List;

public class FormatterTest {

  @Test
  void formatStringListCreatesAFormattedListOfStrings() {
    // Simplified list creation
    List<String> list = List.of("string0", "string1", "string2");

    Formatter formatter = new Formatter();
    String result = formatter.formatStringList(list);

    assertTrue(result.contains("3"));
    assertTrue(result.contains("string0"));
    assertTrue(result.contains("string1"));
    assertTrue(result.contains("string2"));

  }

  @Test
  void formatStringListReturnsEmptyStringForEmptyList() {
    Formatter formatter = new Formatter();
    String result = formatter.formatStringList(List.of());
    
    assertEquals("", result);
  }

  @Test
  void formatDiaryEntryListFormatsDiaryEntryList() {
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0", 0, "title0", "text0");

    List<DiaryEntry> listOfEntries = List.of(entry0);

    Formatter formatter = new Formatter();
    String result = formatter.formatDiaryEntryList(listOfEntries);

    assertTrue(result.contains("author0"));
    assertTrue(result.contains("dest0"));
    assertTrue(result.contains("act0"));
    assertTrue(result.contains("title0"));
  }

  @Test
  void formatDiaryEntryIndexedListCreatesIndexedListOfDiayrEntries() {
    DiaryEntry entry0 = new DiaryEntry("author0", "dest0", "act0", 0, "title0", "text0");

    List<DiaryEntry> listOfEntries = List.of(entry0);

    Formatter formatter = new Formatter();
    String result = formatter.formatDiaryEntryList(listOfEntries);

    assertTrue(result.contains("1"));
    assertTrue(result.contains("author0"));
    assertTrue(result.contains("dest0"));
    assertTrue(result.contains("act0"));
    assertTrue(result.contains("title0"));
  }
}
