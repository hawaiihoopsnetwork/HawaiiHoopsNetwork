package views.formdata;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import models.Team;
import models.TeamDB;

/**
 * 
 * Holds the telephone type data for the drop down list.
 * @author Andrew
 *
 */
public class LeagueTeamsFD {

  private static Map<String, Boolean> teamMap = new LinkedHashMap<>();
  /**
   * 
   * No arguments constructor.
   */
  public LeagueTeamsFD() {
    List<Team> teamList = TeamDB.getTeams();
    while(teamList.isEmpty() == false){
      teamMap.put(teamList.get(0).getName(), false);
    }
    
  }
  
  /**
   * returns a map of all teams.
   * @return teamMap the map of the teams
   */
  public static Map<String, Boolean> getTeams() {
    return teamMap;
  }
  
  /**
   * gets the specific type wanted.
   * @param team the team wanted.
   * @return teamMap the map of all teams
   */
  public static Map<String, Boolean> getTeams(String team) {
    teamMap.put(team, true);
    return teamMap;
  }
  
  /**
   * returns boolean indicating if the provided team is legal.
   * @param team the team to check
   * @return a boolean to indicate if the team is legal
   */
  public static Boolean hasTeam(String team) {
    return teamMap.containsKey(team);
  }
}
