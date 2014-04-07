package controllers;

import java.util.List;
import com.avaje.ebean.Page;
import models.Comment;
import models.teams.Team;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;
import views.formdata.CommentForm;
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
   * @param sort the sort order
   * @param page the current page number
   * @return AllTeams page
   */
  public static Result allTeams(String sort, Integer page) {
    Page<Team> currPage = Team.find(sort, page);
    return ok(AllTeams.render("All teams", currPage, sort));
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
    CommentForm cf = new CommentForm();
    Form<CommentForm> empty = Form.form(CommentForm.class).fill(cf);

    Team team = Team.getTeam(teamName);
    List<Comment> comments = Comment.getComments(team);
    return ok(ShowTeam.render("View Team", Team.getTeam(teamName), empty, comments));
  }

  /**
   * Posts a comment to the given team's board.
   * 
   * @param teamName the team name
   * @return the team's page if there are no errors.
   */
  public static Result postComment(String teamName) {
    Form<CommentForm> cf = Form.form(CommentForm.class).bindFromRequest();

    Team team = Team.getTeam(teamName);
    List<Comment> comments = Comment.getComments(team);

    if (cf.hasErrors()) {
      return badRequest(ShowTeam.render("View Team", Team.getTeam(teamName), cf, comments));
    }
    else {
      CommentForm com = cf.get();

      Team team2 = Team.getTeam(teamName);
      // Replace /""/ with logged in user
      Comment.addComment(team2, "", com);

      return redirect("/teams/view/" + teamName);
    }

  }

}
