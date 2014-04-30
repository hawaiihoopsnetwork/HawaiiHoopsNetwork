import controllers.Users;
import models.Address;
import models.Court;
import models.Player;
import models.User;
import models.games.Game;
import models.leagues.League;
import models.leagues.LeagueDB;
import models.teams.Team;
import models.teams.TeamDB;
import play.Application;
import play.GlobalSettings;
import views.formdata.PlayerFormData;
import views.formdata.games.GameForm;
import play.Play;
import play.mvc.Http.Context;

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

    if (TeamDB.getTeams().size() == 0) {
      TeamDB.addTeam(new Team("Blazers", "String location", "String teamType", "String skillLevel", "String roster",
          "String description",
          "http://upload.wikimedia.org/wikipedia/en/0/06/Portland_Trail_Blazers_alternate_logo.svg"));
      TeamDB.addTeam(new Team("Rockets", "String location", "String teamType", "String skillLevel", "String roster",
          "String description",
          "http://cf.juggle-images.com/matte/white/280x280/houston-rockets-script-logo-5-primary.jpg"));
    }

    String adminEmail = Play.application().configuration().getString("hihoops.admin.email");
    String adminPassword = Play.application().configuration().getString("hihoops.admin.password");

    User user = User.getUser(adminEmail);

    if (user == null && Player.getPlayers().size() == 0) {

      Player player =
          Player.addPlayer("Ali G", "Intermediate", "Center", 10, 5, "5'5\"", "200", "Basketball, I love Basketball.",
              "Pickup Games", "");

      User newUser = User.addUser("Alex G.", adminEmail, adminPassword);
      newUser.setActivation_key(null);
      newUser.setPlayer(player);
      newUser.update();

      player =
          Player.addPlayer("SF", "Intermediate", "Center", 10, 5, "5'5\"", "200", "Basketball, I love Basketball.",
              "Pickup Games", "");

      newUser = User.addUser("Steve F.", "Steve@gmail.com", "password");
      newUser.setActivation_key(null);
      newUser.setPlayer(player);
      newUser.update();

      /**
       * User SteveF = User.addUser("Steve", "Steve@gmail.com", "password"); SteveF.setActivation_key(null);
       * SteveF.update();
       * 
       * User Loa = User.addUser("Loa", "Loa@gmail.com", "password"); Loa.setActivation_key(null); Loa.update();
       * 
       * User Keith = User.addUser("Keith", "Keith@gmail.com", "password"); Keith.setActivation_key(null);
       * Keith.update();
       * 
       * User Hector = User.addUser("Hector", "Hector@gmail.com", "password"); Hector.setActivation_key(null);
       * Hector.update();
       * 
       * 
       * Player.addPlayer(new PlayerFormData("Steve F.", "SF", "Kapolei Park", "Beginner", "Power Forward", 15, 5,
       * "5'5\"", "200", "Basketball, I love Basketball.", "Pickup Games", "")); Player.addPlayer(new
       * PlayerFormData("Loa P.", "LP", "Aulani Park", "Competitive", "Point Gaurd", 5, 5, "5'5\"", "200",
       * "Basketball, I love Basketball.", "Pickup Games", "")); Player.addPlayer(new PlayerFormData("Keith A.",
       * "Albino", "Kapiolani Park", "College", "Small Forward", 20, 5, "5'5\"", "200",
       * "Basketball, I love Basketball.", "Pickup Games", "")); Player.addPlayer(new PlayerFormData("Hector M.",
       * "Malaz", "Hawaii Kai Park", "Intermediate", "Shooting Gaurd", 25, 5, "5'5\"", "200",
       * "Basketball, I love Basketball.", "Pickup Games", ""));
       **/
    }

    if (Court.getCourts().isEmpty()) {
      Address address =
          Address.addAddress("2500 Campus Rd", "Honolulu", "HI", "96822", "United States", (float) 21.2970,
              (float) -157.8170);
      Court court1 =
          Court.addCourt("University of Hawaii", null, "private", "indoor", (long) 8, "full court", "wood", "good",
              true, address, "it's awsome!");

      Address address2 =
          Address.addAddress("2501 Campus Rd", "Honolulu", "HI", "96823", "United States", (float) 21.2980,
              (float) -157.8180);
      Court court2 =
          Court.addCourt("Chaminade", null, "private", "indoor", (long) 8, "full court", "wood", "good", true,
              address2, "it's awsome!");

      /**
       * Court.addCourt("Aina Haina", "Private", (float) 21.2970, (float) -157.8170, "It's Awsome"); Court.addCourt("B",
       * "Private", (float) 23.2970, (float) -157.8170, "It's Awsome"); Court.addCourt("B", "Private", "123 Somewhere",
       * (float)1234, (float)12345.0, "It's Awsome"); Court.addCourt("C", "Private", "123 Somewhere", (float)1234,
       * (float)12345.0, "It's Awsome"); Court.addCourt("D", "Private", "123 Somewhere", (float)1234, (float)12345.0,
       * "It's Awsome"); Court.addCourt("E", "Private", "123 Somewhere", (float)1234, (float)12345.0, "It's Awsome");
       * Court.addCourt("F", "Private", "123 Somewhere", (float)1234, (float)12345.0, "It's Awsome");
       * Court.addCourt("G", "Private", "123 Somewhere", (float)1234, (float)12345.0, "It's Awsome");
       * Court.addCourt("H", "Private", "123 Somewhere", (float)1234, (float)12345.0, "It's Awsome");
       * Court.addCourt("I", "Private", "123 Somewhere", (float)1234, (float)12345.0, "It's Awsome");
       */
    }

    if (Team.getTeams().size() == 0) {
      Team.addTeam(new Team("Kaimuki Ballas", "Kaimuki Community Park", "Recreational", "Male", "Alex G., Loa P.", "",
          "image"));
      Team.addTeam(new Team("Kapolei Boys", "Kapolei Community Park", "Recreational", "Male", "Alex G., Loa P.", "",
          "image"));
      Team.addTeam(new Team("Aina-t Pros", "Aina Haina Community Park", "Recreational", "Female", "Alex G., Loa P.",
          "", "image"));
      Team.addTeam(new Team("Manoa B-Ballas", "Manoa Community Park", "Recreational", "Co-ed", "Alex G., Loa P.", "",
          "image"));
      Team.addTeam(new Team("Makiki Kings", "Makiki Community Park", "Recreational", "Male", "Alex G., Loa P.", "",
          "image"));
    }

    if (LeagueDB.getLeagues().size() == 0) {
      LeagueDB.addLeague(new League("Example League"));
    }

    if (Game.getGames().size() == 0) {

      Player player =
          Player.addPlayer("AG", "Intermediate", "Center", 10, 5, "5'5\"", "200", "Basketball, I love Basketball.",
              "Pickup Games", "");

      User newUser = User.addUser("Alexander G.", "alex@gmail.com", "password");
      newUser.setActivation_key(null);
      newUser.setPlayer(player);
      newUser.update();
      // This shouldn't show up when the app is loaded.
      Game.addGame(new Game("Test Delete", "3", "25", "2014", "5", "00", "test", "Public", "Daily", "Beginner",
          "Alex G.", newUser));
      // These should
      Game.addGame(new Game("Ewa Beach Practice", "4", "2", "2014", "17", "00", "Ewa Beach", "Private", "Nightly",
          "Beginner", "Loa P., Alex G.", newUser));
      Game.addGame(new Game("Makakilo Lunchtime", "5", "2", "2014", "13", "00", "Makakilo Rec Center", "Public",
          "Nightly", "Advanced", "Loa P., Alex G.", newUser));
      Game.addGame(new Game("School PUG", "6", "4", "2014", "18", "00", "UH Manoa", "Public", "Bi-Weekly", "Rookie",
          "Loa P., Alex G.", newUser));
      Game.addGame(new Game("Kapolei PUG", "9", "24", "2014", "5", "00", "Kapolei Park", "Private", "Daily",
          "Competitive", "Loa P., Alex G.", newUser));
      Game.addGame(new Game("Waianae PUG", "4", "2", "2014", "17", "00", "Waianae Park", "Public", "One Time",
          "Advanced", "Loa P., Alex G.", newUser));

    }

  }
}