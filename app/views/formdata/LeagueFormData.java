package views.formdata;

import play.data.validation.ValidationError;
import java.util.ArrayList;
import java.util.List;
import models.Team;

/**
 * Backing class for the login form.
 */
public class LeagueFormData {

  /** The submitted league name. */
  public String leagueName = "";
  
  /** The submitted teams. */
  public String[] teamArray = new String[64];

  /** Required for form instantiation. */
  public LeagueFormData() {
  }

  /**
   * Validates Form<LoginFormData>.
   * Called automatically in the controller by bindFromRequest().
   * Checks to see that email and password are valid credentials.
   * @return Null if valid, or a List[ValidationError] if problems found.
   */
  public List<ValidationError> validate() {

    List<ValidationError> errors = new ArrayList<>();

    return (errors.size() > 0) ? errors : null;
  }

}
