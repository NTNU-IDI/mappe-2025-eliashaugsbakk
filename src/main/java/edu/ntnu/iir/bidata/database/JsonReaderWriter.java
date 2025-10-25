package edu.ntnu.iir.bidata.database;

import edu.ntnu.iir.bidata.utils.DiaryEntry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonReaderWriter {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static String fileName = "register/data.json";

    public static void loadDiaryEntries() throws IOException {
        try (FileReader reader = new FileReader(fileName)) {
            Type listType = new TypeToken<ArrayList<DiaryEntry>>() {}.getType();
            DiaryDatabase.diaryEntries = gson.fromJson(reader, listType);
        }
    }

    public static void writeToFile() {

        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(DiaryDatabase.diaryEntries, writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
