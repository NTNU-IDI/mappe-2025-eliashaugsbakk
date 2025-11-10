package edu.ntnu.iir.bidata.actions;

import edu.ntnu.iir.bidata.database.DiaryEntry;
import edu.ntnu.iir.bidata.ui.Output;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditEntry {
  Output output = new Output();

  private DiaryEntry editEntry(Scanner sc, ArrayList<DiaryEntry> diaryEntries) {
    DiaryEntry diaryEntry = null;
    String title = output.choicePrompt(sc, "Write the title of a Diary Entry to edit, or type:", """
        \t1 - to list the last 10 diary entries.
        \t2 - to list the last 10 diary entries of a spesific author.
        \t3 - to list all entries.
        \t0 - exit to menu.
        """);
    try {
      int entryInt = Integer.parseInt(title);
      switch (entryInt) {
        case 1 -> {

        }
        case 2 -> {

        }
        case 3 -> {

        }
        case 0 -> {
          return null;
        }
      }
    } catch (NumberFormatException exception) {
      int counter = 0;
      for (DiaryEntry e : diaryEntries) {
        counter++;
        if (title.contains(e.getTitle())) {
          return e;
        }
      }
      if (counter > 0) {
        output.warning(sc, "Invalid choice.");
        editEntry(sc, diaryEntries);
      }
    }
    return null;
  }

  public List<DiaryEntry> entryEdit(Scanner sc, ArrayList<DiaryEntry> diaryEntries) {
    ArrayList<DiaryEntry> result = new ArrayList<>();
    DiaryEntry diaryEntry = editEntry(sc, diaryEntries);
    if (diaryEntry == null) {
      return null;
    }
    int id = diaryEntry.getId();
    String timeWritten = diaryEntry.getTimeWritten();
    String timeEdited = createTimeEdited();
    String author = createAuthor(sc, diaryEntries, diaryEntry);
    String trip = createTrip(sc, diaryEntry);
    String title = createTitle(sc, diaryEntry);
    String category = createCategory(sc, diaryEntries, diaryEntry);
    int rating = createRating(sc, diaryEntry);
    String text = createText(sc, diaryEntry);

    result.add(diaryEntry);
    result.add(
        new DiaryEntry(id, timeWritten, timeEdited, author, trip, title, category, rating, text));
    return result;
  }


  private String createTimeEdited() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    return dtf.format(LocalDateTime.now());

  }

  private String createAuthor(Scanner sc, ArrayList<DiaryEntry> diaryEntries,
      DiaryEntry diaryEntry) {
    String author;

    ArrayList<String> authors = new ArrayList<>();
    for (DiaryEntry entry : diaryEntries) {
      authors.add(entry.getAuthor());
    }
    StringBuilder authorsString = new StringBuilder();
    for (int i = 0; i < authors.size(); i++) {
      authorsString.append(i).append(" : ").append(authors.get(i)).append("\n");
    }
    String input = output.choicePrompt(sc, """
            Select a previous author, or create a new one by typing your name.
            Hit return to keep previous author:\s""" + diaryEntry.getAuthor(),
        authorsString.toString());
    if (input.trim().isEmpty()) {
      return diaryEntry.getAuthor();
    }
    try {
      int inputInt = Integer.parseInt(input);
      author = authors.get(inputInt);
    } catch (NumberFormatException ex) {
      author = input;
    }
    output.message(sc, "Welcome " + author);
    return author;
  }

  private String createTrip(Scanner sc, DiaryEntry diaryEntry) {
    String result = output.prompt(sc, """
        Enter the trip destination.
        Hit return to keep previous destination:\s""" + diaryEntry.getTrip());
    if (result.trim().isEmpty()) {
      return diaryEntry.getTrip();
    }
    return result;
  }

  private String createTitle(Scanner sc, DiaryEntry diaryEntry) {
    String result = output.prompt(sc, """
        Enter the title.
        Hit return to keep previous title:\s""" + diaryEntry.getTitle());
    if (result.trim().isEmpty()) {
      return diaryEntry.getTitle();
    }
    return result;
  }

  private String createCategory(Scanner sc, ArrayList<DiaryEntry> diaryEntries,
      DiaryEntry diaryEntry) {
    String category;

    ArrayList<String> categories = new ArrayList<>();
    for (DiaryEntry entry : diaryEntries) {
      categories.add(entry.getCategory());
    }
    StringBuilder authorsString = new StringBuilder();
    for (int i = 0; i < categories.size(); i++) {
      authorsString.append(i).append(" : ").append(categories.get(i)).append("\n");
    }
    String input = output.choicePrompt(sc, """
            Select a previous category, or create a new one by typing your name.
            Hit return to keep previous category:\s""" + diaryEntry.getCategory(),
        authorsString.toString());
    if (input.trim().isEmpty()) {
      return diaryEntry.getCategory();
    }
    try {
      int inputInt = Integer.parseInt(input);
      category = categories.get(inputInt);
    } catch (NumberFormatException ex) {
      category = input;
    }
    output.message(sc, "Category: " + category);
    return category;
  }

  private int createRating(Scanner sc, DiaryEntry diaryEntry) {
    String result = output.prompt(sc, """
        Enter the rating.
        Hit return to keep previous rating:\s""" + diaryEntry.getRating());
    if (result.trim().isEmpty()) {
      return diaryEntry.getRating();
    }
    return Integer.parseInt(result);
  }

  private String createText(Scanner sc, DiaryEntry diaryEntry) {
    StringBuilder text = new StringBuilder();

    output.message(sc, """
        To keep the previous entry text, type only "bye" when prompted.
        Hit return to wiew the previous entry text.""");
    output.message(sc, diaryEntry.getText());
    output.message(sc, """
                Write your entry, type \"bye\" to quit.
                Hit return to begin.
        """);

    int line = 0;
    while (true) {
      System.out.print(String.format("%3d", line) + "  ");
      String inputText = sc.nextLine();
      line++;

      if (inputText.equalsIgnoreCase("bye")) {
        break;
      }
      text.append("\n").append(inputText);
    }
    if (text.toString().trim().isEmpty()) {
      return diaryEntry.getText();
    }
    return text.toString();
  }
}
