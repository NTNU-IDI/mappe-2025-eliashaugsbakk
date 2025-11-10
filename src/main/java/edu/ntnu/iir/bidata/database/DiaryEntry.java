package edu.ntnu.iir.bidata.database;

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

  public DiaryEntry(int id, String timeWritten, String timeEdited, String author, String trip,
      String title, String category, int rating, String text) {
    this.id = id;
    this.timeWritten = timeWritten;
    this.timeEdited = timeEdited;
    this.author = author;
    this.trip = trip;
    this.title = title;
    this.category = category;
    this.rating = rating;
    this.text = text;
  }

  public int getId() {
    return id;
  }

  public String getTimeWritten() {
    return timeWritten;
  }

  public void setTimeWritten(String time) {
    this.timeWritten = time;
  }

  public String getTimeEdited() {
    return timeEdited;
  }

  public void setTimeEdited(String timeEdited) {
    this.timeEdited = timeEdited;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getTrip() {
    return trip;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCategory() {
    return category;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public String getText() {
    return text;
  }
}
