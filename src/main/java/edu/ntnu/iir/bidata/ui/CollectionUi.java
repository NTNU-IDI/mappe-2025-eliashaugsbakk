package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Diary;
import edu.ntnu.iir.bidata.model.DiaryEntry;
import edu.ntnu.iir.bidata.model.DiaryUtils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the creation and editing of a collection of {@link DiaryEntry} holding them in an
 * {@link ArrayList} to allow for sorting and for fast random accessibility.
 * Also provides the user with options to interact with one of the entries in their collection.
 */
public class CollectionUi {

  // exit menu
  private static final int EXIT_MENU = 0;

  // options to choose
  private static final int COLL_PRINT_COLLECTION = 1;
  private static final int COLL_APPLY_FILTER = 2;
  private static final int COLL_SORT_COLLECTION = 3;
  private static final int COLL_CHOOSE_ENTRY = 4;

  // filters to apply
  private static final int FILTER_AUTHOR = 1;
  private static final int FILTER_ACTIVITY = 2;
  private static final int FILTER_DESTINATION = 3;
  private static final int FILTER_TIME_CREATED = 4;

  // sort entries
  private static final int BY_RATING = 1;
  private static final int BY_TIME_WRITTEN = 2;

  // diary actions
  private static final int READ_ENTRY = 1;
  private static final int EDIT_ENTRY = 2;
  private static final int DELETE_ENTRY = 3;


  private final Diary diary;
  private final EntryUi entryUi;
  private final Prompter prompter;

  CollectionUi(Diary diary, EntryUi entryUi,
      Prompter prompter) {
    this.diary = diary;
    this.entryUi = entryUi;
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
    List<DiaryEntry> collection = new ArrayList<>(diary.getAllDiaryEntries().values());
    collectionLoop:
    while (true) {

      int currentEntries = collection.size();
      String currentEntriesString = String.valueOf(currentEntries);

      if (currentEntries == 0) {
        currentEntriesString = "no";
      } else if (currentEntries == diary.getAllDiaryEntries().size()) {
        currentEntriesString = "all";
      }
      prompter.printlnRed("Your current collection contains %s entries."
          .formatted(currentEntriesString));
      int choice = prompter.promptInt("""
          \t%s - Print the current collection
          \t%s - Apply a filter to the collection
          \t%s - Sort the current collection
          \t%s - Pick an entry from the collection to:\s
              \t\t Read, Edit or Delete an entry
          \t%s - Return to Main menu""".formatted(
          COLL_PRINT_COLLECTION, COLL_APPLY_FILTER,
          COLL_SORT_COLLECTION, COLL_CHOOSE_ENTRY, EXIT_MENU));

      switch (choice) {
        case COLL_PRINT_COLLECTION -> prompter.printListOfEntries(collection);
        case COLL_APPLY_FILTER ->   collection = applyFilter(collection);
        case COLL_SORT_COLLECTION -> collection = sortCollection(collection);
        case COLL_CHOOSE_ENTRY -> chooseEntry(collection);
        case EXIT_MENU -> {
          if (prompter.confirmAction("This action will reset your current collection.")) {
            break collectionLoop;
          }
        }
        default -> prompter.warning("Not a valid option");
      }
    }
  }

  private List<DiaryEntry> applyFilter(List<DiaryEntry> entries) {
    filterLoop:
    while (true) {
      int choice = prompter.promptInt("""
          Choose what filter to apply:
          \t%s - Author
          \t%s - Activity
          \t%s - Destination
          \t%s - Time created
          \t%s - Done""".formatted(FILTER_AUTHOR, FILTER_ACTIVITY, FILTER_DESTINATION,
          FILTER_TIME_CREATED, EXIT_MENU));

      switch (choice) {
        case FILTER_AUTHOR -> {
          String author = prompter.chooseFromList("Author to sort by", DiaryUtils.getDistinctAuthors(entries));
          entries = DiaryUtils.filterByAuthor(entries, author);
          prompter.println("Filter applied successfully.");
        }
        case FILTER_ACTIVITY -> {
          String activity = prompter.chooseFromList("Activity to sort by", DiaryUtils.getDistinctActivities(entries));
          entries = DiaryUtils.filterByActivity(entries, activity);
          prompter.println("Filter applied successfully.");
        }
        case FILTER_DESTINATION -> {
          String destination = prompter.chooseFromList("Destination to sort by", DiaryUtils.getDistinctDestinations(entries));
          entries = DiaryUtils.filterByDestination(entries, destination);
          prompter.println("Filter applied successfully.");
        }
        case FILTER_TIME_CREATED -> {
          LocalDateTime timeStart = prompter.chooseTime("Start date");
          LocalDateTime timeStop = prompter.chooseTime("End date");
          if (timeStart.isAfter(timeStop)) {
            prompter.warning("Start date must come before stop date");
            break;
          }
          entries = DiaryUtils.filterByTimeCreated(entries, timeStart, timeStop);
          prompter.println("Filter applied successfully.");
        }
        case EXIT_MENU -> {
          break filterLoop;
        }
        default -> prompter.warning("Not a valid option");
      }
    }
    return entries;
  }

  private List<DiaryEntry> sortCollection(List<DiaryEntry> entries) {
    int choice = prompter.promptInt("""
        Sort entries by:
        \t%s. Rating
        \t%s. Time written""".formatted(BY_RATING, BY_TIME_WRITTEN));
    switch (choice) {
      case 1 -> {
        return DiaryUtils.sortByRating(entries);
      }
      case 2 -> {
        return DiaryUtils.sortByTime(entries);
      }
      default -> {
        prompter.warning("Invalid option");
        return entries;
      }
    }
  }


  private void chooseEntry(List<DiaryEntry> entries) {
    DiaryEntry chosenDiaryEntry = null;
    entryLoop:
    while (true) {
      if (chosenDiaryEntry == null) {
        chosenDiaryEntry = prompter.chooseFromListOfEntries(entries);
      } else {
        prompter.println("Chosen entry: " + chosenDiaryEntry.getTitle());

        int choice = prompter.promptInt("""
          Choose your next action:
          \t%s - Read entry
          \t%s - Edit entry
          \t%s - Delete entry
          \t%s - Done""".formatted(READ_ENTRY, EDIT_ENTRY, DELETE_ENTRY, EXIT_MENU));
        switch (choice) {
          case READ_ENTRY -> entryUi.readEntry(chosenDiaryEntry);
          case EDIT_ENTRY -> entryUi.editEntry(chosenDiaryEntry);
          case DELETE_ENTRY -> {
            if (entryUi.deleteEntry(chosenDiaryEntry)) {
              break entryLoop;
            }
          }
          case EXIT_MENU -> {
            break entryLoop;
          }
          default -> prompter.warning("Invalid option");
        }
      }
    }
  }
}
