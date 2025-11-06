package edu.ntnu.iir.bidata.utils;

import edu.ntnu.iir.bidata.database.DiaryDatabase;
import edu.ntnu.iir.bidata.ui.Output;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
/**
 * Class to print out a diary entry based on: Title Time written
 *
 */
public class ReadEntry {
  public static void readEntry(Scanner scanner) {
    scanner.nextLine();
    Output.redln("Write the title of a Diary Entry, or type:");
    System.out.println("""
        1 - to list the last 10 diary entries.
        2 - to list the last 10 diary entries of a spesific author.
        3 - to list all entries.
        """);
    String entry = scanner.nextLine();
    try {
      int entryInt = Integer.parseInt(entry);
      switch (entryInt) {
        case 1 -> {
          listTen();
          readEntry(scanner);
        }
        case 2 -> {
          ListEntries.topRatingAuthor(scanner);
          readEntry(scanner);
        }
        case 3 -> {
          ListEntries.byDate();
          readEntry(scanner);
        }
      }
    } catch (RuntimeException e) {
      output(entry);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static void listTen() {
    for (int i = 0; i < 10; i++) {
      if (i >= DiaryDatabase.authors.size()) {
        break;
      }
      System.out.println(
          DiaryDatabase.diaryEntries.get(DiaryDatabase.authors.size() - 1 - i).getTitle());
    }
  }

  public static void output(String entry) {
    for (DiaryEntry diaryEntry : DiaryDatabase.diaryEntries) {
      if (diaryEntry.getTitle().contains(entry)) {
        Output.clear();
        Output.line();
        System.out.println("Rating out of 100: " + diaryEntry.getRating());
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        String time = diaryEntry.getTime();
        LocalDateTime dateTime = LocalDateTime.parse(String.valueOf(time), inputFormat);
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        time = dateTime.format(outputFormat);
        System.out.println("Written: " + time);

        if (diaryEntry.getTimeEdited() != null) {
          String timeEdited = diaryEntry.getTimeEdited();
          LocalDateTime dateTimeEdited =
              LocalDateTime.parse(String.valueOf(timeEdited), inputFormat);
          DateTimeFormatter outputFormatEdited = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
          timeEdited = dateTime.format(outputFormat);
          System.out.println("Last eddited: " + timeEdited);
        } else {
          System.out.println("Has not been edited");
        }
        System.out.println();
        Output.redln(diaryEntry.getTitle());
        System.out.println(diaryEntry.getText());
        Output.line();
      }
    }
  }
}
