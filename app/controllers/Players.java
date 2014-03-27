package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.avaje.ebean.Page;
import models.Player;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;
import views.html.player.PlayerList;
import views.html.player.PlayerForm;
import views.formdata.PlayerFields;
import views.formdata.PlayerFormData;
import views.formdata.SearchFormData;


/**
 * Created by scott on 3/26/14.
 */
public class Players extends Controller{
  
  /**
   * Returns the playerprofiles page with all players.
   *
   * @return The Player profiles list page.
   */
  public static Result players(String sortOrder, Integer page) {
    SearchFormData data2 = new SearchFormData();
    Form<SearchFormData> dataForm = Form.form(SearchFormData.class).fill(data2);
    Page<Player> playerPage = Player.find(sortOrder, page);
    return ok(PlayerList.render(playerPage, "PlayerList", dataForm, "none", "none"));
  }
  
  /**
   * Returns the playerprofiles page based on search of skill or position.
   *
   * @return The Player profiles list page.
   */
  public static Result playerSearch(String field, String searchWord, String sortOrder, Integer page) {
    SearchFormData data2 = new SearchFormData();
    Form<SearchFormData> dataForm = Form.form(SearchFormData.class).fill(data2);
    Page<Player> playerPage = Player.find(field, searchWord, sortOrder, page);
    return ok(PlayerList.render(playerPage, "PlayersList", dataForm, field, searchWord));
  }
  
  /**
   * Returns the playerprofiles page based on name search.
   *
   * @return The Player profiles list page.
   */
  public static Result playerNameSearch() {
    SearchFormData data2 = new SearchFormData();
    Form<SearchFormData> dataForm = Form.form(SearchFormData.class).fill(data2);
    
    Form<SearchFormData> data = Form.form(SearchFormData.class).bindFromRequest();
    SearchFormData formData = data.get();
    Page<Player> playerPage = Player.find("name", formData.name, "name asc", 1);
    
    return ok(PlayerList.render(playerPage, "PlayerList", dataForm, "name", formData.name));
  }
  
  /**
   * Returns the player form page to edit player's profiles.
   */
  public static Result playerManage() {
    PlayerFormData data2 = new PlayerFormData();
    Form<PlayerFormData> dataForm = Form.form(PlayerFormData.class).fill(data2);
    
    //Form<PlayerFormData> data = Form.form(PlayerFormData.class).bindFromRequest();
    //PlayerFormData formData = data.get();
    //Player.addPlayer(formData);
    
    Map<String, Boolean> playerSkillMap = PlayerFields.getSkill();
    Map<String, Boolean> playerPosition = PlayerFields.getPosition();
    
    Page<Player> playerPage = Player.find("name asc", 1);
    return ok(PlayerForm.render("Player Form", dataForm, playerSkillMap, playerPosition));
    
  }

}
