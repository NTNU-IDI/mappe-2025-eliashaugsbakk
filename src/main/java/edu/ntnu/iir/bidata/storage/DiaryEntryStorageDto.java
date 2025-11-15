package edu.ntnu.iir.bidata.storage;

import edu.ntnu.iir.bidata.model.DiaryEntry;
/**
 * This class creates a data transfer object of {@code DiaryEntry} as a helper for DiaryStorage to
 * store the diary entry to file.
 *
 * <p>The object will be a representation of a {@code DiaryEntry} storing
 * {@code DiaryEntry.timeWritten} and {@code DiaryEntry.timeEdited} as String, not as
 * {@code LocalDateTime}
 */
public class DiaryEntryStorageDto {
  private final String timeWritten;
  private final String timeEdited;
  private final String author;
  private final String destination;
  private final String activity;
  private final double rating;
  private final String title;
  private final String text;

  public DiaryEntryStorageDto(DiaryEntry diaryEntry) {
    this.timeWritten = diaryEntry.getTimeWritten().toString();
    this.timeEdited = diaryEntry.getTimeEdited().toString();
    this.author = diaryEntry.getAuthor();
    this.destination = diaryEntry.getDestination();
    this.activity = diaryEntry.getActivity();
    this.rating = diaryEntry.getRating();
    this.title = diaryEntry.getTitle();
    this.text = diaryEntry.getText();
  }

  /**
   * Returns the timestamp when the diary entry was first written, stored as an ISO-8601 formatted
   * string.
   *
   * @return the time the entry was created, as a string
   */
  public String getTimeWritten() {
    return timeWritten;
  }

  /**
   * Returns the timestamp of the last edit to the diary entry, stored as an ISO-8601 formatted
   * string.
   *
   * @return the time of the last edit, as a string
   */
  public String getTimeEdited() {
    return timeEdited;
  }

  /**
   * Returns the name of the author of the diary entry.
   *
   * @return the author name
   */
  public String getAuthor() {
    return author;
  }

  /**
   * Returns the destination or travel context associated with the diary entry.
   *
   * @return the destination or travel description
   */
  public String getDestination() {
    return destination;
  }

  /**
   * Returns the activity described in the diary entry (for example, {@code "Hiking"} or
   * {@code "Sightseeing"}).
   *
   * @return the activity associated with the entry
   */
  public String getActivity() {
    return activity;
  }

  /**
   * Returns the user's rating of the activity or experience on a scale from 0 to 10.
   *
   * @return the rating value
   */
  public double getRating() {
    return rating;
  }

  /**
   * Returns the title of the diary entry.
   *
   * @return the entry title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Returns the main text content of the diary entry.
   *
   * @return the entry text
   */
  public String getText() {
    return text;
  }

}
