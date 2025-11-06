package edu.ntnu.iir.bidata.utils;

import edu.ntnu.iir.bidata.database.DiaryDatabase;
import edu.ntnu.iir.bidata.ui.Menu;
import edu.ntnu.iir.bidata.ui.Output;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Function;

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
      case 1 -> byDate();
      case 2 -> sortByRating(DiaryDatabase.diaryEntries, "All entries listed by rating");
      case 3 -> topRatingAuthor(scanner);
      case 4 -> topRatingCategory(scanner);
      case 5 -> topRatingTrip(scanner);
      //case 6 -> topCategory();
      //case 7 -> topTrip();
      default -> System.out.println("Invalid choice");
    }
  }

  static void topRatingAuthor(Scanner scanner) throws InterruptedException {
    printChoices(DiaryEntry::getAuthor);
    ArrayList<DiaryEntry> list = userChoice(DiaryEntry::getAuthor, scanner);
    sortByRating(list, "Author listed in order of rating");
  }

  static void topRatingCategory(Scanner scanner) throws InterruptedException {
    printChoices(DiaryEntry::getCategory);
    ArrayList<DiaryEntry> list = userChoice(DiaryEntry::getCategory, scanner);
    sortByRating(list, "Categry listed in order of rating");
  }

  static void topRatingTrip(Scanner scanner) throws InterruptedException {
    printChoices(DiaryEntry::getTrip);
    ArrayList<DiaryEntry> list = userChoice(DiaryEntry::getTrip, scanner);
    sortByRating(list, "Trip listed in order of rating");
  }

  static void sortByRating(ArrayList<DiaryEntry> list, String message) {
    ArrayList<DiaryEntry> sortedList = new ArrayList<>(list);
    sortedList.sort(Comparator.comparingInt(DiaryEntry::getRating));
    print(sortedList, message);
  }


  public static <T> void printChoices(Function<DiaryEntry, T> extractor) {
    for (int i = 0; i < DiaryDatabase.diaryEntries.size(); i++) {
      T value = extractor.apply(DiaryDatabase.diaryEntries.get(i));
      System.out.println(i + " : " + value);
    }
  }

  public static <T> ArrayList<DiaryEntry> userChoice(Function<DiaryEntry, T> extractor,
      Scanner scanner) throws InterruptedException {

    System.out.println();
    Output.redln("Please write a choice, or select one from the list");
    Output.red("--> ");

    String choice = "";
    if (scanner.hasNextInt()) {
      int choiceInt = scanner.nextInt();
      T value = extractor.apply(DiaryDatabase.diaryEntries.get(choiceInt));
      choice = String.valueOf(value);
      System.out.println("Selected: " + choice);
    } else {
      scanner.nextLine();
      choice = scanner.nextLine();
      System.out.println("Selected: " + choice);
    }

    Output.clear();
    ArrayList<DiaryEntry> returnList = new ArrayList<>();

    for (DiaryEntry diaryEntry : DiaryDatabase.diaryEntries) {
      if (extractor.apply(diaryEntry).equals(choice)) {
        returnList.add(diaryEntry);
      }
    }
    if (returnList.isEmpty()) {
      Output.red("No match found");
      scanner.nextLine();
      Menu.menu(scanner);
    }
    return returnList;
  }

  static void print(ArrayList<DiaryEntry> entries, String message) {
    Output.line();
    Output.red(String.format("%-10s", "Rating"));
    Output.red(String.format("%-20s", "Title"));
    Output.red(String.format("%-15s", "Author"));
    Output.red("Written" + "\n");
    for (DiaryEntry diaryEntry : entries) {
      String rating = String.valueOf(diaryEntry.getRating());
      String title = diaryEntry.getTitle();
      String author = diaryEntry.getAuthor();
      String time = diaryEntry.getTime();
      DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
      LocalDateTime dateTime = LocalDateTime.parse(String.valueOf(time), inputFormat);
      DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      time = dateTime.format(outputFormat);

      System.out.printf("%-10s", rating + "%");
      System.out.printf("%-20s", title);
      System.out.printf("%-15s", author);
      System.out.printf(time + "\n");
    }
    Output.line();
    Output.red(message);
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine();
  }

  public static void byDate() {
    ArrayList<DiaryEntry> sortedList = new ArrayList<>(DiaryDatabase.diaryEntries);
    sortedList.sort(Comparator.comparing(DiaryEntry::getTime));
    print(sortedList, "All entries listed by Date");
  }
}
