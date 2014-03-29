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
  /** Month. **/
  @Constraints.Required(message = "A Month is Required.")
  public String month;
  /** Day. **/
  @Constraints.Required(message = "A day is required.")
  public String day;
  /** Hour. **/
  @Constraints.Required(message = "A time is required.")
  public String hour;
  /** Minute. **/
  @Constraints.Required(message = "A time is required.")
  public String minute;
  /** am pm. **/
  @Constraints.Required(message = "A time is required.")
  public String amPm;
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
   * Main constructor.
   * 
   * @param game the game
   */
  public GameForm(Game game) {
    this.name = game.getName();

    String date = game.getDate();
    System.out.println(date);
    String[] dateSplit = date.split("\\s*");
    this.month = dateSplit[0];
    this.day = dateSplit[1];

    String time = game.getTime();
    System.out.println(time);
    // String[] timeSplit = time.split("(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)");
    // this.hour = timeSplit[0];
    // this.minute = timeSplit[1];
    // this.amPm = timeSplit[2];

    this.location = game.getLocation();
    this.type = game.getType();
    this.frequency = game.getFrequency();
    this.avgSklLvl = game.getAvgSklLvl();
    this.players = game.getPlayers();
  }

}
