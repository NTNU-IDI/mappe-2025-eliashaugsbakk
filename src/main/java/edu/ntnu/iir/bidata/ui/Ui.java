package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Diary;

/**
 * Handles the main menu loop of the program, presenting options to
 * write diary entries or work with collections of entries.
 */
public class Ui {
  private static final int MAIN_SAVE_AND_EXIT = 0;
  private static final int MAIN_WRITE_ENTRY = 1;
  private static final int MAIN_CREATE_COLLECTION = 2;
  private static final int MAIN_DELETE_ALL_ENTRIES = 3;

  private final Diary diary;
  private final Prompter prompter;
  private final CollectionUi collectionUi;
  private final EntryUi entryUi;

  /**
   * Constructs a new Ui instance.
   *
   * @param prompter the Prompter object used for console interaction
   * @param diary the Diary object to manage diary entries
   */
  public Ui(Prompter prompter, Diary diary,
      EntryUi entryUi, Formatter formatter) {
    this.diary = diary;
    this.prompter = prompter;
    this.collectionUi = new CollectionUi(diary, entryUi, prompter);
    this.entryUi = new EntryUi(diary, prompter, formatter);
  }

  /**
   * Runs the main menu loop until the user chooses to save and exit.
   */
  public void run() {
    mainLoop:
    while (true) {
      int choice = prompter.promptInt("""
            \t%s - Write Entry
            \t%s - Create a collection of entries to:
              \t\t List, Read, Edit or Delete entries.
            \t%s - Delete all entries in the diary
            \t%s - Save and Exit""".formatted(
                MAIN_WRITE_ENTRY, MAIN_CREATE_COLLECTION, MAIN_DELETE_ALL_ENTRIES, MAIN_SAVE_AND_EXIT));
      switch (choice) {
        case MAIN_WRITE_ENTRY -> entryUi.writeEntry();
        case MAIN_CREATE_COLLECTION -> collectionUi.collection();
        case MAIN_DELETE_ALL_ENTRIES -> {
          if (prompter.confirmAction("This will delete all: %s entries in the diary."
              .formatted(diary.getAllDiaryEntries().size()))) {
            diary.deleteAllEntries();
          }
        }
        case MAIN_SAVE_AND_EXIT -> {
          break mainLoop;
        }
        default -> prompter.warning("Not a valid option");
      }
    }
  }
}
