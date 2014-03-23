package controllers;

import java.util.List;
import models.teams.Team;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;
import views.formdata.teams.TeamForm;
import views.html.teams.CreateTeam;
import views.html.teams.AllTeams;
import views.html.teams.SingleTeam;

/**
 * Implements the controllers for this application.
 */
public class Teams extends Controller {

  /**
   * Returns a info page about a single team.
   * 
   * @param name the team name
   * @return the page with the team info
   */
  public static Result getTeam(String name) {
    Team team = Team.getTeam(name);
    return ok(SingleTeam.render(team.getTeamName(), team));
  }

  /**
   * A create team page.
   * 
   * @return the create team page
   */
  public static Result createTeam() {
    TeamForm tf = new TeamForm();
    Form<TeamForm> formdata = Form.form(TeamForm.class).fill(tf);
    return ok(CreateTeam.render("Create Team", formdata));
  }

  /**
   * Saves the team page.
   * 
   * @return index page
   */
  public static Result postTeam() {
    Form<TeamForm> teamForm = Form.form(TeamForm.class).bindFromRequest();

    if (teamForm.hasErrors()) {
      return badRequest(CreateTeam.render("Create Team", teamForm));
    }
    else {
      TeamForm tf = teamForm.get();
      // TODO save team
      Team.addTeam(tf);

      return redirect("/");
    }
  }

  /**
   * Returns the all teams page with a list of all the teams.
   * 
   * @return list of all teams
   */
  public static Result allTeams() {
    List<Team> all = Team.getAllTeams();
    return ok(AllTeams.render("All Teams", all));
  }
}
