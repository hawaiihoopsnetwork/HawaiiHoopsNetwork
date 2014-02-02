package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.LoginForm;
import views.html.Index;
import views.html.TermsOfUse;

/**
 * Implements the controllers for this application.
 */
public class Application extends Controller {

  /**
   * Returns the home page.
   * 
   * @return The resulting home page.
   */
  public static Result index() {
    LoginForm data = new LoginForm();
    Form<LoginForm> loginForm = Form.form(LoginForm.class).fill(data);
    return ok(Index.render(loginForm));
  }

  /**
   * Returns the terms of use page.
   * 
   * @return The resulting terms of use page.
   */
  public static Result terms() {
    LoginForm data = new LoginForm();
    Form<LoginForm> loginForm = Form.form(LoginForm.class).fill(data);
    return ok(TermsOfUse.render(loginForm));
  }

}
