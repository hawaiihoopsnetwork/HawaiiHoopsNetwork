package controllers;

import play.mvc.Controller;
import play.mvc.Result;
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
    return ok(Index.render());
  }

  /**
   * Returns the terms of use page.
   * 
   * @return The resulting terms of use page.
   */
  public static Result terms() {
    return ok(TermsOfUse.render());
  }

}
