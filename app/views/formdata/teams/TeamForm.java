package views.formdata.teams;

import javax.persistence.Id;
import play.data.validation.Constraints;

/**
 * The backing class for the team form.
 * 
 * @author AJ
 * 
 */
public class TeamForm {

  /** The team name. **/
  @Id
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
  @Constraints.Required(message = "Players are required for a team, aren't they?")
  public String roster;
  /** Description of the team. **/
  public String description;

  /**
   * Default constructor.
   */
  public TeamForm() {
  }

}
