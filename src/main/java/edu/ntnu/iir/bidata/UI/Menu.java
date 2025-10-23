package edu.ntnu.iir.bidata.UI;

import com.google.gson.stream.JsonReader;
import edu.ntnu.iir.bidata.database.DiaryDatabase;
import edu.ntnu.iir.bidata.database.JsonReaderWriter;
import edu.ntnu.iir.bidata.utils.DiaryEntry;
import edu.ntnu.iir.bidata.utils.Stats;


import java.io.IOException;
import java.util.Scanner;

public class Menu {
    public void menu() throws IOException {
        JsonReaderWriter readerWriter = new JsonReaderWriter("database/data.json");
        Stats stats = new Stats();
        while (true) {
            System.out.println(
                    "Choose your next action:\n" +
                            "1. New Diary Entry\n" +
                            "2. List authors\n" +
                            "3. Save and exit"
            );
            System.out.print("->");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    DiaryEntry newEntry = new DiaryEntry(new Scanner(System.in));
                    DiaryDatabase.diaryEntries.add(newEntry);
                    break;
                case 2:
                    stats.author(5);
                    break;
                case 3:
                    readerWriter.writeToFile();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }
}
