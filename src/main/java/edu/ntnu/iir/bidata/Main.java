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

        JsonReaderWriter readerWriter = new JsonReaderWriter("database/data.json");

        List<DiaryEntry> diaryEntries = readerWriter.readObjects();
        if (diaryEntries == null) {
            diaryEntries = new ArrayList<>();
        }

        Menu menu = new Menu();
        menu.menu();
        diaryEntries.add(new DiaryEntry());
    }
}