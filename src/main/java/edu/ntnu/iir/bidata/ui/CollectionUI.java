package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Diary;
import edu.ntnu.iir.bidata.model.DiaryEntry;
import edu.ntnu.iir.bidata.model.DiaryUtils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the creation and editing of a collection of {@link DiaryEntry} holding them in an
 * {@link ArrayList}.
 * Also provides the user with options to interact with one of the entries in their collection.
 */
public class CollectionUI {

  // options to choose
  private static final int COLL_RETURN_TO_MAIN_MENU = 0;
  private static final int COLL_PRINT_COLLECTION = 1;
  private static final int COLL_APPLY_FILTER = 2;
  private static final int COLL_SORT_COLLECTION = 3;
  private static final int COLL_CHOOSE_ENTRY = 4;

  // filters to apply
  private static final int FILTER_AUTHOR = 1;
  private static final int FILTER_ACTIVITY = 2;
  private static final int FILTER_DESTINATION = 3;
  private static final int FILTER_TIME_CREATED = 4;
  private static final int FILTER_NO_FILTER = 0;

  Diary diary;
  DiaryUtils diaryUtils;
  Prompter prompter;

  CollectionUI(Diary diary, DiaryUtils diaryUtils, Prompter prompter) {
    this.diary = diary;
    this.diaryUtils = diaryUtils;
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
        case COLL_PRINT_COLLECTION -> prompter.printListOfDiaries(collection);
        case COLL_APPLY_FILTER ->   applyFilter(collection);
        case COLL_SORT_COLLECTION -> sortCollection(collection);
        case COLL_CHOOSE_ENTRY -> {
        }
        case COLL_RETURN_TO_MAIN_MENU -> {
          if (prompter.confirmAction("This action will reset your current collection.")) {
            break collLoop;
          }
        }
        default -> prompter.warning("Not a valid option");
      }
    }
  }

  private void applyFilter(ArrayList<DiaryEntry> entries) {
    filterLoop:
    while (true) {
      int choice = prompter.promptInt("""
          Choose what filter to apply:
          \t%s - Author
          \t%s - Activity
          \t%s - Destination
          \t%s - Time created
          \t%s - Done""".formatted(FILTER_AUTHOR, FILTER_ACTIVITY, FILTER_DESTINATION,
          FILTER_TIME_CREATED, FILTER_NO_FILTER));

      switch (choice) {
        case FILTER_AUTHOR -> {
          String author = prompter.chooseFromList(diaryUtils.getDistinctAuthors(entries));
          diaryUtils.filterByAuthor(entries, author);
        }
        case FILTER_ACTIVITY -> {
          String activity = prompter.chooseFromList(diaryUtils.getDistinctActivities(entries));
          diaryUtils.filterByActivity(entries, activity);
        }
        case FILTER_DESTINATION -> {
          String destination = prompter.chooseFromList(diaryUtils.getDistinctDestinations(entries));
          diaryUtils.filterByDestination(entries, destination);
        }
        case FILTER_TIME_CREATED -> {
          LocalDateTime timeStart = prompter.chooseTime("Start time");
          LocalDateTime timeStop = prompter.chooseTime("End time");

          diaryUtils.filterByTimeCreated(entries, timeStart, timeStop);
        }
        case FILTER_NO_FILTER -> {
          break filterLoop;
        }
        default -> prompter.warning("Not a valid option");
      }
    }
  }

  private void sortCollection(List<DiaryEntry> entries) {
    int choice = prompter.promptInt("""
        Sort entries by:
        \t1. Rating
        \t2. Time written""");
    switch (choice) {
      case 1 -> diaryUtils.sortByRating(entries);
      case 2 -> diaryUtils.sortByTime(entries);
      default -> prompter.warning("Invalid option");
    }
  }
}
