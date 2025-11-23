package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Diary;
import edu.ntnu.iir.bidata.model.DiaryUtils;

/**
 * Handles the main menu loop of the program, presenting options to
 * write diary entries or work with collections of entries.
 */
public class UI {
  private static final int MAIN_SAVE_AND_EXIT = 0;
  private static final int MAIN_WRITE_ENTRY = 1;
  private static final int MAIN_CREATE_COLLECTION = 2;

  private final Prompter prompter;
  private final CollectionUI collectionUI;
  private final EntryUI entryUI;

  /**
   * Constructs a new UI instance.
   *
   * @param prompter the Prompter object used for console interaction
   * @param diary the Diary object to manage diary entries
   */
  public UI(Prompter prompter, Diary diary, DiaryUtils diaryUtils,
      EntryUI entryUI) {
    this.prompter = prompter;
    this.collectionUI = new CollectionUI(diary, diaryUtils, entryUI, prompter);
    this.entryUI = new EntryUI(diary, diaryUtils, prompter);
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
            \t%s - Save and Exit""".formatted(MAIN_WRITE_ENTRY, MAIN_CREATE_COLLECTION, MAIN_SAVE_AND_EXIT));
      switch (choice) {
        case MAIN_WRITE_ENTRY -> entryUI.writeEntry();
        case MAIN_CREATE_COLLECTION -> collectionUI.collection();
        case MAIN_SAVE_AND_EXIT -> {
          break mainLoop;
        }
        default -> prompter.warning("Not a valid option");
      }
    }
  }
}
