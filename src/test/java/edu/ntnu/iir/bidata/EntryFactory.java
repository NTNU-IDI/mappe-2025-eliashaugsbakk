package edu.ntnu.iir.bidata;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import edu.ntnu.iir.bidata.storage.DiaryStorage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Class to create dummy diary entries. It uses its own main function
 * and is separate from the rest of the program.
 */
public class EntryFactory {
  private static final Random RANDOM = new Random();

  private final static List<String> AUTHORS = new ArrayList<>(List.of("Einar", "Kevin", "Kristian", "Knut"));
  private final static List<String> ACTIVITIES = new ArrayList<>(List.of("Stroll", "Bathing", "Dining", "Hike"));
  private final static List<String> DESTINATIONS = new ArrayList<>(List.of("Oslo", "Bergen", "Stockholm", "Narvik"));


  private static Map<String ,DiaryEntry> createEntries() {
    Map<String, DiaryEntry> createdEntries = new HashMap<>();
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
          String text = "Dummy text";
          createdEntries.put(title,
              new DiaryEntry(randomDateTime, randomDateTime, author, dest, act, rating, title, text));
        }
      }
    }
    return createdEntries;
  }

  public static void main(String[] args) throws IOException {
    DiaryStorage storage = new DiaryStorage("register/data.json");

    Map<String, DiaryEntry> entries = new HashMap<>(createEntries());
    storage.writeToFile(entries);
  }
}
