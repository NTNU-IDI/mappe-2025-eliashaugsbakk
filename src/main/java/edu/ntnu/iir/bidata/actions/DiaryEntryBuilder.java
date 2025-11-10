package edu.ntnu.iir.bidata.actions;

import edu.ntnu.iir.bidata.database.DiaryEntry;
import edu.ntnu.iir.bidata.ui.Output;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class DiaryEntryBuilder {
  Output output = new Output();

  public DiaryEntry createNewEntry(Scanner sc, ArrayList<DiaryEntry> diaryEntries) {
    int id = createID(diaryEntries);
    String timeWritten = createTimeWritten();
    String timeEdited = createTimeEdited();
    String author = createAuthor(sc, diaryEntries);
    String trip = createTrip(sc);
    String title = createTitle(sc);
    String category = createCategory(sc, diaryEntries);
    int rating = createRating(sc);
    String text = createText(sc);

    return new DiaryEntry(id, timeWritten, timeEdited, author, trip, title, category, rating, text);
  }

  private int createID(ArrayList<DiaryEntry> diaryEntries) {
    return diaryEntries.size() + 1;
  }

  private String createTimeWritten() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    return dtf.format(LocalDateTime.now());
  }

  private String createTimeEdited() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    return dtf.format(LocalDateTime.now());
  }

  private String createAuthor(Scanner sc, ArrayList<DiaryEntry> diaryEntries) {
    String author;


    ArrayList<String> authors = new ArrayList<>();
    for (DiaryEntry diaryEntry : diaryEntries) {
      authors.add(diaryEntry.getAuthor());
    }
    String authorsString = "";
    for (int i = 0; i < authors.size(); i++) {
      authorsString += (i + " : " + authors.get(i) + "\n");
    }
    String input = output.choicePrompt(sc,
        "Select a previous author, or create a new one by typing your name:", authorsString);
    try {
      int inputInt = Integer.parseInt(input);
      author = authors.get(inputInt);
    } catch (NumberFormatException ex) {
      author = input;
    }
    output.message(sc, "Welcome " + author);
    return author;
  }

  private String createTrip(Scanner sc) {
    return output.prompt(sc, "Enter the trip destination:");
  }

  private String createTitle(Scanner sc) {
    return output.prompt(sc, "Enter the entry Title");
  }

  private String createCategory(Scanner sc, ArrayList<DiaryEntry> diaryEntries) {
    String category;

    output.prompt(sc, "Select a previous category, or create a new one by typing your name.");

    ArrayList<String> categories = new ArrayList<>();
    for (DiaryEntry diaryEntry : diaryEntries) {
      categories.add(diaryEntry.getAuthor());
    }
    for (int i = 0; i < categories.size(); i++) {
      System.out.println(i + " : " + categories.get(i));
    }
    System.out.print("-> ");
    String input = sc.nextLine();
    try {
      int inputInt = Integer.parseInt(input);
      category = categories.get(inputInt);
    } catch (NumberFormatException ex) {
      category = input;
    }
    output.message(sc, "Category: " + category);
    return category;
  }

  private int createRating(Scanner sc) {
    int rating = -1;
    String ratingString = output.prompt(sc, "Choose a rating from 0 to 100:");
    try {
      rating = Integer.parseInt(ratingString);
      if (rating < 0 || 100 < rating) {
        createRating(sc);
      }
    } catch (NumberFormatException e) {
      createRating(sc);
    }
    return rating;
  }

  private String createText(Scanner sc) {

    StringBuilder text = new StringBuilder();
    output.message(sc, "Write your entry, type \"bye\"  to quit:");
    int line = 0;
    while (true) {
      System.out.print(String.format("%3d", line) + "  ");
      line++;
      String inputText = sc.nextLine();
      if (inputText.equals("bye")) {
        break;
      }
      text.append("\n").append(inputText);
    }
    sc.nextLine();
    return text.toString();
  }
}
