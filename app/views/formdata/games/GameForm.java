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
  @Constraints.Required(message = "A date is required.")
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
   * @param date date
   * @param time time
   * @param location location
   * @param type type
   * @param freq frequency
   * @param sklLvl Average Skill Level
   * @param players players attending
   */
  public GameForm(String name, String date, String time, String location, String type, String freq, String sklLvl,
      String players) {
    this.name = name;
    this.date = date;
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
    this.date = game.getDate();
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
