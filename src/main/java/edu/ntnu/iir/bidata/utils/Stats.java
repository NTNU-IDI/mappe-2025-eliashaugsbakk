package edu.ntnu.iir.bidata.utils;

import edu.ntnu.iir.bidata.database.DiaryDatabase;

import java.util.ArrayList;

public class Stats {

    public ArrayList<String> author(int number) {
        ArrayList<String> authors = new ArrayList<>();
        for (int i = 0; i < DiaryDatabase.diaryEntries.size(); i++) {
            System.out.println(DiaryDatabase.diaryEntries.get(i).author);
            authors.add(DiaryDatabase.diaryEntries.get(i).author);
            authors.add(DiaryDatabase.diaryEntries.get(DiaryDatabase.diaryEntries.size() - i).author); // Ikke ferdig!!
        }
        return authors;
    }
}
