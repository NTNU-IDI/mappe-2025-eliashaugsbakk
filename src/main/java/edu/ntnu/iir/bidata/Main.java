package edu.ntnu.iir.bidata;

import edu.ntnu.iir.bidata.UI.Menu;
import edu.ntnu.iir.bidata.database.DiaryDatabase;
import java.io.IOException;

/**
 * Main class.
 */
public class Main {
  /**
   * Main method.
   *
   */
  public static void main(String[] args) throws IOException, InterruptedException {

    // loading the previous diary entries, if they exist.
    DiaryDatabase.loadPreviousEntries();

    // The menu loop begins
    while (true) {
      Menu.menu();
    }
  }
}
