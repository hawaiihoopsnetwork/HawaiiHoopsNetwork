package controllers;

import java.util.List;
import models.games.Game;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;
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
  public static Result allGames() {

    GameForm searchGame = new GameForm();
    Form<GameForm> formdata = Form.form(GameForm.class).fill(searchGame);
    return ok(AllGames.render("All Games", Game.getGames(), formdata));
  }

  /**
   * Brings a page showing info about a particular game.
   * 
   * @param name the name of the game
   * @return the page related to the game
   */
  public static Result getGame(String name) {
    Game game = Game.find().byId(name);

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
    GameForm data = new GameForm();
    Form<GameForm> gf = Form.form(GameForm.class).fill(data);

    Form<GameForm> gameForm = Form.form(GameForm.class).bindFromRequest();
    GameForm searched = gameForm.get();

    List<Game> results = Game.searchGames(searched.search);
    return ok(AllGames.render("Results", results, gf));

  }

}
