package controllers;

import java.util.Collections;
import java.util.List;
import models.Player;
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
    return ok(PlayerList.render("PlayerList", Player.getPlayers()));
  }
  
  /**
   * Returns the playerprofiles page
   *
   * @return The Player profiles list page.
   */
  public static Result playersSkill(String skillLevel) {
    return ok(PlayerList.render("PlayerList", Player.getPlayersSkill(skillLevel)));
  }
  
  /**
   * Returns the playerprofiles page
   *
   * @return The Player profiles list page.
   */
  public static Result playersPosition(String position) {
    return ok(PlayerList.render("PlayerList", Player.getPlayersPosition(position)));
  }
  
  /**
   * Returns the playerprofiles page
   *
   * @return The Player profiles list page.
   */
  public static Result playersWithName(String name) {
    return ok(PlayerList.render("PlayerList", Player.getPlayersWithName(name)));
  }
  
  /**
   * Returns a page containing a list of the players in alphabetical order.
   * @param pagination is the pagination number
   * @return the page
   */
  public static Result sortAllPlayersByName() {
    List<Player> players = Player.getPlayers();
    Collections.sort(players, new Player.SortByName());
    return ok(PlayerList.render("PlayerList", players));
  }
  
  /**
   * Returns a page containing all a list of the players in court order.
   * @param pagination is the pagination number
   * @return the page
   */
  public static Result sortAllPlayersByCourt() {
    List<Player> players = Player.getPlayers();
    Collections.sort(players, new Player.SortByCourt());
    return ok(PlayerList.render("PlayerList", players));
  }
  
  /**
   * Returns a page containing all a list of the players in skill order.
   * @param pagination is the pagination number
   * @return the page
   */
  public static Result sortAllPlayersBySkill() {
    List<Player> players = Player.getPlayers();
    Collections.sort(players, new Player.SortBySkill());
    return ok(PlayerList.render("PlayerList", players));
  }
  
  /**
   * Returns a page containing all a list of the players in position order.
   * @param pagination is the pagination number
   * @return the page
   */
  public static Result sortAllPlayersByPosition() {
    List<Player> players = Player.getPlayers();
    Collections.sort(players, new Player.SortByPosition());
    return ok(PlayerList.render("PlayerList", players));
  }

}
