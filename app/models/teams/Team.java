package models.teams;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import play.db.ebean.Model;
import views.formdata.teams.TeamForm;

/**
 * Model of a Team object.
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
  private String teamType;
  private String skillLevel;
  private String roster;
  private String description;

  /**
   * Default constructor.
   */
  public Team() {
  }

  /**
   * Constructor.
   * 
   * @param teamName team name
   * @param location location
   * @param teamType type of team
   * @param skillLevel skill level
   * @param roster roster
   * @param description description
   */
  public Team(String teamName, String location, String teamType, String skillLevel, String roster, String description) {
    this.setTeamName(teamName);
    this.setLocation(location);
    this.setTeamType(teamType);
    this.setSkillLevel(skillLevel);
    this.setRoster(roster);
    this.setDescription(description);
  }

  /**
   * Finder.
   * 
   * @return finder
   */
  public static Finder<String, Team> find() {
    return new Finder<String, Team>(String.class, Team.class);
  }

  /**
   * Adds a team to the database if it is new, otherwise edits existing team.
   * 
   * @param tf
   */
  public static void addTeam(TeamForm tf) {
    Team team;

    String name = tf.teamName;

    if (!isTeam(name)) {
      team = new Team(name, tf.location, tf.teamType, tf.skillLevel, tf.roster, tf.description);
      team.save();
    }
    else {
      team = getTeam(name);
      team.setTeamName(name);
      team.setLocation(tf.location);
      team.setTeamType(tf.teamType);
      team.setSkillLevel(tf.skillLevel);
      team.setRoster(tf.roster);
      team.setDescription(tf.description);
      team.save();
    }
  }

  /**
   * Returns a Java list of teams.
   * 
   * @return list of teams
   */
  public static List<Team> getTeams() {
    return find().all();
  }

  /**
   * Retursn the team associated with the name.
   * 
   * @param teamName team name to be looked for
   * @return the Team if it exists
   */
  public static Team getTeam(String teamName) {
    return find().where().eq("teamName", teamName).findUnique();
  }

  /**
   * Checks if teamName belongs to a team.
   * 
   * @param teamName team name
   * @return true if team exists, false otherwise
   */
  public static boolean isTeam(String teamName) {
    Team team = getTeam(teamName);
    return !(team == null);
  }

  /**
   * Returns a list of types associated with a team.
   * 
   * @return
   */
  public static List<String> typeOfTeam() {
    String[] types = { "Mens", "Womens", "Co-ed" };
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
   * @return the teamType
   */
  public String getTeamType() {
    return teamType;
  }

  /**
   * @param teamType the teamType to set
   */
  public void setTeamType(String teamType) {
    this.teamType = teamType;
  }

  /**
   * @return the skillLevel
   */
  public String getSkillLevel() {
    return skillLevel;
  }

  /**
   * @param skillLevel the skillLevel to set
   */
  public void setSkillLevel(String skillLevel) {
    this.skillLevel = skillLevel;
  }

  /**
   * @return the roster
   */
  public String getRoster() {
    return roster;
  }

  /**
   * Returns the roster as a java list.
   * 
   * @return the roster as a list
   */
  public List<String> getRosterList() {
    // TODO check if player name is related to a player profile
    List<String> rosterList = java.util.Arrays.asList(roster.split("\\s*,\\s*"));
    return rosterList;
  }

  /**
   * @param roster the roster to set
   */
  public void setRoster(String roster) {
    this.roster = roster;
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

}
