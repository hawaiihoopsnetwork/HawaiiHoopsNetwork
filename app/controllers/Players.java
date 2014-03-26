package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import models.Player;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;
import views.html.player.PlayerList;
import views.formdata.SearchFormData;


/**
 * Created by taylorak on 3/18/14.
 */
public class Players extends Controller{
  
  static List<Player> tempList = new ArrayList<Player>();

  /**
   * Returns the playerprofiles page
   *
   * @return The Player profiles list page.
   */
  public static Result players() {
    SearchFormData data2 = new SearchFormData();
    Form<SearchFormData> dataForm = Form.form(SearchFormData.class).fill(data2);
    
    tempList = Player.getPlayers();
    
    return ok(PlayerList.render("PlayerList", Player.getPlayers(), dataForm));
  }
  
  /**
   * Returns the playerprofiles page
   *
   * @return The Player profiles list page.
   */
  public static Result playersSkill(String skillLevel) {
    SearchFormData data2 = new SearchFormData();
    Form<SearchFormData> dataForm = Form.form(SearchFormData.class).fill(data2);
    
    tempList = Player.getPlayersSkill(skillLevel);
    
    return ok(PlayerList.render("PlayerList", Player.getPlayersSkill(skillLevel), dataForm));
  }
  
  /**
   * Returns the playerprofiles page
   *
   * @return The Player profiles list page.
   */
  public static Result playersPosition(String position) {
    SearchFormData data2 = new SearchFormData();
    Form<SearchFormData> dataForm = Form.form(SearchFormData.class).fill(data2);
    
    tempList = Player.getPlayersPosition(position);
    
    return ok(PlayerList.render("PlayerList", Player.getPlayersPosition(position), dataForm));
  }
  
  /**
   * Returns the playerprofiles page
   *
   * @return The Player profiles list page.
   */
  public static Result playersWithName() {
    SearchFormData data2 = new SearchFormData();
    Form<SearchFormData> dataForm = Form.form(SearchFormData.class).fill(data2);
    
    Form<SearchFormData> data = Form.form(SearchFormData.class).bindFromRequest();
    SearchFormData formData = data.get();
    List<Player> results = Player.getPlayersWithName(formData.name);
    
    tempList = results;
    
    return ok(PlayerList.render("PlayerList", results, dataForm));
  }
  
  
  
  /**
   * Returns a page containing a list of the players in alphabetical order.
   * @param pagination is the pagination number
   * @return the page
   */
  public static Result sortAllPlayersByName() {
    SearchFormData data2 = new SearchFormData();
    Form<SearchFormData> dataForm = Form.form(SearchFormData.class).fill(data2);
    
    Collections.sort(tempList, new Player.SortByName());
    return ok(PlayerList.render("PlayerList", tempList, dataForm));
  }
  
  /**
   * Returns a page containing all a list of the players in court order.
   * @param pagination is the pagination number
   * @return the page
   */
  public static Result sortAllPlayersByCourt() {
    SearchFormData data2 = new SearchFormData();
    Form<SearchFormData> dataForm = Form.form(SearchFormData.class).fill(data2);
    
    Collections.sort(tempList, new Player.SortByCourt());
    return ok(PlayerList.render("PlayerList", tempList, dataForm));
  }
  
  /**
   * Returns a page containing all a list of the players in skill order.
   * @param pagination is the pagination number
   * @return the page
   */
  public static Result sortAllPlayersBySkill() {
    SearchFormData data2 = new SearchFormData();
    Form<SearchFormData> dataForm = Form.form(SearchFormData.class).fill(data2);
    
    Collections.sort(tempList, new Player.SortBySkill());
    return ok(PlayerList.render("PlayerList", tempList, dataForm));
  }
  
  /**
   * Returns a page containing all a list of the players in position order.
   * @param pagination is the pagination number
   * @return the page
   */
  public static Result sortAllPlayersByPosition() {
    SearchFormData data2 = new SearchFormData();
    Form<SearchFormData> dataForm = Form.form(SearchFormData.class).fill(data2);
    
    Collections.sort(tempList, new Player.SortByPosition());
    return ok(PlayerList.render("PlayerList", tempList, dataForm));
  }

}
