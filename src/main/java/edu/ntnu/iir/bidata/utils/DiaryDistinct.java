package edu.ntnu.iir.bidata.utils;

import edu.ntnu.iir.bidata.model.DiaryEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Methods to return the distinct object variables from a collection of DiaryEntries.
 */
public class DiaryDistinct {

  /**
   * Returns a list of the unique authors from a collection of Diary Entries.
   *
   * @param originalCollection the collection to get authors from
   * @return the list of unique authors
   */
  public static List<String> getDistinctAuthors(Collection<DiaryEntry> originalCollection) {
    // use TreeSet to ensure uniqueness and alphabetical sorting
    Set<String> authors = new TreeSet<>();
    for (DiaryEntry entry : originalCollection) {
      authors.add(entry.getAuthor());
    }
    return new ArrayList<>(authors);
  }

  /**
   * Returns a list of the unique activities from a collection of Diary Entries.
   *
   * @param originalCollection the collection to get activities from
   * @return the list of unique activities
   */
  public static List<String> getDistinctActivities(Collection<DiaryEntry> originalCollection) {
    // use TreeSet to ensure uniqueness and alphabetical sorting
    Set<String> activities = new TreeSet<>();
    for (DiaryEntry entry : originalCollection) {
      activities.add(entry.getActivity());
    }
    return new ArrayList<>(activities);
  }

  /**
   * Returns a list of the unique destinations from a collection of Diary Entries.
   *
   * @param originalCollection the collection to get destinations from
   * @return the list of unique destinations
   */
  public static List<String> getDistinctDestinations(Collection<DiaryEntry> originalCollection) {
    // use TreeSet to ensure uniqueness and alphabetical sorting
    Set<String> destinations = new TreeSet<>();
    for (DiaryEntry entry : originalCollection) {
      destinations.add(entry.getDestination());
    }
    return new ArrayList<>(destinations);
  }
}
