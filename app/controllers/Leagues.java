package controllers;

import java.util.List;
import models.teams.Team;
import models.teams.TeamDB;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.leagues.LeagueList;
import play.mvc.Security;

public class Leagues extends Controller{

  /**
   * returns the LeaguesPage
   * 
   * @return the LeaguesPage
   */
  //@Security.Authenticated(Secured.class)
  //public static Result leagues() {
    //List<Team> listTeam = TeamDB.getTeams();
    
    //return ok(LeagueList.render("Leagues", listTeam, Secured.isLoggedIn(ctx())));
  //}
 
  
}
