package models.games;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
@Table(name = "game")
public class Game extends Model {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  private long id;

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

  public Game(String time, String date, String location, String type, String freq, String sklLvl, String players) {
    this.setTime(time);
    this.setDate(date);
    this.setLocation(location);
    this.setType(type);
    this.setFrequency(freq);
    this.setAvgSklLvl(sklLvl);
    this.setPlayers(players);
  }

  public static Finder<Long, Game> find() {
    return new Finder<Long, Game>(Long.class, Game.class);
  }

  public static void addGame(String time, String date, String location, String type, String freq, String sklLvl,
      String players) {
    Game game = new Game(time, date, location, type, freq, sklLvl, players);
    game.save();
  }

  public static void addGame(Game game) {
    game.save();
  }

  public static void deleteGame(long id) {
    find().ref(id).delete();
  }

  public static Game getGame(long id) {
    return find().where().eq("id", id).findUnique();
  }

  public static List<Game> getGames() {
    return find().all();
  }

  /**
   * @return the id
   */
  public long getId() {
    return id;
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
   * @return the players
   */
  public List<String> getListPlayers() {
    // TODO check if player name is related to a player profile
    List<String> gamePlayers = java.util.Arrays.asList(players.split("\\s*,\\s*"));
    return gamePlayers;
  }

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
}
