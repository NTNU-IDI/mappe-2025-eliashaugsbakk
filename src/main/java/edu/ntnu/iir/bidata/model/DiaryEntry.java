package edu.ntnu.iir.bidata.model;

import edu.ntnu.iir.bidata.storage.DiaryEntryStorageDto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
 * DiaryEntry entry = new DiaryEntry(
 *     LocalDateTime.now(),
 *     LocalDateTime.now(),
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
   * Constructor to create fabricated diary entries. Takes fabricatedCreationTime to allow for
   * any creation time. This is useful when showcasing the functionality of the program.
   *
   * @param fabricatedCreationTime the fabricated time of creation
   * @param author      the name of the author
   * @param destination the destination of the trip, or a more general travel (Traveling through
   *                    Europe)
   * @param activity    the activity this entry is describing
   * @param rating      a rating the user assigns to the activity, on a scale from 0 to 10
   * @param title       the title of the entry
   * @param text        the main entry text, usually describing the activity
   */
  public DiaryEntry(LocalDateTime fabricatedCreationTime, String author, String destination,
      String activity, double rating, String title,
      String text) {
    this.timeWritten = fabricatedCreationTime;
    this.timeEdited = fabricatedCreationTime;
    this.author = author;
    this.destination = destination;
    this.activity = activity;
    this.rating = checkRating(rating);
    this.title = title;
    this.text = text;
  }

  /**
   * Creates a DiaryEntry from a data transfer object: DiaryEntryStorageDto.
   *
   * @param dto data transfer object.
   */
  public DiaryEntry(DiaryEntryStorageDto dto) {
    timeWritten = LocalDateTime.parse(dto.getTimeWritten());
    timeEdited = LocalDateTime.parse(dto.getTimeEdited());
    author = dto.getAuthor();
    destination = dto.getDestination();
    activity = dto.getActivity();
    rating = dto.getRating();
    title = dto.getTitle();
    text = dto.getText();
  }

  /**
   * Creates a new diary entry, describing an activity during a trip.
   *
   * @param author      the name of the author
   * @param destination the destination of the trip, or a more general travel (Traveling through
   *                    turope)
   * @param activity    the activity this entry is describing
   * @param rating      a rating the user assigns to the activity, on a scale from 0 to 10
   * @param title       the title of the entry
   * @param text        the main entry text, usually describing the activity
   */
  public DiaryEntry(String author, String destination, String activity, double rating, String title,
      String text) {
    this.timeWritten = LocalDateTime.now();
    this.timeEdited = LocalDateTime.now();
    this.author = author;
    this.destination = destination;
    this.activity = activity;
    this.rating = checkRating(rating);
    this.title = title;
    this.text = text;
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

  /**
   * Returns a string representation of this diary entry.
   *
   * @return a string representation of this diary entry
   */
  @Override
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    String formattedWritten = timeWritten.format(formatter);
    String formattedEdited = timeEdited.format(formatter);
    return """
        Written: %s
        Last edit: %s
        
        Author: %s
        Destination: %s
        Activity: %s
        Rating: %.1f
        Title: %s
        
        Main entry:
        %s
        """.formatted(formattedWritten, formattedEdited, author, destination, activity, rating,
        title, text);
  }
}
