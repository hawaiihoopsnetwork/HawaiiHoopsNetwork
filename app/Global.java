import models.Address;
import models.Court;
import models.Player;
import models.User;
import models.games.Game;
import models.teams.Team;
import models.teams.TeamDB;
import play.Application;
import play.GlobalSettings;
import play.libs.F;
import play.Play;
import play.mvc.Http.*;

import play.mvc.*;

import static play.mvc.Results.*;
/**
 * Initialization for the application. will have three player's bios.
 * 
 * @author scotthonda
 * 
 */
public class Global extends GlobalSettings {

    /**
    @Override
    public F.Promise<SimpleResult> onHandlerNotFound(RequestHeader request) {

    }**/

    /**
   * Initialize the app with surfers.
   * 
   * @param app
   */
  public void onStart(Application app) {

    if (TeamDB.getTeams().size() == 0) {
      TeamDB.addTeam(new Team("teamName", "String location", "String teamType", "String skillLevel", "String roster", "String description"));
      TeamDB.addTeam(new Team("teamName2", "String location", "String teamType", "String skillLevel", "String roster", "String description"));
    }
    
    String adminEmail = Play.application().configuration().getString("hihoops.admin.email");
    String adminPassword = Play.application().configuration().getString("hihoops.admin.password");

    User user = User.getUser(adminEmail);

    if (user == null && Player.getPlayers().size() == 0) {

      Player player = Player.addPlayer("Ali G", "Intermediate", "Center", 10, 5, "5'5\"",
        "200", "Basketball, I love Basketball.", "Pickup Games", "");

      User newUser = User.addUser("Admin", adminEmail, adminPassword);
      newUser.setActivation_key(null);
      newUser.setPlayer(player);
      newUser.update();

      /**User SteveF = User.addUser("Steve", "Steve@gmail.com", "password");
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


      Player.addPlayer(new PlayerFormData("Steve F.", "SF", "Kapolei Park", "Beginner", "Power Forward", 15, 5,
          "5'5\"", "200", "Basketball, I love Basketball.", "Pickup Games", ""));
      Player.addPlayer(new PlayerFormData("Loa P.", "LP", "Aulani Park", "Competitive", "Point Gaurd", 5, 5, "5'5\"",
          "200", "Basketball, I love Basketball.", "Pickup Games", ""));
      Player.addPlayer(new PlayerFormData("Keith A.", "Albino", "Kapiolani Park", "College", "Small Forward", 20, 5,
          "5'5\"", "200", "Basketball, I love Basketball.", "Pickup Games", ""));
      Player.addPlayer(new PlayerFormData("Hector M.", "Malaz", "Hawaii Kai Park", "Intermediate", "Shooting Gaurd",
          25, 5, "5'5\"", "200", "Basketball, I love Basketball.", "Pickup Games", ""));**/
    }

    if (Court.getCourts().isEmpty()) {
        Address address = Address.addAddress("2500 Campus Rd", "Honolulu", "HI", "96822", "United States", (float) 21.2970, (float) -157.8170);
        Court court1 = Court.addCourt("University of Hawaii", null, "private", "indoor", (long)8, "full court", "wood", "good", true, address, "it's awsome!");

        Address address2 = Address.addAddress("2501 Campus Rd", "Honolulu", "HI", "96823", "United States", (float) 21.2980, (float) -157.8180);
        Court court2 = Court.addCourt("Chaminade", null, "private", "indoor", (long)8, "full court", "wood", "good", true, address2, "it's awsome!");

       /**
       * Court.addCourt("Aina Haina", "Private", (float) 21.2970, (float) -157.8170, "It's Awsome");
       * Court.addCourt("B", "Private", (float) 23.2970, (float) -157.8170, "It's Awsome");
       * Court.addCourt("B", "Private", "123 Somewhere", (float)1234, (float)12345.0, "It's Awsome");
       * Court.addCourt("C", "Private", "123 Somewhere", (float)1234, (float)12345.0, "It's Awsome");
       * Court.addCourt("D", "Private", "123 Somewhere", (float)1234, (float)12345.0, "It's Awsome");
       * Court.addCourt("E", "Private", "123 Somewhere", (float)1234, (float)12345.0, "It's Awsome");
       * Court.addCourt("F", "Private", "123 Somewhere", (float)1234, (float)12345.0, "It's Awsome");
       * Court.addCourt("G", "Private", "123 Somewhere", (float)1234, (float)12345.0, "It's Awsome");
       * Court.addCourt("H", "Private", "123 Somewhere", (float)1234, (float)12345.0, "It's Awsome");
       * Court.addCourt("I", "Private", "123 Somewhere", (float)1234, (float)12345.0, "It's Awsome");
       */
    }

    if (Team.getTeams().size() == 0) {
      Team.addTeam(new Team("Kaimuki Ballas", "Kaimuki Community Park", "Recreational", "Male", "Alex G., Loa P.", ""));
      Team.addTeam(new Team("Kapolei Boys", "Kapolei Community Park", "Recreational", "Male", "Alex G., Loa P.", ""));
      Team.addTeam(new Team("Aina-t Pros", "Aina Haina Community Park", "Recreational", "Female", "Alex G., Loa P.", ""));
      Team.addTeam(new Team("Manoa B-Ballas", "Manoa Community Park", "Recreational", "Co-ed", "Alex G., Loa P.", ""));
      Team.addTeam(new Team("Makiki Kings", "Makiki Community Park", "Recreational", "Male", "Alex G., Loa P.", ""));
    }

    if (Game.getGames().size() == 0) {
      Game.addGame(new Game("Kapolei PUG", "3:00PM", "cMarch 12", "Kapolei park", "Public", "Nightly", "Beginner",
          "Loa P., Alex G.", "admin"));
      Game.addGame(new Game("Aina Hina PUG", "3:00PM", "cMarch 10", "Aina Hina park", "Private", "Nightly", "Beginner",
          "Loa P., Alex G.", "admin"));
      Game.addGame(new Game("Waianae Nightly", "6:00PM", "cMarch 11", "Waianae park", "Public", "Nightly", "Beginner",
          "Loa P., Alex G.", "admin"));
      Game.addGame(new Game("Makakilo Lunchtime", "12:00PM", "cMarch 25", "Makakilo Rec", "Public", "Nightly",
          "Beginner", "Loa P., Alex G.", "admin"));
      Game.addGame(new Game("Ewa Beach Practice", "5:00PM", "dApril 2", "Kapolei park", "Private", "Nightly",
          "Beginner", "Loa P., Alex G.", "admin"));
    }



  }
}
