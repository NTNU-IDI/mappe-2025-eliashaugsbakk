package edu.ntnu.iir.bidata.database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.ntnu.iir.bidata.logic.DiaryEntry;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonReaderWriter {
    private final String filePath;
    private final Gson gson;


    public JsonReaderWriter(String filePath) {
        this.filePath = filePath;
        this.gson = new Gson();
    }
    public void writeObjects(List<DiaryEntry> diaryEntries) {
        try (FileWriter fw = new FileWriter(filePath)) {
            gson.toJson(diaryEntries, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<DiaryEntry> readObjects() {
        Type listType = new TypeToken<List<DiaryEntry>>(){}.getType();
        try (FileReader fr = new FileReader(filePath)) {
            return gson.fromJson(fr, listType);
        }  catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
