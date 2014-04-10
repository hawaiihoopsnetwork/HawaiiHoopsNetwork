import models.Court;
import models.User;
import play.Application;
import play.GlobalSettings;
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
  }
}