package controllers;

import java.util.List;
import models.teams.Team;
import models.teams.TeamDB;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.leagues.Leagues;

public class LeagueCont extends Controller{

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
