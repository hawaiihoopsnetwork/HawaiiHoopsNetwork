package controllers;

import java.util.Collections;
import java.util.List;
import models.games.Game;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;
import views.formdata.games.GameForm;
import views.formdata.games.SearchSortGames;
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
  public static Result allGames() {
    SearchSortGames ssg = new SearchSortGames();
    Form<SearchSortGames> ssgBlank = Form.form(SearchSortGames.class).fill(ssg);

    List<Game> games = Game.getGames();
    Collections.sort(games, new SearchSortGames.ByDate());

    return ok(AllGames.render("All Games", games, ssgBlank));
  }

  /**
   * Brings a page showing info about a particular game.
   * 
   * @param name the name of the game
   * @return the page related to the game
   */
  public static Result getGame(String name) {
    Game game = Game.find().where().eq("name", name).findUnique();

    if (game == null) {
      throw new RuntimeException("Not a valid game.");
    }
    else {
      return ok(SingleGame.render("Game: " + game.getName(), game));
    }
  }

  /**
   * A page containing the fields required to create a game.
   * 
   * @return the Create Game page.
   */
  public static Result createGame() {
    GameForm gameForm = new GameForm();
    Form<GameForm> formdata = Form.form(GameForm.class).fill(gameForm);

    return ok(CreateGame.render("Create Game", "Create", formdata));
  }

  /**
   * Adds a game to the list of upcoming games.
   * 
   * @return the list of upcoming games if the form has no errors
   */
  public static Result addGame() {

    Form<GameForm> gameForm = Form.form(GameForm.class).bindFromRequest();

    if (gameForm.hasErrors()) {
      return badRequest(CreateGame.render("Create Game", "Create", gameForm));
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
  public static Result editGame(String name) {
    GameForm data = new GameForm(Game.find().byId(name));
    Form<GameForm> formdata = Form.form(GameForm.class).fill(data);
    return ok(CreateGame.render("Edit Game", "Edit", formdata));

  }

  /**
   * Search for games.
   * 
   * @return All games page with the results
   */
  public static Result searchResults() {
    SearchSortGames ssg = new SearchSortGames();
    Form<SearchSortGames> ssgBlank = Form.form(SearchSortGames.class).fill(ssg);

    Form<SearchSortGames> form = Form.form(SearchSortGames.class).bindFromRequest();
    SearchSortGames searched = form.get();
    List<Game> results = Game.searchGames(searched.search);

    return ok(AllGames.render("Results", results, ssgBlank));

  }

  /**
   * Returns a sorted list of games based on location.
   * 
   * @return All Games page
   */
  public static Result sortByLocation() {

    SearchSortGames ssg = new SearchSortGames();
    Form<SearchSortGames> ssgBlank = Form.form(SearchSortGames.class).fill(ssg);

    List<Game> games = Game.getGames();
    Collections.sort(games, new SearchSortGames.ByLocation());

    return ok(AllGames.render("Games by Date", games, ssgBlank));
  }

  /**
   * Returns a sorted list of games based on skill level.
   * 
   * @return All Games page.
   */
  public static Result sortBySkillLevel() {

    SearchSortGames ssg = new SearchSortGames();
    Form<SearchSortGames> ssgBlank = Form.form(SearchSortGames.class).fill(ssg);

    List<Game> games = Game.getGames();
    Collections.sort(games, new SearchSortGames.BySkillLevel());

    return ok(AllGames.render("Games by Date", games, ssgBlank));
  }

}
