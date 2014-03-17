import models.Team;
import models.TeamDB;
import play.Application;
import play.GlobalSettings;


public class Global extends GlobalSettings {

  /**
   * Defines initialization of the web application.
   */
  public void onStart(Application app) {
    
    Team team = new Team("http://hawaiihoopsnetwork.github.io/uimockup/img/blazerslogo.jpg", "Portland TrailBlazers");
    
    TeamDB.addTeam(team);

  }
}
