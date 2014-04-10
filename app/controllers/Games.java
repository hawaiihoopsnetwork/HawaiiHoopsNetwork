package controllers;

import java.util.Collections;
import java.util.List;
import com.avaje.ebean.Page;
import models.games.Game;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.data.Form;
import views.formdata.SearchFormData;
import views.formdata.games.GameForm;
import views.html.games.SingleGame;
import views.html.games.CreateGame;
import views.html.games.AllGames;

/**
 * Implements the controllers for this application.
 */
public class Games extends Controller {

  /**
   * Returns a page containing all upcoming games.
   * 
   * @return the list of games
   */
  @Security.Authenticated(Secured.class)
  public static Result allGames(Integer page, String sort) {

    SearchFormData sfd = new SearchFormData();
    Form<SearchFormData> stuff = Form.form(SearchFormData.class).fill(sfd);

    Page<Game> games = Game.find("date asc", page);
    return ok(AllGames.render("All Games", games, stuff, Secured.isLoggedIn(ctx()), sort));
  }

  /**
   * Brings a page showing info about a particular game.
   * 
   * @param name the name of the game
   * @return the page related to the game
   */
  @Security.Authenticated(Secured.class)
  public static Result getGame(String name) {
    Game game = Game.find().where().eq("name", name).findUnique();

    if (game == null) {
      throw new RuntimeException("Not a valid game.");
    }
    else {
      return ok(SingleGame.render("Game: " + game.getName(), game, Secured.isLoggedIn(ctx())));
    }
  }

  /**
   * A page containing the fields required to create a game.
   * 
   * @return the Create Game page.
   */
  @Security.Authenticated(Secured.class)
  public static Result createGame() {
    GameForm gameForm = new GameForm();
    Form<GameForm> formdata = Form.form(GameForm.class).fill(gameForm);

    return ok(CreateGame.render("Create Game", "Create", formdata, Secured.isLoggedIn(ctx())));
  }

  /**
   * Adds a game to the list of upcoming games.
   * 
   * @return the list of upcoming games if the form has no errors
   */
  @Security.Authenticated(Secured.class)
  public static Result addGame() {

    Form<GameForm> gameForm = Form.form(GameForm.class).bindFromRequest();

    if (gameForm.hasErrors()) {
      return badRequest(CreateGame.render("Create Game", "Create", gameForm, Secured.isLoggedIn(ctx())));
    }
    else {
      GameForm game = gameForm.get();
      Game.addGame(game);
      return redirect("/games/view/" + game.name);
    }
  }

  /**
   * Brings up the form required to edit the game.
   * 
   * @param name name of game
   * @return the create game page
   */
  @Security.Authenticated(Secured.class)
  public static Result editGame(String name) {
    GameForm data = new GameForm(Game.find().byId(name));
    Form<GameForm> formdata = Form.form(GameForm.class).fill(data);
    return ok(CreateGame.render("Edit Game", "Edit", formdata, Secured.isLoggedIn(ctx())));

  }

  /**
   * Search for games.
   * 
   * @return All games page with the results
   */
  @Security.Authenticated(Secured.class)
  public static Result searchResults(Integer page) {
    SearchFormData sfd = new SearchFormData();
    Form<SearchFormData> stuff = Form.form(SearchFormData.class).fill(sfd);

    Form<SearchFormData> sfd2 = Form.form(SearchFormData.class).bindFromRequest();
    SearchFormData search = sfd2.get();

    Page<Game> results = Game.find(search.term, "date asc", page);
    return ok(AllGames.render("Results", results, stuff, Secured.isLoggedIn(ctx()), "date asc"));

  }

}
