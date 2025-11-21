package edu.ntnu.iir.bidata;

import edu.ntnu.iir.bidata.model.Diary;
import edu.ntnu.iir.bidata.model.DiaryUtils;
import edu.ntnu.iir.bidata.storage.DiaryStorage;
import edu.ntnu.iir.bidata.ui.EntryUI;
import edu.ntnu.iir.bidata.ui.Formatter;
import edu.ntnu.iir.bidata.ui.Prompter;
import edu.ntnu.iir.bidata.ui.UI;
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
    DiaryStorage storage = new DiaryStorage("register/data.json");

    // create a Diary instance to store diary entries in memory and do operations on the
    // collection of diary entries
    Diary diary = new Diary();
    diary.addDiaryEntries(storage.loadEntries());

    // create a UI instance to handle the main program loop
    // with Prompter and Formatter as arguments
    Formatter formatter = new Formatter();
    DiaryUtils diaryUtils = new DiaryUtils();
    Prompter prompter = new Prompter(formatter);
    EntryUI entryUI = new EntryUI(diary, prompter);
    UI ui = new UI(prompter, diary, diaryUtils, entryUI,formatter);

    // run the main program loop
    ui.run();

    // store diary entries before exiting the program
    storage.writeToFile(diary.getAllDiaryEntries());
  }
}
