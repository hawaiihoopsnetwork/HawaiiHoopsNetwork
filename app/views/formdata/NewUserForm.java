package views.formdata;

import java.util.ArrayList;
import java.util.List;
import play.data.validation.ValidationError;
import models.Profile;
import models.db.ProfilesDB;

public class NewUserForm {

  public String userName;
  public String password;
  public String passwordCheck;
  public String emailAddress;
  
  public NewUserForm() {}
  
  public NewUserForm(Profile profile) {
    profile.setUserName(userName);
    profile.setPassword(password);
    profile.setEmailAdress(emailAddress);
  }
  
  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<>();
    
    if (ProfilesDB.isUser(userName)) {
      errors.add(new ValidationError("userName", "User Name already in use."));
    }
    if (password == null || password.length() == 0) {
      errors.add(new ValidationError("password", "No password was entered."));
    }
    if (passwordCheck == null || passwordCheck.length() == 0) {
      errors.add(new ValidationError("passwordCheck", "No password was entered."));
    }
    if (!password.equals(passwordCheck)) {
      errors.add(new ValidationError("passwordCheck", "Password do not match."));
    }
    if (emailAddress == null || emailAddress.length() == 0) {
      errors.add(new ValidationError("emailAddress", "No email address was entered."));
    }
    return errors.isEmpty() ? null : errors;
  }
}
