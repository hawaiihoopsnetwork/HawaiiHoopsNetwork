import models.*;
import models.games.Game;
import models.leagues.League;
import models.leagues.LeagueDB;
import models.teams.Team;
import models.teams.TeamDB;
import play.*;
import play.mvc.*;
//import play.Application;
//import play.GlobalSettings;
//import play.Play;

import play.libs.F.*;
import play.mvc.Http.*;
import views.html.errors.PageNotFound;
import static play.mvc.Results.*;

/**
 * Initialization for the application. will have three player's bios.
 * 
 * @author scotthonda
 * 
 */
public class Global extends GlobalSettings {


    public Promise<SimpleResult> onHandlerNotFound(RequestHeader request) {
        return Promise.<SimpleResult>pure(notFound(
                PageNotFound.render()
        ));
    }

    public Promise<SimpleResult> onBadRequest(RequestHeader request, String error) {
        return Promise.<SimpleResult>pure(badRequest("Don't try to hack the URI!"));
    }

  /**
   * Initialize the app with surfers.
   * 
   * @param app
   */
  public void onStart(Application app) {

    if (TeamDB.getTeams().size() == 0) {
      Team team1 = new Team("Blazers", "String location", "String teamType", "String skillLevel", "String roster",
          "String description",
          "http://upload.wikimedia.org/wikipedia/en/0/06/Portland_Trail_Blazers_alternate_logo.svg");
      Team team2 = new Team("Rockets", "String location", "String teamType", "String skillLevel", "String roster",
          "String description",
          "http://cf.juggle-images.com/matte/white/280x280/houston-rockets-script-logo-5-primary.jpg");
      TeamDB.addTeam(team1);
      TeamDB.addTeam(team2);
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

        Hours hours = Hours.addDuration(1, 11, 30, 1, 4, 0);
        hours.addCourt(court1);
        //court1.addPlayer();


      Address address2 =
          Address.addAddress("2501 Campus Rd", "Honolulu", "HI", "96823", "United States", (float) 21.2980,
              (float) -157.8180);
      Court court2 =
          Court.addCourt("Chaminade", null, "private", "indoor", (long) 8, "full court", "wood", "good", true,
              address2, "it's awsome!");

      Address address3 =
          Address.addAddress("1313 Makiki Street", "Honolulu", "HI", "96814", "United States", (float) 21.300859,
              (float) -157.837527);

      Court court3 =
          Court.addCourt("Cartwright Neighborhood Park", null, "public", "outdoor", (long) 8, "full court", "wood",
              "good", true, address3, "it's awesome");

      Address address4 =
          Address.addAddress("1400 Kalihi Street", "Honolulu", "HI", "96819", "United States", (float) 21.3349115,
              (float) -157.8710021);

      Court court4 =
          Court.addCourt("Kamehameha Community Park", null, "public", "outdoor", (long) 8, "full court", "black top",
              "good", true, address4, "It's awesome.");

      Address address5 =
          Address.addAddress("2329 Kalihi Street", "Honolulu", "HI", "96819", "United States", (float) 21.344941,
              (float) -157.862468);

      Court court5 =
          Court.addCourt("Kalihi Uka Community Park", null, "public", "outdoor", (long) 8, "full court", "black top",
              "good", true, address5, "it's awesome");

      Address address6 =
          Address.addAddress("500 University Avenue", "Honolulu", "HI", "96826", "United States", (float) 21.286924,
              (float) -157.8272629);

      Court court6 =
          Court.addCourt("Ala Wai Community Park", null, "public", "outdoor", (long) 8, "full court", "black top",
              "good", true, address6, "It's awesome.");

      Address address7 =
          Address.addAddress("2331 Kanealii Avenue", "Honolulu", "HI", "96813", "United States", (float) 21.3205757,
              (float) -157.8435066);

      Court court7 =
          Court.addCourt("Booth District Park", null, "public", "indoor", (long) 8, "full court", "wood", "good", true,
              address7, "It's awesome.");

      Address address8 =
          Address.addAddress("1159 Ala Lilikoi Place", "Honolulu", "HI", "96818", "United States", (float) 21.3522987,
              (float) -157.9108152);

      Court court8 =
          Court.addCourt("Salt Lake District Park", null, "public", "outdoor", (long) 8, "full court", "black top",
              "good", true, address7, "It's awesome");

    }

    if (Team.getTeams().size() == 0) {
      Team.addTeam(new Team("Kaimuki Ballas", "Kaimuki Community Park", "Male", "Recreational", "Alex G., Loa P.", "",
          "http://upload.wikimedia.org/wikipedia/en/0/06/Portland_Trail_Blazers_alternate_logo.svg"));
      Team.addTeam(new Team("Kapolei Boys", "Kapolei Community Park", "Male", "Recreational", "Alex G., Loa P.", "",
          "http://cf.juggle-images.com/matte/white/280x280/houston-rockets-script-logo-5-primary.jpg"));
      Team.addTeam(new Team("Aina-t Pros", "Aina Haina Community Park", "Female", "Recreational", "Alex G., Loa P.",
          "", "image"));
      Team.addTeam(new Team("Manoa B-Ballas", "Manoa Community Park", "Co-ed", "Recreational", "Alex G., Loa P.", "",
          "image"));
      Team.addTeam(new Team("Makiki Kings", "Makiki Community Park", "Male", "Recreational", "Alex G., Loa P.", "",
          "image"));
      Team.addTeam(new Team("Cartwright Kings", "Cartwright Community Park", "Male", "Recreational", "Alex G., Loa P.", "",
          "image"));
    }

    if (LeagueDB.getLeagues().size() == 0) {
      League league = new League("Example League");
      for(int i = 1; i <= Team.getTeams().size(); i++){
        league.addTeam(Team.getTeam(i));
      }
      league.setNumTeams(6);
      league.setLocation("Manoa Community Park");
      league.setStartDate("05/01/2015");
      LeagueDB.addLeague(league);
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
