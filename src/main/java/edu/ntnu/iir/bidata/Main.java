package edu.ntnu.iir.bidata;

import com.google.gson.Gson;
import edu.ntnu.iir.bidata.logic.DiaryEntry;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        DiaryEntry diaryEntry = new DiaryEntry();
        diaryEntry.textInput();
    }
}