package edu.ntnu.iir.bidata.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Class to create dummy diary entries. It uses its own main function
 * and is separate from the rest of the program.
 */
public class EntryFactory {

  private static final Random RANDOM = new Random();

  private static final List<String> AUTHORS = new ArrayList<>(
      List.of("Einar", "Kevin", "Kristian", "Synne"));
  private static final List<String> ACTIVITIES = new ArrayList<>(
      List.of("Climbing", "Bathing", "Dining", "Hiking"));
  private static final List<String> DESTINATIONS = new ArrayList<>(
      List.of("Oslo", "Bergen", "Stockholm", "Narvik"));

  /**
   * Fabricates diary entries.
   * Uses random dates and ratings.
   * Also loops through lists of Authors, Activities and destinations to generate unique entries.
   *
   * @return the hashmap of fabricated diary entries
   */
  public static Map<String, DiaryEntry> fabricateEntries() {
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
            randomDateTime = LocalDateTime.of(
                randomYear, randomMonth, randomDay, 0, 0, 0);
          } catch (RuntimeException e) {
            throw new RuntimeException(e);
          }

          double rating = (double) RANDOM.nextInt(101) / 10;
          String text = "I was %s in %s.\nI would rate the experience a: %s"
              .formatted(act, dest, rating);

          String title = "title" + counter;
          createdEntries.put(title,
              new DiaryEntry(randomDateTime, randomDateTime, author,
                  dest, act, rating, title, text));
        }
      }
    }
    return createdEntries;
  }
}
