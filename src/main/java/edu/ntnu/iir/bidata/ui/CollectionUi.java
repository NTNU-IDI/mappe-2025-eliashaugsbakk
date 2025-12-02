package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Diary;
import edu.ntnu.iir.bidata.model.DiaryEntry;
import edu.ntnu.iir.bidata.utils.DiaryDistinct;
import edu.ntnu.iir.bidata.utils.DiaryFilter;
import edu.ntnu.iir.bidata.utils.DiarySort;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Handles the user interface for viewing, filtering, and sorting a collection of diary entries.
 *
 * <p>This class provides a menu-driven interface for the user to interact with a collection of
 * {@link DiaryEntry} objects. The user can view the collection, apply filters, sort the collection,
 * and select an entry to perform actions on (read, edit, or delete).
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
  private static final int FILTER_TEXT_CONTAINS = 5;

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

  /**
   * Constructor to create an instance of collectionUi.
   *
   * @param diary the diary containing all diary entries
   * @param entryUi entryUi to handle the user interaction with one diary entry
   * @param prompter used to write and read from and to a terminal
   */
  public CollectionUi(Diary diary, EntryUi entryUi,
      Prompter prompter) {
    this.diary = diary;
    this.entryUi = entryUi;
    this.prompter = prompter;
  }

  /**
   * Starts the collection UI loop.
   *
   * <p>This method displays a menu of options for the user to interact with a collection of diary
   * entries. The user can view the collection, apply filters, sort the collection, or select an
   * entry to perform actions on. The loop continues until the user chooses to exit to the main
   * menu.
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
      prompter.printlnGreen("Your current collection contains %s entries."
          .formatted(currentEntriesString));
      int choice = prompter.promptInt("""
          \t%s - View the current collection
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

  /**
   * Applies one or more filters to the given list of diary entries.
   *
   * <p>This method displays a menu of filter options to the user. The user can choose to filter by
   * author, activity, destination, creation time, or by text content. The method returns a new list
   * containing only the entries that match the filter criteria.
   *
   * @param entries the list of diary entries to filter
   * @return a new list of diary entries that match the filter criteria
   */
  private List<DiaryEntry> applyFilter(List<DiaryEntry> entries) {
    filterLoop:
    while (true) {
      int choice = prompter.promptInt("""
          Choose what filter to apply. Choose done when you are done filtering.
          \t%s - Author
          \t%s - Activity
          \t%s - Destination
          \t%s - Time created
          \t%s - Main text contains...
          \t%s - Done""".formatted(FILTER_AUTHOR, FILTER_ACTIVITY, FILTER_DESTINATION,
          FILTER_TIME_CREATED, FILTER_TEXT_CONTAINS, EXIT_MENU));

      switch (choice) {
        case FILTER_AUTHOR -> {
          String author = prompter.chooseFromList("Author to sort by",
              DiaryDistinct.getDistinct(entries, DiaryEntry::getAuthor));
          entries = DiaryFilter.filter(
              entries, entry -> entry.getAuthor().equals(author));
          prompter.printlnGreen("Filter applied successfully.");
        }
        case FILTER_ACTIVITY -> {
          String activity = prompter.chooseFromList("Activity to sort by",
              DiaryDistinct.getDistinct(entries, DiaryEntry::getActivity));
          entries = DiaryFilter.filter(
              entries, entry -> entry.getActivity().equals(activity));
          prompter.printlnGreen("Filter applied successfully.");
        }
        case FILTER_DESTINATION -> {
          String destination = prompter.chooseFromList("Destination to sort by",
              DiaryDistinct.getDistinct(entries, DiaryEntry::getDestination));
          entries = DiaryFilter.filter(
              entries, entry -> entry.getDestination().equals(destination));
          prompter.printlnGreen("Filter applied successfully.");
        }
        case FILTER_TIME_CREATED -> {
          LocalDateTime timeStart = prompter.chooseTime("Start date");
          LocalDateTime timeStop = prompter.chooseTime("End date");
          if (timeStart.isAfter(timeStop)) {
            prompter.warning("Start date must come before stop date");
            break;
          }
          entries = DiaryFilter.filterByTimeInterval(entries, timeStart, timeStop);
          prompter.printlnGreen("Filter applied successfully.");
        }
        case FILTER_TEXT_CONTAINS -> {
          String searchText = prompter.prompt("Enter the text you want to filter by."
              + " The search ignores whitespace and capitalization.");
          entries = DiaryFilter.filterByContent(entries, searchText);
        }
        case EXIT_MENU -> {
          break filterLoop;
        }
        default -> prompter.warning("Not a valid option");
      }
    }
    return entries;
  }

  /**
   * Sorts the given list of diary entries.
   *
   * <p>This method prompts the user to choose a sorting criterion (rating or time written) and
   * returns a new list sorted according to the user's choice.
   *
   * @param entries the list of diary entries to sort
   * @return a new sorted list of diary entries
   */
  private List<DiaryEntry> sortCollection(List<DiaryEntry> entries) {
    int choice = prompter.promptInt("""
        Sort entries by:
        \t%s. Rating
        \t%s. Time written""".formatted(BY_RATING, BY_TIME_WRITTEN));
    switch (choice) {
      case 1 -> {
        return DiarySort.sort(entries, Comparator.comparing(DiaryEntry::getRating));
      }
      case 2 -> {
        return DiarySort.sort(entries, Comparator.comparing(DiaryEntry::getTimeWritten));
      }
      default -> {
        prompter.warning("Invalid option");
        return entries;
      }
    }
  }

  /**
   * Allows the user to choose a diary entry from a list and perform an action on it.
   *
   * <p>This method prompts the user to select an entry from the given list. Once an entry is
   * selected, the user can choose to read, edit, or delete it.
   *
   * @param entries the list of diary entries to choose from
   */
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
              entries.remove(chosenDiaryEntry);
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
