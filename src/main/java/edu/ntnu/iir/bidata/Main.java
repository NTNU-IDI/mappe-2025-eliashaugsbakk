package edu.ntnu.iir.bidata;

import com.google.gson.Gson;
import edu.ntnu.iir.bidata.database.JsonReaderWriter;
import edu.ntnu.iir.bidata.logic.DiaryEntry;
import edu.ntnu.iir.bidata.UI.Menu;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Checking if data.json is present, if not, it is created.
        File file = new File("database/data.json");
        if (!file.exists()) {
            System.out.println("No database file found, starting fresh.");
            file.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("[]");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // readerWriter to handle Output and Intput to and from the JSON file
        JsonReaderWriter readerWriter = new JsonReaderWriter("database/data.json");

        // Initiating a list with all the diferent diary entries
        List<DiaryEntry> diaryEntries = readerWriter.readObjects();
        if (diaryEntries == null) {
            diaryEntries = new ArrayList<>();
        }
        // The menu loop is started
        Menu menu = new Menu();
        menu.menu();
        diaryEntries.add(new DiaryEntry());
    }
}