package views.formdata.games;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Id;
import models.games.Game;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

public class GameForm {

  @Id
  public long id;

  @Constraints.Required(message = "A date is required.")
  public String date;

  @Constraints.Required(message = "A time is required.")
  public String time;

  @Constraints.Required(message = "A location is required.")
  public String location;

  @Constraints.Required(message = "A type is required.")
  public String type;

  public String frequency;
  public String avgSklLvl;
  public String players;

  public GameForm() {
  }

  public GameForm(String date, String time, String location, String type, String freq, String sklLvl, String players) {
    this.date = date;
    this.time = time;
    this.location = location;
    this.type = type;
    this.frequency = freq;
    this.avgSklLvl = sklLvl;
    this.players = players;
  }

  public GameForm(Game game) {
    this.date = game.getDate();
    this.time = game.getTime();
    this.location = game.getLocation();
    this.type = game.getType();
    this.frequency = game.getFrequency();
    this.avgSklLvl = game.getAvgSklLvl();
    this.players = game.getPlayers();
  }

  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<>();

    // TODO check if location is in location database

    return errors.isEmpty() ? null : errors;
  }

}
