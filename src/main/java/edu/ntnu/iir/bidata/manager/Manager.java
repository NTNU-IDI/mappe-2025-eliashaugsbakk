package edu.ntnu.iir.bidata.manager;

import edu.ntnu.iir.bidata.actions.DiaryEntryBuilder;
import edu.ntnu.iir.bidata.actions.EditEntry;
import edu.ntnu.iir.bidata.database.Diary;
import edu.ntnu.iir.bidata.database.DiaryEntry;
import edu.ntnu.iir.bidata.database.DiaryStorage;
import edu.ntnu.iir.bidata.ui.Menu;
import edu.ntnu.iir.bidata.ui.Output;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Manager {

  Scanner sc = new Scanner(System.in);
  Diary diary = new Diary();
  DiaryStorage storage = new DiaryStorage();
  Menu menu = new Menu();
  Output output = new Output();
  DiaryEntryBuilder entryBuilder = new DiaryEntryBuilder();
  EditEntry editEntry = new EditEntry();

  public void init() throws IOException, InterruptedException {
    diary.addEntries(storage.init());
    output.message(sc, "All previous entries are loaded.");
    loop();
  }

  /**
   * The main loop of the program.
   */
  public void loop() throws InterruptedException {

    while (true) {
      int userInput = menu.menu(sc);

      switch (userInput) {
        case Menu.CREATE_ENTRY -> {
          diary.addEntry(entryBuilder.createNewEntry(sc, diary.getEntries()));
          output.message(sc, "entry added");
        }
        case Menu.EDIT_ENTRY -> {
          output.message(sc, "You will now create a new Diary Entry to replace an old one.");
          output.warning(sc, "These changes are Irreversible");

          List<DiaryEntry> edits = editEntry.entryEdit(sc, diary.getEntries());
          if (edits == null) {
            // User exited
            break;
          }
          ArrayList<DiaryEntry> diaryEdits = new ArrayList<>(edits);

          DiaryEntry replaceFrom = diaryEdits.get(0);
          DiaryEntry replaceWith = diaryEdits.get(1);
          diary.replaceEntry(replaceFrom, replaceWith);
          output.message(sc, "Entry has been changed");
        }
        case Menu.LIST_ENTRIES_BY -> {
        }
        case Menu.READ_ENTRY -> {
        }
      }
    }
  }
}

