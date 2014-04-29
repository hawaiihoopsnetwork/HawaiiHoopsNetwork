import controllers.Users;
import models.Court;
import models.Player;
import models.User;
import models.games.Game;
import models.teams.Team;
import models.teams.TeamDB;
import play.Application;
import play.GlobalSettings;
import views.formdata.PlayerFormData;
import views.formdata.games.GameForm;
import play.Play;

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

    if (TeamDB.getTeams().size() == 0) {
      TeamDB.addTeam(new Team("teamName", "String location", "String teamType", "String skillLevel", "String roster",
          "String description"));
      TeamDB.addTeam(new Team("teamName2", "String location", "String teamType", "String skillLevel", "String roster",
          "String description"));
    }

    String adminEmail = Play.application().configuration().getString("hihoops.admin.email");
    String adminPassword = Play.application().configuration().getString("hihoops.admin.password");

    User user = User.getUser(adminEmail);

    User newUser = null;
    User SteveF = null;
    User Loa = null;
    User Keith = null;
    User Hector = null;

    if (user == null) {

      newUser = User.addUser("Alex G.", adminEmail, adminPassword);
      newUser.setActivation_key(null);
      newUser.update();

      SteveF = User.addUser("Steve F.", "Steve@gmail.com", "password");
      SteveF.setActivation_key(null);
      SteveF.update();

      Loa = User.addUser("Loa P.", "Loa@gmail.com", "password");
      Loa.setActivation_key(null);
      Loa.update();

      Keith = User.addUser("Keith A.", "Keith@gmail.com", "password");
      Keith.setActivation_key(null);
      Keith.update();

      Hector = User.addUser("Hector M.", "Hector@gmail.com", "password");
      Hector.setActivation_key(null);
      Hector.update();
    }

    if (Court.getCourts().isEmpty()) {
      Court.addCourt("Aina Haina", "Private", "123 Somewhere", (float) 21.2970, (float) -157.8170, "It's Awsome");
      Court.addCourt("B", "Private", "123 Somewhere", (float) 23.2970, (float) -157.8170, "It's Awsome");
      /*
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
      Game.addGame(new Game("Ewa Beach Practice", "4", "2", "2014", "17", "00", "Ewa Beach", "Private", "Nightly", "Beginner",
          "Loa P., Alex G.", SteveF));
      Game.addGame(new Game("Makakilo Lunchtime", "5", "2", "2014", "13", "00", "Makakilo Rec Center", "Public", "Nightly",
          "Advanced", "Loa P., Alex G.", Keith));
      Game.addGame(new Game("School PUG", "6", "4", "2014", "18", "00", "UH Manoa", "Public", "Bi-Weekly", "Rookie",
          "Loa P., Alex G.", Hector));
      Game.addGame(new Game("Kapolei PUG", "9", "24", "2014", "5", "00", "Kapolei Park", "Private", "Daily", "Competitive",
          "Loa P., Alex G.", Loa));
      Game.addGame(new Game("Waianae PUG", "4", "2", "2014", "17", "00", "Waianae Park", "Public", "One Time", "Advanced",
          "Loa P., Alex G.", newUser));

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
