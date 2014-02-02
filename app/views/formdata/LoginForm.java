package views.formdata;

import java.util.ArrayList;
import java.util.List;
import models.db.ProfilesDB;
import play.data.validation.ValidationError;

/**
 * The login form data.
 * 
 * @author AJ
 * 
 */
public class LoginForm {

  /** User Name. */
  public String userName;
  /** Password. */
  public String password;

  /**
   * Default constructor.
   */
  public LoginForm() {
  }

  /**
   * The validation method.
   * 
   * @return errors, if any.
   */
  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<>();

    if (ProfilesDB.isUser(userName)) {
      errors.add(new ValidationError("userName", "Already a registered user."));
    }
    if (!ProfilesDB.getProfiles().get(userName).equals(password)) {
      errors.add(new ValidationError("password", "Password Incorrect."));
    }
    return errors.isEmpty() ? null : errors;
  }
}
