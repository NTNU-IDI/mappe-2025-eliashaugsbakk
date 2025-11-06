package edu.ntnu.iir.bidata.utils;

import edu.ntnu.iir.bidata.database.DiaryDatabase;
import edu.ntnu.iir.bidata.database.JsonReaderWriter;
import edu.ntnu.iir.bidata.ui.Output;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DiaryEntry {
  private int id;
  private String timeWritten;
  private String timeEdited;
  private String author;
  private String trip;
  private String title;
  private String category;
  private int rating;
  private String text;

  // Empty for Gson to load from data.json
  public DiaryEntry() {
  }

  // Constructor from the UI.start test method
  public DiaryEntry(String author, String trip, String title, String category, int rating,
      String text) {
    // 999 id for test entries
    this.id = 999;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    this.timeWritten = dtf.format(LocalDateTime.now());
    this.timeEdited = dtf.format(LocalDateTime.now());
    this.author = author;
    this.trip = trip;
    this.title = title;
    this.category = category;
    this.rating = rating;
    this.text = text;
  }

  // Constructor for creating new entries
  public DiaryEntry(Scanner scanner) {
    this.id = DiaryDatabase.diaryEntries.size();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    this.timeWritten = dtf.format(LocalDateTime.now());
    setTimeEdited();
    setAuthor(scanner);

    setTrip(scanner);
    setTitle(scanner);
    setCategory(scanner);
    setRating(scanner);
    setText(scanner);
    DiaryDatabase.authors.add(this.author);
    DiaryDatabase.categories.add(this.category);
    JsonReaderWriter.writeToFile();
  }

  public void setTimeEdited() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    this.timeEdited = dtf.format(LocalDateTime.now());
  }

  // getters
  public String getTitle() {
    return title;
  }

  public void setTitle(Scanner scanner) {
    System.out.print("Enter Title: ");
    this.title = scanner.nextLine();
  }

  public int getRating() {
    return rating;
  }

  public void setRating(Scanner scanner) {
    System.out.print("Enter Rating (0 to 100): ");
    try {
      this.rating = scanner.nextInt();
    } catch (InputMismatchException e) {
      Output.redln("Invalid input! Rating must be a number");
      scanner.nextLine();
      setRating(scanner);
    }
    if (rating < 0 || rating > 100) {
      Output.redln("Invalid input! Rating must be between 0 and 100");
      scanner.nextLine();
      setRating(scanner);
    }
  }

  public String getTime() {
    return timeWritten;
  }

  public String getTimeEdited() {
    return timeEdited;
  }

  public String getAuthor() {
    return author;
  }

  // create a new author, or select from list
  public void setAuthor(Scanner scanner) {
    scanner.nextLine();
    Output.clear();
    Output.line();
    Output.redln("Select a previous author, or create a new one by typing your name!");

    ArrayList<String> authors = new ArrayList<>();
    for (int i = 0; i < DiaryDatabase.diaryEntries.size(); i++) {
      authors.add(DiaryDatabase.diaryEntries.get(i).author);
    }
    for (int i = 0; i < authors.size(); i++) {
      System.out.println(i + " : " + authors.get(i));
    }
    System.out.print("-> ");
    String input = scanner.nextLine();
    try {
      int inputInt = Integer.parseInt(input);
      this.author = authors.get(inputInt);
      System.out.println("\n\nWelcome " + author);
    } catch (NumberFormatException ex) {
      System.out.println("\n\nWelcome " + input);
      this.author = input;
    }
  }

  public String getTrip() {
    return trip;
  }

  public void setTrip(Scanner scanner) {
    System.out.print("\nEnter Trip Destination: ");
    this.trip = scanner.nextLine();
  }

  public String getCategory() {
    return category;
  }

  // create a new categories, or select from list
  public void setCategory(Scanner scanner) {
    Output.clear();
    Output.line();
    Output.redln(
        "Select a previous category, or create a new one by typing in the name of the categories.");
    ArrayList<String> categories = new ArrayList<>();
    for (int i = 0; i < DiaryDatabase.diaryEntries.size(); i++) {
      categories.add(DiaryDatabase.diaryEntries.get(i).category);
    }
    for (int i = 0; i < categories.size(); i++) {
      System.out.println(i + " : " + categories.get(i));
    }
    System.out.print("-> ");
    String input = scanner.nextLine();
    try {
      int inputInt = Integer.parseInt(input);
      this.category = categories.get(inputInt);
      System.out.println("\n\n" + this.category + " chosen");
    } catch (NumberFormatException ex) {
      if (input.equals(null)) {
        System.out.println("You need to choose a category");
        setCategory(scanner);
      }
      System.out.println("\n\n" + input + " chosen");
      this.category = input;
    }
  }

  public String getText() {
    return text;
  }

  // input for the diary entry text
  // while loop to get multiple line input
  public void setText(Scanner scanner) {
    scanner.nextLine();
    StringBuilder text = new StringBuilder();
    Output.clear();
    System.out.println("Write your entry, type \"bye\"  to quit:");
    Output.line();
    int line = 0;
    while (true) {
      System.out.print(String.format("%3d", line) + "  ");
      line++;
      String inputText = scanner.nextLine();
      if (inputText.equals("bye")) {
        break;
      }
      text.append("\n").append(inputText);
    }
    JsonReaderWriter.writeToFile();
    scanner.nextLine();
    this.text = text.toString();
  }
}
