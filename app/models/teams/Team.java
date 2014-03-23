package models.teams;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import play.db.ebean.Model;
import views.formdata.teams.TeamForm;

/**
 * Model of a team.
 * 
 * @author AJ
 * 
 */
@Entity
@Table(name = "teams")
public class Team extends Model {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @Id
  private String teamName;
  private String location;
  private String gender;
  private Map<String, Boolean> days;
  private String sklLvl;
  private String roster;
  @Lob
  private String description;

  /**
   * Constructor.
   * 
   * @param name name
   * @param loc location
   * @param gender gender
   * @param days days
   * @param sklLvl skill level
   * @param roster team roster
   * @param description description
   */
  public Team(String name, String loc, String gender, Map<String, Boolean> days, String sklLvl, String roster,
      String description) {
    this.setTeamName(name);
    this.setLocation(loc);
    this.setGender(gender);
    this.setDays(days);
    this.setSklLvl(sklLvl);
    this.setRoster(roster);
    this.setDescription(description);
  }

  /**
   * Finder.
   * 
   * @return a finder
   */
  public static Finder<String, Team> find() {
    return new Finder<String, Team>(String.class, Team.class);
  }

  /**
   * Returns a list of all the teams.
   * 
   * @return the list of teams
   */
  public static List<Team> getAllTeams() {
    return find().all();
  }

  /**
   * Returns the team.
   * 
   * @param teamName the name of the team to be found.
   * @return team
   */
  public static Team getTeam(String teamName) {
    return find().where().eq("teamName", teamName).findUnique();
  }

  /**
   * Add a team to the database.
   * 
   * @param teamForm team data
   */
  public static void addTeam(TeamForm teamForm) {

    if (getTeam(teamForm.teamName) == null) {
      Team team =
          new Team(teamForm.teamName, teamForm.location, teamForm.gender, teamForm.days, teamForm.sklLvl,
              teamForm.roster, teamForm.description);
      team.save();
    }
    else {
      Team team = getTeam(teamForm.teamName);
      team.setTeamName(teamForm.teamName);
      team.setLocation(teamForm.location);
      team.setGender(teamForm.gender);
      team.setDays(teamForm.days);
      team.setSklLvl(teamForm.sklLvl);
      team.setRoster(teamForm.roster);
      team.setDescription(teamForm.description);
      team.save();
    }
  }

  /**
   * Returns a java Map containing the days.
   * 
   * @return the days
   */
  public static Map<String, Boolean> getMapDays() {
    Map<String, Boolean> teamDays = new LinkedHashMap<>();
    String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
    for (int x = 0; x < days.length; x++) {
      teamDays.put(days[x], false);
    }
    return teamDays;
  }

  /**
   * Returns the list of types.
   * 
   * @return the list of types
   */
  public static List<String> getTypes() {
    String[] types = { "Mens", "Womens", "Coed" };
    return java.util.Arrays.asList(types);
  }

  /**
   * @return the teamName
   */
  public String getTeamName() {
    return teamName;
  }

  /**
   * @param teamName the teamName to set
   */
  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }

  /**
   * @return the location
   */
  public String getLocation() {
    return location;
  }

  /**
   * @param location the location to set
   */
  public void setLocation(String location) {
    this.location = location;
  }

  /**
   * @return the gender
   */
  public String getGender() {
    return gender;
  }

  /**
   * @param gender the gender to set
   */
  public void setGender(String gender) {
    this.gender = gender;
  }

  /**
   * @return the sklLvl
   */
  public String getSklLvl() {
    return sklLvl;
  }

  /**
   * @param sklLvl the sklLvl to set
   */
  public void setSklLvl(String sklLvl) {
    this.sklLvl = sklLvl;
  }

  /**
   * @return the roster
   */
  public String getRoster() {
    return roster;
  }

  /**
   * @param roster the roster to set
   */
  public void setRoster(String roster) {
    this.roster = roster;
  }

  /**
   * @return the players as a java List
   */
  public List<String> getListPlayers() {
    // TODO check if player name is related to a player profile
    List<String> gamePlayers = java.util.Arrays.asList(roster.split("\\s*,\\s*"));
    return gamePlayers;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return the days
   */
  public Map<String, Boolean> getDays() {
    return days;
  }

  /**
   * @param days the days to set
   */
  public void setDays(Map<String, Boolean> days) {
    this.days = days;
  }
}
