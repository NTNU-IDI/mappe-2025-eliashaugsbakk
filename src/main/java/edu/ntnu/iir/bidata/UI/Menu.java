package edu.ntnu.iir.bidata.UI;

import edu.ntnu.iir.bidata.database.DiaryDatabase;
import edu.ntnu.iir.bidata.database.JsonReaderWriter;
import edu.ntnu.iir.bidata.utils.DiaryEntry;
import edu.ntnu.iir.bidata.utils.EditEntry;
import edu.ntnu.iir.bidata.utils.ListEntries;
import java.util.Scanner;

public class Menu {
  public static void menu() throws InterruptedException {
    while (true) {
      Output.clear();
      Output.line();
      Output.redln("Choose your next action:");
      System.out.println(
          "\t1. New Diary Entry\n" + "\t2. Edit Diary Entry\n" + "\t3. List entries by..\n" + "\t4. Read Diary Entry\n" + "\t5. Save and exit");
      Output.red("--> ");
      Scanner scanner = new Scanner(System.in);
      int choice = scanner.nextInt();
      Output.clear();

      switch (choice) {
        case 1 -> {
          DiaryEntry newEntry = new DiaryEntry(new Scanner(System.in));
          DiaryDatabase.diaryEntries.add(newEntry);
        }
        case 2 -> {
          EditEntry.edit();
        }
        case 3 -> ListEntries.listEntriesBy();
        case 4 -> {
        }
        case 5 -> {
          JsonReaderWriter.writeToFile();
          System.exit(0);
        }
        default -> System.out.println("Invalid choice.");
      }
    }
  }
}
