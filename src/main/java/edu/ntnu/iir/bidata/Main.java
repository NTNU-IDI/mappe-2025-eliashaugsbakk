package edu.ntnu.iir.bidata;

import edu.ntnu.iir.bidata.manager.Manager;
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

    Manager manager = new Manager();
    manager.init();
  }
}
