package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;
import views.formdata.teams.TeamForm;
import views.html.teams.CreateTeam;
import views.html.teams.AllTeams;

/**
 * Implements the controllers for this application.
 */
public class Teams extends Controller {

  public static Result createTeam() {
    TeamForm tf = new TeamForm();
    Form<TeamForm> formdata = Form.form(TeamForm.class).fill(tf);
    return ok(CreateTeam.render("Create Team", formdata));
  }

  public static Result postTeam() {
    Form<TeamForm> teamForm = Form.form(TeamForm.class).bindFromRequest();

    if (teamForm.hasErrors()) {
      return badRequest(CreateTeam.render("Create Team", teamForm));
    }
    else {
      TeamForm tf = teamForm.get();
      // TODO save team

      return redirect("/");
    }
  }

  public static Result allTeams() {
    return ok(AllTeams.render("All Teams"));
  }
}
