package views.formdata.teams;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import play.data.validation.Constraints;
import play.data.validation.ValidationError;

/**
 * The backing class for the team form.
 * 
 * @author AJ
 * 
 */
public class TeamForm {

  /** The team name. **/
  @Id
  /** team id **/
  public long id;
  @Constraints.Required(message = "A team name is required.")
  public String teamName;
  /** Team location. **/
  @Constraints.Required(message = "A general location is required.")
  public String location;
  /** Team type. **/
  @Constraints.Required(message = "A team type is required.")
  public String teamType;
  /** Team Skill level. **/
  public String skillLevel;
  /** Team roster. **/
  @Constraints.Required(message = "Atleast one player is required to start a team")
  public List<String> roster = new ArrayList<>();
  /** Description of the team. **/
  public String description;
  /** Team logo **/
  public String imageUrl;
  
  public int wins;
  
  public int losses;
  
  public int pointsFor;
  
  public int pointsAgainst;

  /**
   * Default constructor.
   */
  public TeamForm() {
  }

  /**
   * Validation Method.
   * 
   * @return errors
   */
  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<>();

    // Milestone 3, check if team being added already exists in the database.
    return errors.isEmpty() ? null : errors;
  }

}
