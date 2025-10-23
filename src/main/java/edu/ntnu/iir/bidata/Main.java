package edu.ntnu.iir.bidata;

import com.google.gson.stream.JsonReader;
import edu.ntnu.iir.bidata.UI.Menu;
import edu.ntnu.iir.bidata.database.DiaryDatabase;
import edu.ntnu.iir.bidata.database.JsonReaderWriter;

import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {

        File file = new File("database/data.json");
        try {
            String content = "";
            if (file.exists()) { // does the data.json file exist?
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    content = reader.lines().reduce("", (acc, line) -> acc + line).trim();
                }
            }
            // checks to see if file is empty or if contents are "null"
            if (content.isEmpty() || content.equals("null")) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write("[]");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Travel Diary, to document and rate all of your travels!");
        JsonReaderWriter readerWriter = new JsonReaderWriter("database/data.json");
        System.out.println("new readerWriter");
        readerWriter.loadDiaryEntries();
        System.out.println("Diary entries loaded");
        Menu menu = new Menu();
		// The menu loop begins
		while (true) {
			menu.menu();
		}
	}
}
