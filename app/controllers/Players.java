package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.avaje.ebean.Page;
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
  
  /**
   * Returns the playerprofiles page
   *
   * @return The Player profiles list page.
   */
  public static Result players(String sortOrder, Integer page) {
    SearchFormData data2 = new SearchFormData();
    Form<SearchFormData> dataForm = Form.form(SearchFormData.class).fill(data2);
    Page<Player> playerPage = Player.find(sortOrder, page);
    return ok(PlayerList.render(playerPage, "PlayerList", dataForm, "none", "none"));
  }
  
  public static Result playerSearch(String field, String searchWord, String sortOrder, Integer page) {
    SearchFormData data2 = new SearchFormData();
    Form<SearchFormData> dataForm = Form.form(SearchFormData.class).fill(data2);
    Page<Player> playerPage = Player.find(field, searchWord, sortOrder, page);
    return ok(PlayerList.render(playerPage, "PlayersList", dataForm, field, searchWord));
  }
  
  /**
   * Returns the playerprofiles page
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
  

}
