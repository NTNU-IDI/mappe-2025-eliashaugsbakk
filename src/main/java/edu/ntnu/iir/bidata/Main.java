package edu.ntnu.iir.bidata;

import edu.ntnu.iir.bidata.model.Diary;
import edu.ntnu.iir.bidata.model.EntryFactory;
import edu.ntnu.iir.bidata.storage.DiaryStorage;
import edu.ntnu.iir.bidata.ui.EntryUi;
import edu.ntnu.iir.bidata.ui.Formatter;
import edu.ntnu.iir.bidata.ui.Prompter;
import edu.ntnu.iir.bidata.ui.Ui;
import java.io.IOException;

/**
 * Main serves as the entrypoint for the program. It contains the {@code main(String[] args)}
 * method.
 */
public class Main {

  /**
   * The main method serves as the entry point for the program execution. It initializes the
   * {@code DiaryStorage} and {@code Diary} to load and save diary entries, and to store
   * diaryEntries in memory respectively.
   *
   * @param args command-line arguments supplied to the program, not used in this implementation
   * @throws IOException loading and saving entries may cause an error
   */
  public static void main(String[] args) throws IOException {
    Main main = new Main();
    main.init();
  }

  private void init() throws IOException {
    // create a DiaryStorage instance to load and save diary entries
    DiaryStorage storage = new DiaryStorage();

    // create a Diary instance to store diary entries in memory and do operations on the
    // collection of diary entries
    Diary diary = new Diary();
    diary.addDiaryEntries(storage.loadEntries());


    Formatter formatter = new Formatter();
    Prompter prompter = new Prompter(formatter);

    // if the Diary is empty, prompt the user to ask if they want to add fabricated entries
    if (diary.getAllDiaryEntries().isEmpty()) {
      if (prompter.confirmAction(
          "It seems your diary is empty."
          + "Do you want to generate sample data to test out the program?")) {
        diary.addDiaryEntries(EntryFactory.fabricateEntries());
      }
    }

    // create an Ui instance to handle the main program loop
    // with Prompter, Diary, EntryUi and Formatter as arguments
    EntryUi entryUi = new EntryUi(diary, prompter, formatter);
    Ui ui = new Ui(prompter, diary, entryUi, formatter);

    // run the main program loop
    ui.run();

    // store diary entries before exiting the program
    storage.writeToFile(diary.getAllDiaryEntries());
  }
}
