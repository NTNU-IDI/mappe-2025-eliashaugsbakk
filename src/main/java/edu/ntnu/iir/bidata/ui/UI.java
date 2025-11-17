package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Diary;
import edu.ntnu.iir.bidata.model.DiaryEntry;

import java.util.ArrayList;

public class UI {
  private static final int SAVE_AND_EXIT = 0;
  private static final int READ_ENTRY = 1;
  private static final int WRITE_ENTRY = 2;

  Diary diary;
  Prompter prompter;
  Formatter formatter;

  public UI(Prompter prompter, Formatter formatter, Diary diary) {
    this.prompter = prompter;
    this.formatter = formatter;
    this.diary = diary;
  }

  public void run() {
    mainLoop:
    while (true) {
      int choice = prompter.promptInt("""
            \t%s - Read Entry
            \t%s - Write Entry
            \t%s - Save and Exit""".formatted(READ_ENTRY, WRITE_ENTRY, SAVE_AND_EXIT));
      switch (choice) {
        case READ_ENTRY -> readEntry();
        case WRITE_ENTRY -> writeEntry();
        case SAVE_AND_EXIT -> { break mainLoop; }
      }
    }
  }

  /**
   * Calls on chooseEntry to let the user pick a diary entry to read, then prints out the toString
   * of the chosen DiaryEntry.
   */
  private void readEntry() {
    try {

      DiaryEntry entry = chooseEntry(diary.getAllDiaryEntries());
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
   * Prompts the user to choose an entry from a list of diary entries.
   *
   * @return A diary entry chosen by the user
   */
  private DiaryEntry chooseEntry(ArrayList<DiaryEntry> entries) {
    if (entries.isEmpty()) {
      prompter.warning("No entries are available");
      return null;
    }

    StringBuilder prompt = new StringBuilder("Choose an entry:\n");
    for (int i = 0; i < entries.size(); i++) {
      prompt.append(i)
            .append(" : ")
            .append(entries.get(i).getTitle())
            .append("\n");
    }

    int choice;
    while (true) {
      choice = prompter.promptInt(prompt.toString());
      if (choice >= 0 && choice < entries.size()) {
        break;
      }
      prompter.warning(STR."Please choose a number between 0 and \{entries.size() - 1}.");
    }
    return entries.get(choice);
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
  private void writeEntry() {
    String author = prompter.prompt("Enter an author.");
    String destination = prompter.prompt("Enter the destination of your travels or the general traver context.");
    String title = prompter.prompt("Enter the Title of your entry.");
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
}
