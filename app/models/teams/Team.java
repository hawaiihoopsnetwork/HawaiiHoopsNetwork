package models.teams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Model of a team.
 * 
 * @author AJ
 * 
 */
public class Team {

  public static Map<String, Boolean> getDays() {
    Map<String, Boolean> teamDays = new HashMap<>();
    String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
    for (int x = 0; x < days.length; x++) {
      teamDays.put(days[x], false);
    }
    return teamDays;
  }

  public static List<String> getTypes() {
    String[] types = { "Mens", "Womens", "Coed" };
    return java.util.Arrays.asList(types);
  }
}
