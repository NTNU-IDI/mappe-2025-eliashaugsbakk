package edu.ntnu.iir.bidata.manager;

import edu.ntnu.iir.bidata.actions.DiaryEntryBuilder;
import edu.ntnu.iir.bidata.database.Diary;
import edu.ntnu.iir.bidata.database.DiaryEntry;
import edu.ntnu.iir.bidata.database.DiaryStorage;
import edu.ntnu.iir.bidata.ui.Menu;
import edu.ntnu.iir.bidata.ui.Output;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Manager {

  Scanner sc = new Scanner(System.in);
  Diary diary = new Diary();
  DiaryStorage storage = new DiaryStorage();
  Menu menu = new Menu();
  Output output = new Output();
  DiaryEntryBuilder entryBuilder = new DiaryEntryBuilder();

  public void init() throws IOException {
    ArrayList<DiaryEntry> entries = storage.init();
    for (DiaryEntry entry : entries) {
      diary.addEntry(entry);
    }
    output.message(sc, "All previous entries are loaded.");
  }

  /**
   * The main loop of the program.
   */
  public void loop() throws InterruptedException {
    int userInput = menu.menu(sc);

    switch (userInput) {
      case Menu.CREATE_ENTRY -> {
        diary.addEntry(entryBuilder.createNewEntry(sc, diary.getEntries()));
        output.message(sc, "entry added");
      }
    }
  }
}

