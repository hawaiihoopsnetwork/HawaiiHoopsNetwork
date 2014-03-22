package models.games;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import views.formdata.games.GameForm;

/**
 * Game model.
 * 
 * @author AJ
 * 
 */
@Entity
@Table(name = "game")
public class Game extends Model {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  private String name;

  @Constraints.Required
  private String time;
  @Constraints.Required
  private String date;
  @Constraints.Required
  private String location;

  @Constraints.Required
  private String type;

  private String frequency;
  private String avgSklLvl;
  private String players;

  /**
   * Constructs a game.
   * 
   * @param name name
   * @param time time
   * @param date date
   * @param location location
   * @param type type
   * @param freq frequency
   * @param sklLvl skill level
   * @param players players
   */
  public Game(String name, String time, String date, String location, String type, String freq, String sklLvl,
      String players) {
    this.setName(name);
    this.setTime(time);
    this.setDate(date);
    this.setLocation(location);
    this.setType(type);
    this.setFrequency(freq);
    this.setAvgSklLvl(sklLvl);
    this.setPlayers(players);
  }

  /**
   * Finder for a game.
   * 
   * @return a finder object
   */
  public static Finder<String, Game> find() {
    return new Finder<String, Game>(String.class, Game.class);
  }

  /**
   * Adds a game to the database.
   * 
   * @param gf the game form
   */
  public static void addGame(GameForm gf) {
    // TODO currently does not work with editing of games
    if (getGame(gf.name) == null) {
      Game game = new Game(gf.name, gf.time, gf.date, gf.location, gf.type, gf.frequency, gf.avgSklLvl, gf.players);
      game.save();

    }
    else {
      Game game = getGame(gf.name);
      game.setTime(gf.time);
      game.setDate(gf.date);
      game.setLocation(gf.location);
      game.setType(gf.type);
      game.setFrequency(gf.frequency);
      game.setAvgSklLvl(gf.avgSklLvl);
      game.setPlayers(gf.players);
      game.save();
    }

  }

  /**
   * Deletes a game.
   * 
   * @param name the name of the game
   */
  public static void deleteGame(String name) {
    find().ref(name).delete();
  }

  /**
   * Returns the game based on it's name.
   * 
   * @param name name
   * @return the game
   */
  public static Game getGame(String name) {
    return find().byId(name);
  }

  /**
   * Returns a list of all the games.
   * 
   * @return the list of games
   */
  public static List<Game> getGames() {
    return find().all();
  }

  /**
   * @return the time
   */
  public String getTime() {
    return time;
  }

  /**
   * @param time the time to set
   */
  public void setTime(String time) {
    this.time = time;
  }

  /**
   * @return the date
   */
  public String getDate() {
    return date;
  }

  /**
   * @param date the date to set
   */
  public void setDate(String date) {
    this.date = date;
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
   * Returns the types of games.
   * 
   * @return type of game
   */
  public static List<String> getTypes() {
    String[] types = { "Public", "Private" };
    return java.util.Arrays.asList(types);
  }

  /**
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * @param type the type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @return the players as a java List
   */
  public List<String> getListPlayers() {
    // TODO check if player name is related to a player profile
    List<String> gamePlayers = java.util.Arrays.asList(players.split("\\s*,\\s*"));
    return gamePlayers;
  }

  /**
   * Returns the players.
   * 
   * @return players
   */
  public String getPlayers() {
    return players;
  }

  /**
   * @param players the players to set
   */
  public void setPlayers(String players) {
    this.players = players;
  }

  /**
   * @return the avgSklLvl
   */
  public String getAvgSklLvl() {
    return avgSklLvl;
  }

  /**
   * @param avgSklLvl the avgSklLvl to set
   */
  public void setAvgSklLvl(String avgSklLvl) {
    this.avgSklLvl = avgSklLvl;
  }

  /**
   * Returns a list of frequencies.
   * 
   * @return the list of frequencies.
   */
  public static List<String> getListFrequencies() {
    String[] freqs = { "One Time", "Recurring" };
    return java.util.Arrays.asList(freqs);
  }

  /**
   * @return the frequency
   */
  public String getFrequency() {
    return frequency;
  }

  /**
   * @param frequency the frequency to set
   */
  public void setFrequency(String frequency) {
    this.frequency = frequency;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }
}
