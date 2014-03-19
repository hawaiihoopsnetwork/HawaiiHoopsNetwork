package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.player.PlayerList;

/**
 * Created by taylorak on 3/18/14.
 */
public class Players extends Controller{

  /**
   * Returns the playerprofiles page
   *
   * @return The Player profiles list page.
   */
  public static Result players() {
    return ok(PlayerList.render("PlayerList"));
  }

}
