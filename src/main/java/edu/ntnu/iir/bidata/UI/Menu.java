package edu.ntnu.iir.bidata.UI;

import edu.ntnu.iir.bidata.database.DiaryDatabase;
import edu.ntnu.iir.bidata.database.JsonReaderWriter;
import edu.ntnu.iir.bidata.utils.DiaryEntry;
import edu.ntnu.iir.bidata.utils.Stats;


import java.util.Scanner;

public class Menu {
    public static void menu() throws InterruptedException {
        JsonReaderWriter readerWriter = new JsonReaderWriter();
        while (true) {
            Output.clear();
            Output.line();
            Output.redln("Choose your next action:");
            System.out.println(
                "\t1. New Diary Entry\n" +
                "\t2. Edit Diary Entry\n" +
                "\t3. List entries by..\n" +
                "\t4. Save and exit"
            );
            System.out.print("--> ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            Output.clear();
            switch (choice) {
                case 1 -> {
                    DiaryEntry newEntry = new DiaryEntry(new Scanner(System.in));
                    DiaryDatabase.diaryEntries.add(newEntry);
                }
                case 2 -> {}
                case 3 -> {
                    Stats.listEntriesBy();}
                case 4 -> {
                    readerWriter.writeToFile();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
