package edu.ntnu.iir.bidata;

import edu.ntnu.iir.bidata.UI.Menu;
import edu.ntnu.iir.bidata.database.JsonReaderWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        // loading all previous diary entries
        File file = new File("register/data.json");
        try {
            String content = "";
            if (file.exists()) { // does the data.json file exist?
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    content = reader.lines().reduce("", (acc, line) -> acc + line).trim();
                }
            }
            // checks to see if file is empty or if contents are "null"
            // if it is, prepare it for Gson.
            if (content.isEmpty() || content.equals("null")) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write("[]");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonReaderWriter readerWriter = new JsonReaderWriter();
        readerWriter.loadDiaryEntries();

		// The menu loop begins
		while (true) {
			Menu.menu();
		}
	}
}
