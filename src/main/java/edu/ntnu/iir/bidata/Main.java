package edu.ntnu.iir.bidata;

import edu.ntnu.iir.bidata.database.JsonReaderWriter;
import edu.ntnu.iir.bidata.ui.UI;
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
    JsonReaderWriter.loadPreviousEntries();

    UI ui = new UI();
    ui.start();
    ui.init();
    // The menu loop begins
  }
}
