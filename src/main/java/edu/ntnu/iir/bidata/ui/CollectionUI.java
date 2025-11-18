package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Diary;
import edu.ntnu.iir.bidata.model.DiaryEntry;
import java.util.ArrayList;

/**
 * Handles the creation and editing of a collection of {@link DiaryEntry} holding them in an
 * {@link ArrayList}.
 * Also provides the user with options to interact with one of the entries in their collection.
 */
public class CollectionUI {

  private static final int COLL_RETURN_TO_MAIN_MENU = 0;
  private static final int COLL_PRINT_COLLECTION = 1;
  private static final int COLL_APPLY_FILTER = 2;
  private static final int COLL_SORT_COLLECTION = 3;
  private static final int COLL_CHOOSE_ENTRY = 4;

  Diary diary;
  Prompter prompter;

  CollectionUI(Diary diary, Prompter prompter) {
    this.diary = diary;
    this.prompter = prompter;
  }

  /**
   * Creates a collection of all Diary Entries for the user to interact with.
   * Provides a menu for the user to choose what to do. The options are to:
   *  - View the current collection of entries.
   *  - Apply a filter to the current list of entries.
   *  - Sort the current list of entries.
   *  - Choose an entry to choose an entry to: Read, Edit or Delete.
   *  - Exit to the main menu.
   */
  public void collection() {
    // creates a list with all diary entries for the user to filter and sort
    ArrayList<DiaryEntry> collection = new ArrayList<>(diary.getAllDiaryEntries());
    collLoop:
    while (true) {
      int currentEntries = collection.size();
      int[] currentFilters = null;
      String currentEntriesString = String.valueOf(currentEntries);

      if (currentEntries == 0) {
        currentEntriesString = "no";
      } else if (currentEntries == diary.getAllDiaryEntries().size()) {
        currentEntriesString = "all";
      }
      int choice = prompter.promptInt("""
          Your current collection contains %s entries.
          \t%s - Print the current collection
          \t%s - Apply a filter to the collection
          \t%s - Sort the current collection
          \t%s - Pick an entry to\s
              \t\t Read, Edit or Delete an entry
          \t%s - Return to Main menu""".formatted(
          currentEntriesString, COLL_PRINT_COLLECTION, COLL_APPLY_FILTER,
          COLL_SORT_COLLECTION, COLL_CHOOSE_ENTRY, COLL_RETURN_TO_MAIN_MENU));

      switch (choice) {
        case COLL_PRINT_COLLECTION -> {}
        case COLL_APPLY_FILTER -> {}
        case COLL_SORT_COLLECTION -> {}
        case COLL_CHOOSE_ENTRY -> {}
        case COLL_RETURN_TO_MAIN_MENU -> {
          if (prompter.confirmAction("This action will reset your current collection.")) {
            break collLoop;
          }
        }
        default -> prompter.warning("Not a valid option");
      }

    }
  }
}
