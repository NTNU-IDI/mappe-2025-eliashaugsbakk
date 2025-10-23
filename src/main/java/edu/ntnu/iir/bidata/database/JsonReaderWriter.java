package edu.ntnu.iir.bidata.database;

import edu.ntnu.iir.bidata.utils.DiaryEntry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class JsonReaderWriter {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    String fileName;
    public JsonReaderWriter(String fileName) {
        this.fileName = fileName;
    }
    public String jsonToString() throws IOException {
        File file = new File(this.fileName);
        String data = "";
        if (file.exists()) {

            try (Scanner reader = new Scanner(file)) {
                while (reader.hasNextLine()) {
                    data = reader.nextLine();
                }
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        if (data.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("[]");
            }
        }
            return data;
    }

    public void loadDiaryEntries() throws IOException {
        try (FileReader reader = new FileReader(this.fileName)) {
            Type listType = new TypeToken<ArrayList<DiaryEntry>>() {}.getType();
            DiaryDatabase.diaryEntries = gson.fromJson(reader, listType);
            System.out.println("Privios entries loaded");
        }
    }

    public void writeToFile() throws IOException {

        try (FileWriter writer = new FileWriter(this.fileName)) {
            gson.toJson(DiaryDatabase.diaryEntries, writer);
            System.out.println("Diary entries are saved");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
