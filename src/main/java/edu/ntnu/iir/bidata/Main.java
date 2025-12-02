package edu.ntnu.iir.bidata;

import edu.ntnu.iir.bidata.model.Diary;
import edu.ntnu.iir.bidata.model.EntryFactory;
import edu.ntnu.iir.bidata.storage.DiaryStorage;
import edu.ntnu.iir.bidata.ui.CollectionUi;
import edu.ntnu.iir.bidata.ui.EntryUi;
import edu.ntnu.iir.bidata.ui.Formatter;
import edu.ntnu.iir.bidata.ui.Prompter;
import edu.ntnu.iir.bidata.ui.Ui;
import java.io.IOException;

/**
 * Serves as the entry point for the program. Contains the {@code main(String[] args)} method.
 *
 * <p>Provides an {@code init} method to start the program by invoking methods that load
 *   previous entries and initialize necessary classes. Handles program execution via the
 *   {@code run} method, and ensures entries are saved before exiting gracefully using
 *   the {@code shutdown} method.
 */
public class Main {

  /**
   * The main method serves as the entry point for the program execution. It initializes the
   * {@code DiaryStorage} and {@code Diary} to load and save diary entries, and to store
   * {@link edu.ntnu.iir.bidata.model.DiaryEntry} objects in memory respectively.
   *
   * @param args command-line arguments supplied to the program, not used in this implementation
   * @throws IOException loading and saving entries may cause an error
   */
  public static void main(String[] args) throws IOException {
    try {
      Main program = new Main();
      program.init();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
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
          "Diary is empty. "
          + "Generate sample data?")) {
        diary.addDiaryEntries(EntryFactory.fabricateEntries());
      }
    }

    // create an instance of Ui to handle the main program loop
    // with Prompter, Diary, EntryUi and Formatter as arguments
    EntryUi entryUi = new EntryUi(diary, prompter, formatter);
    CollectionUi collectionUi = new CollectionUi(diary, entryUi, prompter);
    Ui ui = new Ui(prompter, diary, entryUi, collectionUi);

    // run the main program
    run(diary, ui, storage);
  }

  /**
   * Runs the program. This contains the program loop
   *
   * @param diary the diary to hold all {@code DiaryEntry} instances
   * @param ui the Ui instance to interact with the user
   * @param storage storage instance to pass on to {@code shutdown()}
   * @throws IOException writing to disk may throw an exception
   */
  private void run(Diary diary, Ui ui, DiaryStorage storage) throws IOException {
    // run the Ui program loop
    ui.run();
    // shutdown the program
    shutdown(diary, storage);
  }

  private void shutdown(Diary diary, DiaryStorage storage) throws IOException {
    // store diary entries before exiting the program
    storage.writeToFile(diary.getAllDiaryEntries());
  }
}
