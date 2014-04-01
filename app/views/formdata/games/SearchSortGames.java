package views.formdata.games;

import java.util.Comparator;
import java.util.List;
import models.games.Game;

/**
 * Search/Sort form for Games.
 * 
 * @author AJ
 * 
 */
public class SearchSortGames {

  /** String to search for. **/
  public String search;

  private static String[] months = { "January", "February", "March", "April", "May", "June", "July", "August",
      "September", "October", "November", "December" };

  /**
   * Default constructor.
   */
  public SearchSortGames() {
  }

  /**
   * Compares the two dates of games.
   * 
   * @author AJ
   * 
   */
  public static class ByDate implements Comparator<Game> {

    @Override
    public int compare(Game one, Game two) {
      String[] dateOne = one.getDate().split("\\s+");
      String[] dateTwo = two.getDate().split("\\s+");

      List<String> monthList = java.util.Arrays.asList(months);

      int indexOne = monthList.indexOf(dateOne[0]);
      int indexTwo = monthList.indexOf(dateTwo[0]);

      if (indexOne < indexTwo) {
        return -1;
      }
      else if (indexOne > indexTwo) {
        return 1;
      }
      else {
        return dateOne[1].compareTo(dateTwo[1]);
      }

    }
  }

  /**
   * Sorts games based on skill level.
   * 
   * @author AJ
   * 
   */
  public static class BySkillLevel implements Comparator<Game> {

    @Override
    public int compare(Game one, Game two) {
      return one.getAvgSklLvl().compareTo(two.getAvgSklLvl());
    }
  }

  /**
   * Sorts games based on location.
   * 
   * @author AJ
   * 
   */
  public static class ByLocation implements Comparator<Game> {

    @Override
    public int compare(Game one, Game two) {
      return one.getLocation().compareTo(two.getLocation());
    }
  }
}
