package controllers;

import models.teams.Team;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;
import views.formdata.teams.TeamForm;
import views.html.teams.AllTeams;
import views.html.teams.CreateTeam;
import views.html.teams.ShowTeam;

/**
 * Implements the controllers for this application.
 */
public class Teams extends Controller {

  /**
   * Returns the All teams page listing all the teams in the database.
   * 
   * @return AllTeams page
   */
  public static Result allTeams() {
    return ok(AllTeams.render("All Teams", Team.getTeams()));
  }

  /**
   * Returns the page containing the create team form.
   * 
   * @return create team form.
   */
  public static Result createTeam() {
    TeamForm teamForm = new TeamForm();
    Form<TeamForm> emptyForm = Form.form(TeamForm.class).fill(teamForm);
    return ok(CreateTeam.render("Create Team", emptyForm));
  }

  /**
   * Adds a team to the database after the create team form has been filled out correctly.
   * 
   * @return the team page related to the team
   */
  public static Result addTeam() {
    Form<TeamForm> teamForm = Form.form(TeamForm.class).bindFromRequest();

    if (teamForm.hasErrors()) {
      return badRequest(CreateTeam.render("Create Team", teamForm));
    }
    else {
      TeamForm tf = teamForm.get();
      Team.addTeam(tf);
      return redirect("/teams/view/" + tf.teamName);
    }
  }

  /**
   * Returns the page containing the teams info.
   * 
   * @param teamName the team name
   * @return the team page
   */
  public static Result showTeam(String teamName) {
    return ok(ShowTeam.render("View Team", Team.getTeam(teamName)));
  }

}
