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
    String title = setTitle();
    String activity = prompter.prompt("Enter the activity related to your entry.");
    activity = activity.toLowerCase();
    double rating;
    while (true) {
      try {
        rating = Double.parseDouble(prompter.prompt("Enter your rating (0 - 10) of the activity"));
        if (rating < 0 || rating > 10) {
          prompter.warning("Rating must be between 0 and 10.");
        } else {
          break;
        }
      } catch (NumberFormatException e) {
        prompter.warning("Input must be a number");
      }
    }
    String text = prompter.multipleLinePrompt("Write the main body of your diary entry.");
    diary.addDiaryEntry(new DiaryEntry(author, destination, activity, rating, title, text));
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
    // TODO: Write editEntry
  }

  /**
   * Method to delete a DiaryEntry.
   *
   * @param entry the DiaryEntry to delet.
   */
  public void deleteEntry(DiaryEntry entry) {
    // TODO: Write deleteEntry
  }
}
