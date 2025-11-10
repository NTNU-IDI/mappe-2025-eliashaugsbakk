package edu.ntnu.iir.bidata.ui;

import java.util.Scanner;

/**
 * Menu class to hold the menu method.
 */
public class Menu {
  public static final int EXIT = 0;
  public static final int CREATE_ENTRY = 1;
  public static final int EDIT_ENTRY = 2;
  public static final int LIST_ENTRIES_BY = 3;
  public static final int READ_ENTRY = 4;

  public int menu(Scanner sc) throws InterruptedException {
    Output output = new Output();
    output.clear();
    output.line(35);
    try {
      int choice = Integer.parseInt(output.choicePrompt(sc, "Choose your next action: ", """
           \t1. New Diary Entry
           \t2. Edit Diary Entry
           \t3. List entries by..
           \t4. Read Diary Entry
           \t0. Save and exit
          """));
      if (choice < 0 || READ_ENTRY < choice) {
        output.message(sc, "Invalid choice");
        menu(sc);
      }
      return choice;
    } catch (NumberFormatException e) {
      output.message(sc, "Invalid choice.");
      menu(sc);
    }
    return -1;
  }
}
