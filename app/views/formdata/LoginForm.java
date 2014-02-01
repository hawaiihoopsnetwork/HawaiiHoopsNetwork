package views.formdata;

import java.util.ArrayList;
import java.util.List;
import models.db.ProfilesDB;
import play.data.validation.ValidationError;

public class LoginForm {

  public String userName;
  public String password;
  
  public LoginForm() {}
  
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
