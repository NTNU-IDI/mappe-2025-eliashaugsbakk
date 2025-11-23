package edu.ntnu.iir.bidata;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import edu.ntnu.iir.bidata.storage.DiaryStorage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class to create dummy diary entries. It uses its own main function
 * and is separate from the rest of the program.
 */
public class EntryFactory {
  private static final Random RANDOM = new Random();

  private final static List<String> AUTHORS = new ArrayList<>(List.of("Jonas", "Vilde", "Synne", "Knut"));
  private final static List<String> ACTIVITIES = new ArrayList<>(List.of("Stroll", "Bathing", "Dining", "Hike"));
  private final static List<String> DESTINATIONS = new ArrayList<>(List.of("Oslo", "Bergen", "Stockholm", "Narvik"));


  private List<DiaryEntry> createEntries() {
    List<DiaryEntry> createdEntries = new ArrayList<>();
    int counter = 0;
    for (String author : AUTHORS) {
      for (String dest : DESTINATIONS) {
        for (String act : ACTIVITIES) {
          counter++;

          int randomYear = 2015 + RANDOM.nextInt(10);
          int randomMonth = RANDOM.nextInt(12) + 1;
          int randomDay = RANDOM.nextInt(27) + 1;
          LocalDateTime randomDateTime;
          try {
            randomDateTime = LocalDateTime.of(randomYear, randomMonth, randomDay, 0, 0, 0);
          } catch (RuntimeException e) {
            throw new RuntimeException(e);
          }

          double rating = (double) RANDOM.nextInt(101) / 10;
          String title = "title" + counter;
          String text = "dummy text";
          createdEntries.add(
              new DiaryEntry(randomDateTime, author, dest, act, rating, title, text));
        }
      }
    }
    return createdEntries;
  }

  void main() throws IOException {
    DiaryStorage storage = new DiaryStorage("register/data.json");

    List<DiaryEntry> entries = new ArrayList<>(createEntries());
    storage.writeToFile(entries);
  }
}
