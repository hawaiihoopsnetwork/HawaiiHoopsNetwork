import models.Court;
import models.Player;
import models.games.Game;
import models.teams.Team;
import models.teams.TeamDB;
import play.Application;
import play.GlobalSettings;
import views.formdata.PlayerFormData;
import views.formdata.games.GameForm;

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
      Court.addCourt("Aina Haina", "Private", "123 Somewhere", (float)1234, (float)12345.0, "It's Awsome");
      
      Team team = new Team("Trailblazers", "http://f00.inventorspot.com/images/July%208th%20&%2010th%20-%20Sports%20Logos2.JPG", 0);
      TeamDB.addTeam(team);
      Team team2 = new Team("Rockets", "http://f00.inventorspot.com/images/July%208th%20&%2010th%20-%20Sports%20Logos2.JPG", 0);
      TeamDB.addTeam(team2);
      Team team3 = new Team("Warriors", "http://f00.inventorspot.com/images/July%208th%20&%2010th%20-%20Sports%20Logos2.JPG", 0);
      TeamDB.addTeam(team3);
      Team team4 = new Team("Clippers", "http://f00.inventorspot.com/images/July%208th%20&%2010th%20-%20Sports%20Logos2.JPG", 0);
      TeamDB.addTeam(team4);
      Team team5 = new Team("Suns", "http://f00.inventorspot.com/images/July%208th%20&%2010th%20-%20Sports%20Logos2.JPG", 0);
      TeamDB.addTeam(team5);
      Team team6 = new Team("Thunder", "http://f00.inventorspot.com/images/July%208th%20&%2010th%20-%20Sports%20Logos2.JPG", 0);
      TeamDB.addTeam(team6);
      
      Game.addGame(new GameForm(new Game("Kapolei PUG", "3:00PM", "3.13.14", "Kapolei park", "Public", "Nightly", "Beginner", "Loa P., Alex G.")));
      Game.addGame(new GameForm(new Game("Aina Hina PUG", "3:00PM", "3.13.14", "Aina Hina park", "Private", "Nightly", "Beginner","Loa P., Alex G.")));
      Game.addGame(new GameForm(new Game("Waianae Nightly", "6:00PM", "3.13.14", "Waianae park", "Public", "Nightly", "Beginner", "Loa P., Alex G.")));
      Game.addGame(new GameForm(new Game("Makakilo Lunchtime", "12:00PM", "3.13.14", "Makakilo Rec", "Public", "Nightly", "Beginner", "Loa P., Alex G.")));
      Game.addGame(new GameForm(new Game("Ewa Beach Practice", "5:00PM", "3.13.14", "Kapolei park", "Private", "Nightly", "Beginner", "Loa P., Alex G.")));
      
      Player.addPlayer(new PlayerFormData("Alex G.", "Ali G", "Paki Park", "Intermediate", "Center", 10, 5, "5'5\"", "200", "Basketball, I love Basketball.", "Pickup Games", ""));
      Player.addPlayer(new PlayerFormData("Steve F.", "SF", "Kapolei Park", "Beginner", "Power Forward", 15, 5, "5'5\"", "200", "Basketball, I love Basketball.", "Pickup Games", ""));
      Player.addPlayer(new PlayerFormData("Loa P.", "LP", "Aulani Park", "Competitive", "Point Gaurd", 5, 5, "5'5\"", "200", "Basketball, I love Basketball.", "Pickup Games", ""));
      Player.addPlayer(new PlayerFormData("Keith A.", "Albino", "Kapiolani Park", "College", "Small Forward", 20, 5, "5'5\"", "200", "Basketball, I love Basketball.", "Pickup Games", ""));
      Player.addPlayer(new PlayerFormData("Hector M.", "Malaz", "Hawaii Kai Park", "Intermediate", "Shooting Gaurd", 25, 5, "5'5\"", "200", "Basketball, I love Basketball.", "Pickup Games", ""));
  }
}