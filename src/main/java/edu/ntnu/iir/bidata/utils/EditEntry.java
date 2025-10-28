package edu.ntnu.iir.bidata.utils;

import java.util.Scanner;

public class EditEntry {
  public static void edit() {
    Scanner scanner = new Scanner(System.in);
    System.out.println(
        "Search for the Diary Entry to edit by:\n" + "\tTitle\n" + "\tAuthor\n" + "\tDate created");
  }
}
