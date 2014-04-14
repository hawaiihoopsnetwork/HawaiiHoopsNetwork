import controllers.Users;
import models.Court;
import models.Player;
import models.User;
import models.games.Game;
import play.Application;
import play.GlobalSettings;
import views.formdata.PlayerFormData;
import views.formdata.games.GameForm;
import play.Play;

/**
 * Initialization for the application.
 * will have three player's bios.
 * @author scotthonda
 *
 */
public class Global extends GlobalSettings {
   
  /**
   * Initialize the app with surfers.
   * @param app 
   */
  public void onStart(Application app) {
    
      String adminEmail = Play.application().configuration().getString("hihoops.admin.email");
      String adminPassword = Play.application().configuration().getString("hihoops.admin.password");

      User user = User.getUser(adminEmail);

      if (user == null) {
          User newUser = User.addUser("user", adminEmail, adminPassword);
          newUser.setActivation_key(null);
          newUser.update();
          
          User SteveF = User.addUser("Steve", "Steve@gmail.com", "password");
          SteveF.setActivation_key(null);
          SteveF.update();
          
          User Loa = User.addUser("Loa", "Loa@gmail.com", "password");
          Loa.setActivation_key(null);
          Loa.update();
          
          User Keith = User.addUser("Keith", "Keith@gmail.com", "password");
          Keith.setActivation_key(null);
          Keith.update();
          
          User Hector = User.addUser("Hector", "Hector@gmail.com", "password");
          Hector.setActivation_key(null);
          Hector.update();
      }

      if (Court.getCourts().isEmpty()) {
          Court.addCourt("Aina Haina", "Private", "123 Somewhere", (float) 21.2970, (float) -157.8170, "It's Awsome");
          Court.addCourt("B", "Private", "123 Somewhere", (float) 23.2970, (float) -157.8170, "It's Awsome");
          /*Court.addCourt("B", "Private", "123 Somewhere", (float)1234, (float)12345.0, "It's Awsome");
          Court.addCourt("C", "Private", "123 Somewhere", (float)1234, (float)12345.0, "It's Awsome");
          Court.addCourt("D", "Private", "123 Somewhere", (float)1234, (float)12345.0, "It's Awsome");
          Court.addCourt("E", "Private", "123 Somewhere", (float)1234, (float)12345.0, "It's Awsome");
          Court.addCourt("F", "Private", "123 Somewhere", (float)1234, (float)12345.0, "It's Awsome");
          Court.addCourt("G", "Private", "123 Somewhere", (float)1234, (float)12345.0, "It's Awsome");
          Court.addCourt("H", "Private", "123 Somewhere", (float)1234, (float)12345.0, "It's Awsome");
          Court.addCourt("I", "Private", "123 Somewhere", (float)1234, (float)12345.0, "It's Awsome");*/
      }
      
      

      Game.addGame(new GameForm(new Game("Kapolei PUG", "3:00PM", "3.13.14", "Kapolei park", "Public", "Nightly", "Beginner", "Loa P., Alex G.")));
      Game.addGame(new GameForm(new Game("Aina Hina PUG", "3:00PM", "3.13.14", "Aina Hina park", "Private", "Nightly", "Beginner","Loa P., Alex G.")));
      Game.addGame(new GameForm(new Game("Waianae Nightly", "6:00PM", "3.13.14", "Waianae park", "Public", "Nightly", "Beginner", "Loa P., Alex G.")));
      Game.addGame(new GameForm(new Game("Makakilo Lunchtime", "12:00PM", "3.13.14", "Makakilo Rec", "Public", "Nightly", "Beginner", "Loa P., Alex G.")));
      Game.addGame(new GameForm(new Game("Ewa Beach Practice", "5:00PM", "3.13.14", "Kapolei park", "Private", "Nightly", "Beginner", "Loa P., Alex G.")));
      
      if (Player.getPlayers().isEmpty()) {
        Player.addPlayer(new PlayerFormData("Alex G.", "Ali G", "Paki Park", "Intermediate", "Center", 10, 5, "5'5\"", "200", "Basketball, I love Basketball.", "Pickup Games", ""));
        Player.addPlayer(new PlayerFormData("Steve F.", "SF", "Kapolei Park", "Beginner", "Power Forward", 15, 5, "5'5\"", "200", "Basketball, I love Basketball.", "Pickup Games", ""));
        Player.addPlayer(new PlayerFormData("Loa P.", "LP", "Aulani Park", "Competitive", "Point Gaurd", 5, 5, "5'5\"", "200", "Basketball, I love Basketball.", "Pickup Games", ""));
        Player.addPlayer(new PlayerFormData("Keith A.", "Albino", "Kapiolani Park", "College", "Small Forward", 20, 5, "5'5\"", "200", "Basketball, I love Basketball.", "Pickup Games", ""));
        Player.addPlayer(new PlayerFormData("Hector M.", "Malaz", "Hawaii Kai Park", "Intermediate", "Shooting Gaurd", 25, 5, "5'5\"", "200", "Basketball, I love Basketball.", "Pickup Games", ""));
      }
  }
}
