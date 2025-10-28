package edu.ntnu.iir.bidata.utils;

import edu.ntnu.iir.bidata.UI.Menu;
import edu.ntnu.iir.bidata.UI.Output;
import edu.ntnu.iir.bidata.database.DiaryDatabase;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ListEntries {

  public static void listEntriesBy() throws InterruptedException {
    Scanner scanner = new Scanner(System.in);
    Output.line();
    Output.redln("How do you want to list entries?");
    System.out.println(
        "\t1. By date\n" + "\t2. Overall Rating\n" + "\t3. Top Rating by \"Author\"\n" + "\t4. Top Rating by \"Category\"\n" + "\t5. Top Rating by \"Trip\"\n" + "\t6. Top rated Category\n" + "\t7. Top rated Trips");
    Output.red("--> ");

    int choise = 0;
    try {
      choise = scanner.nextInt();
    } catch (InputMismatchException e) {
      System.out.println("Please enter a valid choice");
    }
    switch (choise) {
      //case 1 ->
      case 2 -> {
        Output.clear();
        topRating();
      }
      case 3 -> {
        Output.clear();
        topRatingByAuthor();
      }
      //case 4 -> topRatingCategory();
      //case 5 -> topRatingTrip();
      //case 6 -> topCategory();
      //case 7 -> topTrip();
      default -> System.out.println("Invalid choice");
    }
  }

  private static void print(ArrayList<DiaryEntry> entries, String message) {
    Output.line();
    Output.red(String.format("%-7s", "Rating") + " ");
    Output.red(String.format("%-20s", "Title") + " ");
    Output.red(String.format("%-15s", "Author") + " ");
    Output.red("Written" + "\n");
    for (DiaryEntry diaryEntry : entries) {
      String rating = String.valueOf(diaryEntry.getRating());
      String title = diaryEntry.getTitle();
      String author = diaryEntry.getAuthor();
      String time = diaryEntry.getTime();

      System.out.print(String.format("%-10s", rating + "%") + " ");
      System.out.print(String.format("%-20s", title) + " ");
      System.out.print(String.format("%-15s", author) + " ");
      System.out.print(time + "\n");
    }
    Output.line();
    Output.red(message);
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine();
  }

  private static void topRating() {
    ArrayList<DiaryEntry> sortedList = new ArrayList<>(DiaryDatabase.diaryEntries);
    sortedList.sort(Comparator.comparingInt(DiaryEntry::getRating));
    print(sortedList, "All entries listed by Rating");
  }

  private static void topRatingByAuthor() throws InterruptedException {
    Scanner scanner = new Scanner(System.in);
    Output.line();
    for (int i = 0; i < DiaryDatabase.authors.size(); i++) {
      System.out.print(i + " : " + DiaryDatabase.authors.get(i));
    }
    Output.redln("Please enter an Author, or select one from the list");
    Output.red("--> ");

    String author = scanner.nextLine();

    Output.clear();
    ArrayList<DiaryEntry> sortedAuthors = new ArrayList<>();
    for (DiaryEntry diaryEntry : DiaryDatabase.diaryEntries) {
      if (diaryEntry.getAuthor().equals(author)) {
        sortedAuthors.add(diaryEntry);

      }
    }
    if (sortedAuthors.isEmpty()) {
      Output.red("Author not found");
      scanner.nextLine();
      Menu.menu();
    }

    ArrayList<DiaryEntry> sortedList = new ArrayList<>(sortedAuthors);
    sortedList.sort(Comparator.comparingInt(DiaryEntry::getRating));
    print(sortedList, "All of " + author + "'s entries listed by Rating");
  }
}

