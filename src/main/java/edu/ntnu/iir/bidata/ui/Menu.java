package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.database.DiaryDatabase;
import edu.ntnu.iir.bidata.database.JsonReaderWriter;
import edu.ntnu.iir.bidata.utils.DiaryEntry;
import edu.ntnu.iir.bidata.utils.EditEntry;
import edu.ntnu.iir.bidata.utils.ListEntries;
import edu.ntnu.iir.bidata.utils.ReadEntry;
import java.util.Scanner;

/**
 * Menu class to hold the menu method.
 */
public class Menu {
  /**
   * Menu method to handle the user options.
   */
  public static void menu(Scanner scanner) throws InterruptedException {
    while (true) {
      scanner.nextLine();
      Output.clear();
      Output.line();
      Output.redln("Choose your next action:");
      System.out.println("""
           \t1. New Diary Entry
           \t2. Edit Diary Entry
           \t3. List entries by..
           \t4. Read Diary Entry
           \t5. Save and exit
          """);
      Output.red("--> ");
      int choice = scanner.nextInt();
      Output.clear();

      switch (choice) {
        case 1 -> {
          DiaryEntry newEntry = new DiaryEntry(new Scanner(System.in));
          DiaryDatabase.diaryEntries.add(newEntry);
        }
        case 2 -> {
          EditEntry.editEntry(scanner);
        }
        case 3 -> ListEntries.listEntriesBy();
        case 4 -> ReadEntry.readEntry(scanner);
        case 5 -> {
          JsonReaderWriter.writeToFile();
          System.exit(0);
        }
        default -> System.out.println("Invalid choice.");
      }
    }
  }
}
