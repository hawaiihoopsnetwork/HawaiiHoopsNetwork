package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.LoginForm;
import views.html.PlayerProfile;

public class Profile extends Controller {
  
  public static Result profile() {
    LoginForm data = new LoginForm();
    Form<LoginForm> loginForm = Form.form(LoginForm.class).fill(data);
    return ok(PlayerProfile.render("Name", loginForm));
  }

}
