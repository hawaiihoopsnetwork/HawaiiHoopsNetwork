package views.formdata.teams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Id;
import play.data.validation.ValidationError;
import play.data.validation.Constraints;

/**
 * Create Team backing form.
 * 
 * @author AJ
 * 
 */
public class TeamForm {

  /** Team name. **/
  @Id
  @Constraints.Required(message = "A team name is required.")
  public String teamName;
  /** Main team location. **/
  @Constraints.Required(message = "A location is required.")
  public String location;
  /** Type. **/
  @Constraints.Required(message = "Please select one.")
  public String gender;
  /** Scheduled days. **/
  public Map<String, Boolean> days;
  /** Skill level. **/
  public String sklLvl;
  /** Roster. **/
  @Constraints.Required
  public String roster;
  /** Description of Team. **/
  public String description;

  /**
   * Default constructor.
   */
  public TeamForm() {
  }

  /**
   * Team Form constructor.
   * 
   * @param name team name
   * @param loc team location
   * @param gender team type
   * @param days team days
   * @param sklLvl team skill level
   * @param desc team description
   */
  public TeamForm(String name, String loc, String gender, Map<String, Boolean> days, String sklLvl, String desc) {
    this.teamName = name;
    this.location = loc;
    this.gender = gender;
    this.days = days;
    this.sklLvl = sklLvl;
    this.description = desc;
  }

  /**
   * Validation method.
   * 
   * @return list of errors
   */
  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<>();
    return errors.isEmpty() ? null : errors;
  }

}
