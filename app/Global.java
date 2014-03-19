import models.Court;
import play.Application;
import play.GlobalSettings;

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
  }
}