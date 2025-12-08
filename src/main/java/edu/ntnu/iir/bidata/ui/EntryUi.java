package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Diary;
import edu.ntnu.iir.bidata.model.DiaryEntry;
import edu.ntnu.iir.bidata.utils.DiaryDistinct;

/**
 * Class to handle all user actions related to handling one instance of a {@link DiaryEntry}. This
 * includes: - Creating a new entry - Editing an existing entry - Reading an entry - Deleting an
 * entry
 */
public class EntryUi {
  private static final int EXIT_LOOP = 0;
  private static final int EDIT_AUTHOR = 1;
  private static final int EDIT_DESTINATION = 2;
  private static final int EDIT_ACTIVITY = 3;
  private static final int EDIT_RATING = 4;
  private static final int EDIT_TITLE = 5;
  private static final int EDIT_TEXT = 6;

  private final Diary diary;
  private final Prompter prompter;
  private final Formatter formatter;

  /**
   * Constructor for the EntryUi class.
   *
   * @param diary     The diary to interact with.
   * @param prompter  The prompter to use for program output and user input.
   * @param formatter The formatter to use for formatting data.
   */
  public EntryUi(Diary diary, Prompter prompter, Formatter formatter) {
    this.diary = diary;
    this.prompter = prompter;
    this.formatter = formatter;
  }

  /**
   * Displays the details of a diary entry to the user.
   *
   * @param entry The diary entry to read. If the entry is null, the method will do nothing.
   */
  public void readEntry(DiaryEntry entry) {
    try {
      if (entry == null) {
        // no entry was selected, probably because the diary is empty
        return;
      }
      prompter.message(formatter.formatDiaryEntry(entry));
    } catch (RuntimeException e) {
      prompter.warning(e.getMessage());
    }
  }

  /**
   * Prompts the user for the information needed to create a new diary entry and adds it to the
   * diary.
   *
   * <p>The user is asked for author, destination, title, activity, a rating
   * between 0 and 10, and the main text body (multiline). The method keeps prompting until a valid
   * rating is entered.</p>
   *
   * @throws IllegalArgumentException if an entry with the same title already exists
   */
  public void writeEntry() {
    String author = prompter.chooseFromListOrWriteNew("Enter the author",
        DiaryDistinct.getDistinct(diary.getAllDiaryEntries().values(), DiaryEntry::getAuthor));
    prompter.println("Author: " + author);

    String destination = prompter.chooseFromListOrWriteNew(
        "Enter the destination of your " + "travels or the general travel context.",
        DiaryDistinct.getDistinct(diary.getAllDiaryEntries().values(), DiaryEntry::getDestination));
    prompter.println("Destination: " + destination);

    double rating = setRating();
    prompter.println("Rating: " + rating);

    String title = setTitle();
    prompter.println("Title: " + title);

    String activity = prompter.chooseFromListOrWriteNew("Enter the activity related to your entry.",
        DiaryDistinct.getDistinct(diary.getAllDiaryEntries().values(), DiaryEntry::getActivity));
    activity = activity.toLowerCase();
    prompter.println("Activity: " + activity);

    String text = prompter.multipleLinePrompt("Write the main body of your diary entry.");
    diary.addDiaryEntry(new DiaryEntry(author, destination, activity, rating, title, text));
    prompter.message("DiaryEntry created successfully.");
  }

  /**
   * Prompts the user to enter a rating for a diary entry. The method keeps prompting until a valid
   * rating is entered.
   *
   * @return the rating entered by the user
   */
  private double setRating() {
    double rating;
    while (true) {
      try {
        rating = prompter.promptDouble("Enter your rating (0.0 - 10.0) of the activity");
        if (rating < 0 || rating > 10) {
          prompter.warning("Rating must be between 0 and 10.");
        } else {
          return rating;
        }
      } catch (NumberFormatException e) {
        prompter.warning("Input must be a number");
      }
    }
  }

  /**
   * Prompts the user to enter a title for a diary entry. The method keeps prompting until a unique
   * title is entered.
   *
   * @return the title entered by the user
   */
  private String setTitle() {
    while (true) {
      String title = prompter.prompt("Write the title of your entry");

      if (diary.getAllDiaryEntries().containsKey(title)) {
        prompter.warning("Diary entry title has to be unique.");
      } else {
        return title;
      }
    }
  }

  /**
   * Allows the user to edit the contents of a diary entry.
   *
   * @param entry the DiaryEntry to edit
   */
  public void editEntry(DiaryEntry entry) {
    editLoop:
    while (true) {
      prompter.println(formatter.formatDiaryEntry(entry));
      int choice = prompter.promptInt("""
          \t%s - edit Author
          \t%s - edit Destination
          \t%s - edit Activity
          \t%s - edit Rating
          \t%s - edit Title
          \t%s - edit Text
          \t%s - done""".formatted(EDIT_AUTHOR, EDIT_DESTINATION, EDIT_ACTIVITY, EDIT_RATING,
          EDIT_TITLE, EDIT_TEXT, EXIT_LOOP));
      switch (choice) {
        case EDIT_AUTHOR -> editAuthor(entry);
        case EDIT_DESTINATION -> editDestination(entry);
        case EDIT_ACTIVITY -> editActivity(entry);
        case EDIT_RATING -> editRating(entry);
        case EDIT_TITLE -> editTitle(entry);
        case EDIT_TEXT -> editText(entry);
        case EXIT_LOOP -> {
          break editLoop;
        }
        default -> prompter.warning("Not a valid input.");
      }
    }
  }

  /**
   * Prompts the user to edit the author of a diary entry.
   *
   * @param entry the diary entry to edit
   */
  private void editAuthor(DiaryEntry entry) {
    prompter.println("Current author: " + entry.getAuthor());
    entry.setAuthor(prompter.chooseFromListOrWriteNew("Enter or select new author: ",
        DiaryDistinct.getDistinct(diary.getAllDiaryEntries().values(), DiaryEntry::getAuthor)));
    prompter.println("Author: " + entry.getAuthor());
  }

  /**
   * Prompts the user to edit the destination of a diary entry.
   *
   * @param entry the diary entry to edit
   */
  private void editDestination(DiaryEntry entry) {
    prompter.println("Current destination: " + entry.getDestination());
    entry.setDestination(prompter.chooseFromListOrWriteNew("Enter new destination: ",
        DiaryDistinct.getDistinct(diary.getAllDiaryEntries().values(),
            DiaryEntry::getDestination)));
    prompter.println("Destination: " + entry.getDestination());
  }

  /**
   * Prompts the user to edit the activity of a diary entry.
   *
   * @param entry the diary entry to edit
   */
  private void editActivity(DiaryEntry entry) {
    prompter.println("Current activity: " + entry.getActivity());
    entry.setActivity(prompter.chooseFromListOrWriteNew("Enter new activity: ",
        DiaryDistinct.getDistinct(diary.getAllDiaryEntries().values(), DiaryEntry::getActivity)));
    prompter.println("Activity: " + entry.getActivity());
  }

  /**
   * Prompts the user to edit the rating of a diary entry.
   *
   * @param entry the diary entry to edit
   */
  private void editRating(DiaryEntry entry) {
    prompter.println("Current rating: " + entry.getRating());
    entry.setRating(setRating());
    prompter.println("Rating: " + entry.getRating());
  }

  /**
   * Prompts the user to edit the title of a diary entry.
   *
   * @param entry the diary entry to edit
   */
  private void editTitle(DiaryEntry entry) {
    prompter.println("Current title: " + entry.getTitle());
    // remove the old mapping, update title then, add it back to ensure the Map key is correct
    diary.deleteEntry(entry);
    entry.setTitle(setTitle());
    diary.addDiaryEntry(entry);
    prompter.println("Title: " + entry.getTitle());
  }

  /**
   * Prompts the user to edit the text of a diary entry.
   *
   * @param entry the diary entry to edit
   */
  private void editText(DiaryEntry entry) {
    prompter.println("Current text:\n\n" + entry.getText());
    entry.setText(prompter.multipleLinePrompt("Write the new main body of your diary entry."));
    prompter.message("Text has been updated");
  }

  /**
   * Deletes a diary entry after confirming with the user.
   *
   * @param entry the entry to delete
   * @return true if the entry was deleted, false otherwise
   */
  public boolean deleteEntry(DiaryEntry entry) {
    if (prompter.confirmAction("You are about to delete the diary entry: " + entry.getTitle())) {
      diary.deleteEntry(entry);
      prompter.message("Entry has been deleted.");
      return true;
    } else {
      prompter.message("No entry was deleted.");
      return false;
    }
  }
}
