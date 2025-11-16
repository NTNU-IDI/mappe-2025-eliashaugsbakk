package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.model.Diary;
public class UI {
  Diary diary;
  Prompter prompter;
  Formatter formatter;

  public UI(Prompter prompter, Formatter formatter, Diary diary) {
    this.prompter = prompter;
    this.formatter = formatter;
    this.diary = diary;
  }

  public void run() {

  }
}
