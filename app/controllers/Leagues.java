package controllers;

import java.util.List;
import models.Comment;
import models.leagues.League;
import models.leagues.LeagueDB;
import models.teams.Team;
import models.teams.TeamDB;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.CommentForm;
import views.formdata.leagues.LeagueForm;
import views.formdata.teams.TeamForm;
import views.html.leagues.LeagueList;
import views.html.leagues.LeagueSchedule;
import views.html.teams.CreateTeam;
import views.html.teams.ShowTeam;
import play.mvc.Security;

public class Leagues extends Controller{

  /**
   * returns the LeaguesPage
   * 
   * @return the LeaguesPage
   */
  @Security.Authenticated(Secured.class)
  public static Result leagues(Long id) {
    League league = LeagueDB.getLeague(id);
    List<Team> listTeam = TeamDB.getTeams();
    LeagueForm leagueForm = new LeagueForm();
    Form<LeagueForm> emptyForm = Form.form(LeagueForm.class).fill(leagueForm);
    
    return ok(LeagueList.render("Leagues", league, emptyForm, listTeam, Secured.isLoggedIn(ctx())));
  }
 
  public static Result editSchedule(){
    LeagueForm leagueForm = new LeagueForm();
    Form<LeagueForm> emptyForm = Form.form(LeagueForm.class).fill(leagueForm);
    return ok(LeagueSchedule.render("Create Schedule", emptyForm, Secured.isLoggedIn(ctx())));
  }
  
  public static Result changeSchedule(){
    League league = LeagueDB.getLeague(1);
    
    Form<LeagueForm> leagueForm = Form.form(LeagueForm.class).bindFromRequest();

    if (leagueForm.hasErrors()) {
      return badRequest(LeagueSchedule.render("Create Schedule", leagueForm, Secured.isLoggedIn(ctx())));
    }
    else {
      LeagueForm schedule = leagueForm.get();
      //League.addSchedule(schedule);
      List<Team> listTeam = TeamDB.getTeams();
      return ok(LeagueList.render("League Team", league, leagueForm, listTeam, Secured.isLoggedIn(ctx())));
    }
  }
  
  public static Result addLeague(){
    Form<LeagueForm> leagueForm = Form.form(LeagueForm.class).bindFromRequest();
    List<Team> listTeam = TeamDB.getTeams();
    System.out.println(leagueForm.get().leagueName);
    

    if (leagueForm.hasErrors()) {
      return badRequest(LeagueList.render("League Team", LeagueDB.getLeague(leagueForm.get().id), leagueForm, listTeam, Secured.isLoggedIn(ctx())));
    }
    else {
      LeagueForm form = leagueForm.get();
      LeagueDB.addLeague(form);
      return ok(LeagueList.render("League Team", LeagueDB.getLeague(2), leagueForm, listTeam, Secured.isLoggedIn(ctx())));
      //return redirect("/leagues/view/" + form.id);
    }
  }
  
  public static Result showLeague(Long id) {
    
    League league = LeagueDB.getLeague(id);
    List<Team> listTeam = TeamDB.getTeams();
    LeagueForm leagueForm = new LeagueForm();
    Form<LeagueForm> emptyForm = Form.form(LeagueForm.class).fill(leagueForm);
    
    return ok(LeagueList.render("League Team", league, emptyForm, listTeam, Secured.isLoggedIn(ctx())));
  }
}