package views.formdata.games;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Id;
import models.games.Game;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

/**
 * The backing class when creating a game.
 * 
 * @author AJ
 * 
 */
public class GameForm {

  /** The name of the game. **/
  @Id
  @Constraints.Required(message = "A name is required.")
  public String name;
  /** Date. **/
  
  public String date;
  /** Time. **/
  @Constraints.Required(message = "A time is required.")
  public String time;
  /** Location. **/
  @Constraints.Required(message = "A location is required.")
  public String location;
  /** Type. **/
  @Constraints.Required(message = "A type is required.")
  public String type;
  /** Frequency. **/
  public String frequency;
  /** Average Skill Level. **/
  public String avgSklLvl;
  /** Players attending. **/
  public String players;

  /** Month. **/
  @Constraints.Required(message = "A Month is Required.")
  public String month;
  /** Day. **/
  @Constraints.Required(message = "A day is required.")
  public String day;

  /**
   * Default Constructor.
   * 
   */
  public GameForm() {
  }

  /**
   * Game form.
   * 
   * @param name name
   * @param month month
   * @param day day
   * @param time time
   * @param location location
   * @param type type
   * @param freq frequency
   * @param sklLvl Average Skill Level
   * @param players players attending
   */
  public GameForm(String name, String month, String day, String time, String location, String type, String freq,
      String sklLvl, String players) {
    this.name = name;
    this.month = month;
    this.day = day;
    this.time = time;
    this.location = location;
    this.type = type;
    this.frequency = freq;
    this.avgSklLvl = sklLvl;
    this.players = players;
  }

  /**
   * Other constructor.
   * 
   * @param game the game
   */
  public GameForm(Game game) {
    this.name = game.getName();

    String date = game.getDate();
    String[] dateSplit = date.split("\\s*");
    this.month = dateSplit[0];
    this.day = dateSplit[1];

    this.time = game.getTime();
    this.location = game.getLocation();
    this.type = game.getType();
    this.frequency = game.getFrequency();
    this.avgSklLvl = game.getAvgSklLvl();
    this.players = game.getPlayers();
  }

  /**
   * Validation Method.
   * 
   * @return list of errors
   */
  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<>();

    // TODO check if location is in location database

    return errors.isEmpty() ? null : errors;
  }

}
