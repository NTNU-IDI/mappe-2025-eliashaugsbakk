package edu.ntnu.iir.bidata.model;

import java.time.LocalDateTime;

/**
 * Represents a single diary entry for a travel diary. Each entry contains information about the
 * author, the destination or general travel context, the activity performed, a rating from 0 to 10,
 * a title, and the main content of the entry. The entry also tracks timestamps for when it was
 * first written and last edited.
 *
 * <p>This class provides getters and setters for all fields, including
 * validation for the rating. The {@link #toString()} method returns a formatted string to be
 * displayed in the terminal.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * DiaryEntry entry = new DiaryEntry (
 *     "Alice",
 *     "Backpacking Europe",
 *     "Hiking",
 *     8.5,
 *     "Alps Adventure",
 *     "Have climbed several peaks over three days..."
 * );
 * System.out.println(entry);
 * </pre>
 */
public class DiaryEntry {
  private final LocalDateTime timeWritten;
  private LocalDateTime timeEdited;
  private String author;
  private String destination;
  private String title;
  private String activity;
  private double rating;
  private String text;


  /**
   * General constructor for reconstructing a diary entry (e.g., from storage) where all
   * fields including timestamps are known. Or when fabricating entries from EntryFactory
   */
  public DiaryEntry(LocalDateTime timeWritten, LocalDateTime timeEdited, String author,
      String destination, String activity, double rating, String title, String text) {
    this.timeWritten = timeWritten;
    this.timeEdited = timeEdited;
    this.author = author;
    this.destination = destination;
    this.activity = activity;
    this.rating = checkRating(rating);
    this.title = title;
    this.text = text;
  }

  /**
   * Creates a new diary entry, describing an activity during a trip. This constructor is
   *     for the user to create new entries. Assigns the time created and edited to the
   *     current local time. Rating is stored as a double to allow the user to choose freely.
   *     All other object variables are stored as Strings.
   *
   * @param author      the name of the author
   * @param destination the destination of the trip, or a more general travel (Traveling through
   *                    Europe)
   * @param activity    the activity this entry is describing
   * @param rating      a rating the user assigns to the activity, on a scale from 0 to 10
   * @param title       the title of the entry
   * @param text        the main entry text, usually describing the activity
   */
  public DiaryEntry(String author, String destination, String activity, double rating, String title,
      String text) {
    this.timeWritten = LocalDateTime.now(); // set the time of creation to now
    this.timeEdited = LocalDateTime.now(); // set the time of the last edit to now
    this.author = author; // the string representation of an author, usually a name
    this.destination = destination; // the destination or general travel context of the diary entry
    this.activity = activity; // the activity associated with the diary entry
    this.rating = checkRating(rating); // the user chosen rating of the activity
    this.title = title; // a unique title given by the user
    this.text = text; // the main text of the entry
  }

  /**
   * Ensures that the rating is between 0 and 10.
   *
   * @param rating The rating of the diary entry
   * @return The validated rating of the diary entry
   * @throws IllegalArgumentException If the rating is outside the valid range
   */
  private static double checkRating(double rating) {
    if (rating < 0 || rating > 10) {
      throw new IllegalArgumentException("Rating must be between 0 and 10.");
    }
    return rating;
  }

  /**
   * Returns the time the entry was first created.
   *
   * @return the time written.
   */
  public LocalDateTime getTimeWritten() {
    return timeWritten;
  }

  /**
   * Returns the time of the last edit to the diary entry.
   *
   * @return the time of the last edit
   */
  public LocalDateTime getTimeEdited() {
    return timeEdited;
  }

  /**
   * Update the last edited time to the current time. This method should be called whenever the
   * diary entry is edited.
   */
  public void setTimeEdited() {
    this.timeEdited = LocalDateTime.now();
  }

  /**
   * Returns the name of the author.
   *
   * @return the name of the author
   */
  public String getAuthor() {
    return author;
  }

  /**
   * Updates the name of the author.
   *
   * @param author the name of the author
   */
  public void setAuthor(String author) {
    this.author = author;
    setTimeEdited();
  }

  /**
   * Returns the destination associated with this diary entry. This could also be a more general
   * description of the journey (e.g., "Traveling through Europe").
   *
   * @return the destination of the general travel context
   */
  public String getDestination() {
    return destination;
  }

  /**
   * Updates the destination associated with this diary entry. This could also be a more general
   * description of the journey (e.g., "Traveling through Europe").
   *
   * @param destination the destination of the general travel context
   */
  public void setDestination(String destination) {
    this.destination = destination;
    setTimeEdited();
  }

  /**
   * Returns the title of the diary entry.
   *
   * @return the title of the diary entry
   */
  public String getTitle() {
    return title;
  }

  /**
   * Updates the title of the diary entry.
   *
   * @param title the title of the diary entry
   */
  public void setTitle(String title) {
    this.title = title;
    setTimeEdited();
  }

  /**
   * Returns the activity associated with this diary entry. Usually one descriptive word (e.g.,
   * "Bathing", "Hiking").
   *
   * @return the activity associated with this diary entry
   */
  public String getActivity() {
    return activity;
  }

  /**
   * Updates the activity associated with this diary entry. Usually one descriptive word (e.g.,
   * "Bathing", "Hiking").
   *
   * @param category the activity associated with this diary entry
   */
  public void setActivity(String category) {
    this.activity = category;
    setTimeEdited();
  }

  /**
   * Returns the users rating of the activity or experience on a scale from 0 to 10.
   *
   * @return the rating of the activity
   */
  public double getRating() {
    return rating;
  }

  /**
   * Updates the users' rating of the activity or experience on a scale from 0 to 10.
   *
   * @param rating the rating of the activity
   */
  public void setRating(double rating) {
    this.rating = checkRating(rating);
    setTimeEdited();
  }

  /**
   * Returns the main content of the diary entry. This would usually be a description of the
   * activity, context and anything the user wants to add.
   *
   * @return the diary entry text
   */
  public String getText() {
    return text;
  }

  /**
   * Updates the main content of the diary entry. This would usually be a description of the
   * activity, context and anything the user wants to add.
   *
   * @param text the main text of the diary entry
   */
  public void setText(String text) {
    this.text = text;
    setTimeEdited();
  }
}
