import models.Court;
import models.Player;
import play.Application;
import play.GlobalSettings;
import views.formdata.PlayerFormData;

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
      
      Player.addPlayer(new PlayerFormData("Alex G.", "Paki Park", "Intermediate", "Center", 5));
      Player.addPlayer(new PlayerFormData("Steve F.", "Kapolei Park", "Beginner", "Power Forward", 5));
      Player.addPlayer(new PlayerFormData("Loa P.", "Aulani Park", "Competitive", "Point Gaurd", 5));
      Player.addPlayer(new PlayerFormData("Keith A.", "Kapiolani Park", "College", "Small Forward", 5));
      Player.addPlayer(new PlayerFormData("Hector M.", "Hawaii Kai Park", "Intermediate", "Shooting Gaurd", 5));
  }
}