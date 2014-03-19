package controllers;

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

  public static Result allGames() {
    return ok(AllGames.render("All Games", Game.getGames()));
  }

  public static Result getGame(long id) {
    Game game = Game.find().byId(id);

    if (game == null) {
      throw new RuntimeException("Not a valid game.");
    }
    else {
      return ok(SingleGame.render("Game: " + game.getId(), game));
    }
  }

  public static Result createGame() {
    GameForm gameForm = new GameForm();
    Form<GameForm> formdata = Form.form(GameForm.class).fill(gameForm);

    return ok(CreateGame.render("Create Game", formdata));
  }

  public static Result addGame() {

    Form<GameForm> gameForm = Form.form(GameForm.class).bindFromRequest();

    if (gameForm.hasErrors()) {
      return badRequest(CreateGame.render("Create Game", gameForm));
    }
    else {
      GameForm game = gameForm.get();

      Game.addGame(game.time, game.date, game.location, game.type, game.frequency, game.avgSklLvl, game.players);
      // TODO save edits to a game.
      return redirect("/games/list");
    }
  }

  public static Result editGame(long id) {
    Game game = Game.find().byId(id);
    GameForm data = new GameForm(game);
    Form<GameForm> formdata = Form.form(GameForm.class).fill(data);
    return ok(CreateGame.render("Edit Game", formdata));

  }

}
