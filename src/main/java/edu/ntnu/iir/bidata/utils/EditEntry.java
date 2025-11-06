package edu.ntnu.iir.bidata.utils;

import edu.ntnu.iir.bidata.database.DiaryDatabase;
import edu.ntnu.iir.bidata.database.JsonReaderWriter;
import edu.ntnu.iir.bidata.ui.Output;
import java.util.Scanner;

import static edu.ntnu.iir.bidata.utils.ReadEntry.listTen;

public class EditEntry {
  public static void editEntry(Scanner scanner) {
    scanner.nextLine();
    Output.redln("Write the title of a Diary Entry to edit, or type:");
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
          editEntry(scanner);
        }
        case 2 -> {
          ListEntries.topRatingAuthor(scanner);
          editEntry(scanner);
        }
        case 3 -> {
          ListEntries.byDate();
          editEntry(scanner);
        }
      }
    } catch (NumberFormatException | InterruptedException e) {
      edit(scanner, entry);
    }
  }

  private static void edit(Scanner scanner, String entryTitle) {
    ReadEntry.output(entryTitle);
    Output.red("The entry is printed over for reference.");

    int entry = 0;
    for (DiaryEntry diaryEntry : DiaryDatabase.diaryEntries) {
      if (diaryEntry.getTitle().contains(entryTitle)) {
        entry = DiaryDatabase.diaryEntries.indexOf(diaryEntry);
      }
    }

    DiaryDatabase.diaryEntries.get(entry).setTimeEdited();
    DiaryDatabase.diaryEntries.get(entry).setAuthor(scanner);
    DiaryDatabase.diaryEntries.get(entry).setTrip(scanner);
    DiaryDatabase.diaryEntries.get(entry).setTitle(scanner);
    DiaryDatabase.diaryEntries.get(entry).setCategory(scanner);
    DiaryDatabase.diaryEntries.get(entry).setRating(scanner);
    DiaryDatabase.diaryEntries.get(entry).setText(scanner);
    JsonReaderWriter.writeToFile();
  }
}
