package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.utils.DiaryEntry;
import java.util.ArrayList;
import java.util.Scanner;
public class UI {
  ArrayList<DiaryEntry> testEntries = new ArrayList<>();

  /**
   * The start method contains a test code to initiate some instanses of the DiaryEnry class. It
   */
  public void start() {
    for (int i = 0; i < 4; i++) {
      DiaryEntry newEntry =
          new DiaryEntry("Author" + i, "Trip" + i, "Title" + i, "Category" + i, i * 10,
              "Test entry number : " + i);
      testEntries.add(newEntry);
    }
    printTestEntries();
  }

  public void printTestEntries() {
    Output.clear();
    Output.line();
    Output.redln("Test entries.");
    for (int i = 0; i < testEntries.size(); i++) {
      Output.red("Author: ");
      System.out.println(testEntries.get(i).getAuthor());
      Output.red("Time ceated: ");
      System.out.println(testEntries.get(i).getTime());
      Output.red("Category: ");
      System.out.println(testEntries.get(i).getCategory());
      Output.red("Rating: ");
      System.out.println(testEntries.get(i).getRating());
      System.out.println();
    }
    Output.line();
  }

  public void init() throws InterruptedException {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      Menu.menu(scanner);
    }
  }
}
