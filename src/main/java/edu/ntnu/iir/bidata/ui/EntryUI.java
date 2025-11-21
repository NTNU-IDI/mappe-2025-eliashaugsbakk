package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Diary;
import edu.ntnu.iir.bidata.model.DiaryEntry;

/**
 * Class to handle all user actions related to handling one instance of a diary entry.
 * This includes:
 *  - Creating a new entry
 *  - Editing an existing entry
 *  - Reading an entry
 *  - Deleting an entry
 */
public class EntryUI {
  private static final int EXIT_LOOP = 0;
  private static final int EDIT_AUTHOR = 1;
  private static final int EDIT_DESTINATION = 2;
  private static final int EDIT_ACTIVITY = 3;
  private static final int EDIT_RATING = 4;
  private static final int EDIT_TITLE = 5;
  private static final int EDIT_TEXT = 6;

  private final Diary diary;
  private final Prompter prompter;

  /**
   * Constructor to let EntryUI to interact with {@link Prompter}.
   *
   * @param prompter to give output and take input from the user
   */
  public EntryUI(Diary diary, Prompter prompter) {
    this.diary = diary;
    this.prompter = prompter;
  }

  /**
   * Calls on chooseEntry to let the user pick a diary entry to read, then prints out the toString
   * of the chosen DiaryEntry.
   */
  public void readEntry(DiaryEntry entry) {
    try {
      if (entry == null) {
        // no entry was selected, probably because the diary is empty
        return;
      }
      prompter.message(entry.toString());
    } catch (RuntimeException e) {
      prompter.warning(e.getMessage());
    }
  }


  /**
   * Prompts the user for the information needed to create a new diary entry
   * and adds it to the diary.
   *
   * <p>The user is asked for author, destination, title, activity, a rating
   * between 0 and 10, and the main text body (multiline). The method keeps
   * prompting until a valid rating is entered.</p>
   *
   * @throws IllegalArgumentException if an entry with the same title already exists
   */
  public void writeEntry() {
    String author = prompter.prompt("Enter an author.");
    String destination = prompter.prompt("Enter the destination of your "
        + "travels or the general traver context.");
    double rating = setRating();
    String title = setTitle();
    String activity = prompter.prompt("Enter the activity related to your entry.");
    activity = activity.toLowerCase();

    String text = prompter.multipleLinePrompt("Write the main body of your diary entry.");
    diary.addDiaryEntry(new DiaryEntry(author, destination, activity, rating, title, text));
  }

  private double setRating() {
    double rating;
    while (true) {
      try {
        rating = Double.parseDouble(prompter.prompt("Enter your rating (0 - 10) of the activity"));
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

  private String setTitle() {
    String title = prompter.prompt("Write the title of your entry");
    for (DiaryEntry entry : diary.getAllDiaryEntries()) {
      if (entry.getTitle().equalsIgnoreCase(title)) {
        prompter.warning("Diary entry title has to be unique.");
        setTitle();
      }
    }
    return title;
  }

  /**
   * Method to edit the contents of a DiaryEntry.
   *
   * @param entry the DiaryEntry to edit
   */
  public void editEntry(DiaryEntry entry) {
    editLoop:
    while (true) {
      prompter.println(entry.toString());
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

  private void editAuthor(DiaryEntry entry) {
    prompter.println("Current author: " + entry.getAuthor());
    entry.setAuthor(prompter.prompt("Enter new author: "));
  }

  private void editDestination(DiaryEntry entry) {
    prompter.println("Current destination: " + entry.getDestination());
    entry.setDestination(prompter.prompt("Enter new destination: "));
  }

  private void editActivity(DiaryEntry entry) {
    prompter.println("Current activity: " + entry.getActivity());
    entry.setActivity(prompter.prompt("Enter new activity: "));
  }

  private void editRating(DiaryEntry entry) {
    entry.setRating(setRating());
  }

  private void editTitle(DiaryEntry entry) {
    prompter.println("Current title: " + entry.getTitle());
    entry.setText(setTitle());
  }

  private void editText(DiaryEntry entry) {
    prompter.println("Current text:\n\n" + entry.getText());
    entry.setText(prompter.multipleLinePrompt("Write the new main body of your diary entry."));
  }

  /**
   * Asks the user if they want to delete the diary entry. If yes, it deletes and returns true.
   * If not, it returns false without deleting the entry.
   *
   * @param entry the entry to delete
   * @return returs true if the deletion was successful
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
