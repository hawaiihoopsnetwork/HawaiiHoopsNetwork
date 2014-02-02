package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.LoginForm;
import views.formdata.NewUserForm;
import views.html.Index;
import views.html.LogIn;

/**
 * Contains the different methods required for the login page.
 * 
 * @author AJ
 * 
 */
public class Login extends Controller {

  /**
   * Returns the LogIn page.
   * 
   * @return the login page.
   */
  public static Result logIn() {
    LoginForm data1 = new LoginForm();
    NewUserForm data2 = new NewUserForm();
    Form<LoginForm> logIn = Form.form(LoginForm.class).fill(data1);
    Form<NewUserForm> user = Form.form(NewUserForm.class).fill(data2);
    return ok(LogIn.render(logIn, user));
  }

  /**
   * Logs the user in.
   * 
   * @return Method for when the user logs in.
   */
  public static Result loginUser() {
    Form<LoginForm> formData = Form.form(LoginForm.class).bindFromRequest();

    NewUserForm data2 = new NewUserForm();
    Form<NewUserForm> user = Form.form(NewUserForm.class).fill(data2);

    if (formData.hasErrors()) {
      System.out.println("Errors Found");
      return badRequest(LogIn.render(formData, user));
    }
    else {
      LoginForm data = new LoginForm();
      Form<LoginForm> loginForm = Form.form(LoginForm.class).fill(data);
      return ok(Index.render(loginForm));
    }
  }

  /**
   * New user sign up.
   * 
   * @return New user sign up button.
   */
  public static Result createNewUser() {
    Form<NewUserForm> formData = Form.form(NewUserForm.class).bindFromRequest();

    LoginForm data2 = new LoginForm();
    Form<LoginForm> user = Form.form(LoginForm.class).fill(data2);

    if (formData.hasErrors()) {
      System.out.println("Errors Found");
      return badRequest(LogIn.render(user, formData));
    }
    else {
      LoginForm data = new LoginForm();
      Form<LoginForm> loginForm = Form.form(LoginForm.class).fill(data);
      return ok(Index.render(loginForm));
    }
  }

}
