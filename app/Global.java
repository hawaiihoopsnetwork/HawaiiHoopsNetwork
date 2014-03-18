import models.PlayerDB;
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
    PlayerDB.addPlayer(new PlayerFormData("alexg", "Alex G.", "Paki Park", "Intermediate", "Center", 5));
    PlayerDB.addPlayer(new PlayerFormData("stevef", "Steve F.", "Paki Park", "Intermediate", "Center", 5));
    PlayerDB.addPlayer(new PlayerFormData("loap", "Loa P.", "Paki Park", "Intermediate", "Center", 5));
    PlayerDB.addPlayer(new PlayerFormData("keitha", "Keith A.", "Paki Park", "Intermediate", "Center", 5));
    PlayerDB.addPlayer(new PlayerFormData("hectorm", "Hector M.", "Paki Park", "Intermediate", "Center", 5));
  }
}