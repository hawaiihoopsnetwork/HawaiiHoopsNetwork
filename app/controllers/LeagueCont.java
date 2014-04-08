package controllers;

import java.util.List;
import models.teams.Team;
import models.teams.TeamDB;
import play.mvc.Result;
import views.html.leagues.Leagues;

public class LeagueCont {

  /**
   * returns the LeaguesPage
   * 
   * @return the LeaguesPage
   */
  public static Result leagues() {
    List<Team> listTeam = TeamDB.getTeams();
    
    return ok(Leagues.render("Leagues", listTeam));
  }

  
}
