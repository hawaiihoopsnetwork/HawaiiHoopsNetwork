import controllers.Users;
import models.Court;
import models.Player;
import models.User;
import models.games.Game;
import play.Application;
import play.GlobalSettings;
import views.formdata.PlayerFormData;
import views.formdata.games.GameForm;

/**
 * Initialization for the application. will have three player's bios.
 * 
 * @author scotthonda
 * 
 */
public class Global extends GlobalSettings {

  /**
   * Initialize the app with surfers.
   * 
   * @param app
   */
  public void onStart(Application app) {

    if (Court.getCourts().size() == 0) {
      Court.addCourt("Aina Haina", "Private", "123 Somewhere", (float) 1234, (float) 12345.0, "It's Awsome");
      Court.addCourt("B", "Private", "123 Somewhere", (float) 1234, (float) 12345.0, "It's Awsome");
      Court.addCourt("C", "Private", "123 Somewhere", (float) 1234, (float) 12345.0, "It's Awsome");
      Court.addCourt("D", "Private", "123 Somewhere", (float) 1234, (float) 12345.0, "It's Awsome");
      Court.addCourt("E", "Private", "123 Somewhere", (float) 1234, (float) 12345.0, "It's Awsome");
      Court.addCourt("F", "Private", "123 Somewhere", (float) 1234, (float) 12345.0, "It's Awsome");
      Court.addCourt("G", "Private", "123 Somewhere", (float) 1234, (float) 12345.0, "It's Awsome");
      Court.addCourt("H", "Private", "123 Somewhere", (float) 1234, (float) 12345.0, "It's Awsome");
      Court.addCourt("I", "Private", "123 Somewhere", (float) 1234, (float) 12345.0, "It's Awsome");
    }

    
    if (Game.getGames().size() == 0) {
      Game.addGame(new Game("Kapolei PUG", "3:00PM", "March 12", "Kapolei park", "Public", "Nightly",
          "Beginner", "Loa P., Alex G."));
      Game.addGame(new Game("Aina Hina PUG", "3:00PM", "March 10", "Aina Hina park", "Private", "Nightly",
          "Beginner", "Loa P., Alex G."));
      Game.addGame(new Game("Waianae Nightly", "6:00PM", "March 11", "Waianae park", "Public", "Nightly",
          "Beginner", "Loa P., Alex G."));
      Game.addGame(new Game("Makakilo Lunchtime", "12:00PM", "March 25", "Makakilo Rec", "Public",
          "Nightly", "Beginner", "Loa P., Alex G."));
      Game.addGame(new Game("Ewa Beach Practice", "5:00PM", "April 2", "Kapolei park", "Private",
          "Nightly", "Beginner", "Loa P., Alex G."));
    }
    
    

    if (Player.getPlayers().size() == 0) {
      Player.addPlayer(new PlayerFormData("Alex G.", "Ali G", "Paki Park", "Intermediate", "Center", 10, 5, "5'5\"",
          "200", "Basketball, I love Basketball.", "Pickup Games", ""));
      Player.addPlayer(new PlayerFormData("Steve F.", "SF", "Kapolei Park", "Beginner", "Power Forward", 15, 5,
          "5'5\"", "200", "Basketball, I love Basketball.", "Pickup Games", ""));
      Player.addPlayer(new PlayerFormData("Loa P.", "LP", "Aulani Park", "Competitive", "Point Gaurd", 5, 5, "5'5\"",
          "200", "Basketball, I love Basketball.", "Pickup Games", ""));
      Player.addPlayer(new PlayerFormData("Keith A.", "Albino", "Kapiolani Park", "College", "Small Forward", 20, 5,
          "5'5\"", "200", "Basketball, I love Basketball.", "Pickup Games", ""));
      Player.addPlayer(new PlayerFormData("Hector M.", "Malaz", "Hawaii Kai Park", "Intermediate", "Shooting Gaurd",
          25, 5, "5'5\"", "200", "Basketball, I love Basketball.", "Pickup Games", ""));
    }
  }
}