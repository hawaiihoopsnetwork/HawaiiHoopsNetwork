package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import models.User;
import forms.*;
// import views.html.Index;
// import views.html.Login;
// import views.html.Registration;

/**
 * Implements the login controller for this application.
 */
public class Users extends Controller
{
  
  // private static final Form<SearchForm> searchForm = Form.form(SearchForm.class);
  private static final Form<RegistrationForm> registrationForm = Form.form(RegistrationForm.class);
  private static final Form<LoginForm> loginForm = Form.form(LoginForm.class);

  /**
   * Provides the Registration page (only to unauthenticated users). 
   * @return The Registration page. 
   */
  public static Result register()
  {
    return ok();
  }
  
  /**
   * Processes a registration form submission from an unauthenticated user. 
   * First we bind the HTTP POST data to an instance of RegistrationForm.
   * The binding process will invoke the Registration.validate() method.
   * If errors are found, re-render the page, displaying the error data. 
   * If errors not found, render the page with the good data. 
   * @return The index page with the results of validation. 
   */
  public static Result postRegister()
  {
    Form<RegistrationForm> filledRegistrationForm = registrationForm.bindFromRequest();
    
    if (filledRegistrationForm.hasErrors())
    {
      return badRequest();
    }
    else
    {
      RegistrationForm data = filledRegistrationForm.get();
      User user = User.addUser(data);
      session().clear();
      session("email", user.getEmail());
      flash("registered", "Thank you for signing up with Hawaii Hoops Network!");
      return redirect(routes.Application.index());
    }
  }
  
  /**
   * Provides the Login page (only to unauthenticated users). 
   * @return The Login page. 
   */
  public static Result login()
  {
    return ok();
  }

  /**
   * Processes a login form submission from an unauthenticated user. 
   * First we bind the HTTP POST data to an instance of LoginFormData.
   * The binding process will invoke the LoginFormData.validate() method.
   * If errors are found, re-render the page, displaying the error data. 
   * If errors not found, render the page with the good data. 
   * @return The index page with the results of validation. 
   */
  public static Result postLogin()
  {

    // Get the submitted form data from the request object, and run validation.
    Form<LoginForm> filledLoginForm = loginForm.bindFromRequest();

    if (filledLoginForm.hasErrors())
    {
      flash("error", "Login credentials not valid.");
      return badRequest();
    }
    else
    {
      // email/password OK, so now we set the session variable and only go to authenticated pages.
      session().clear();
      session("email", filledLoginForm.get().email);
      return redirect(routes.Application.index());
    }
  }
  
  /**
   * Logs out (only for authenticated users) and returns them to the Index page. 
   * @return A redirect to the Index page. 
   */
  @Security.Authenticated(Secured.class)
  public static Result logout()
  {
    session().clear();
    return redirect(routes.Application.index());
  }
  
  @Security.Authenticated(Secured.class)
  public static Result deleteUser()
  {
    User user = Secured.getUserInfo(ctx());
    user.delete();
    session().clear();
    return redirect(routes.Application.index());
  }
  
}
