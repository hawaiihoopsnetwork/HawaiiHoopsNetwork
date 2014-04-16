package models.leagues;

import java.util.ArrayList;
import java.util.List;
import models.teams.Team;
import com.avaje.ebean.Ebean;

public class LeagueDB {

  private static List<League> leagueList = new ArrayList<League>();
  
  public static void addleague(League newleague){
    leagueList.add(newleague);
  }
  
  public static List<League> getleagues(){
    return leagueList;
  }
  
  public static void addTeam(Surfer surfer, UserInfo userInfo, int teamVal) {
    if (userInfo != null) {
      Team team = Team.find().where().eq("surfer_id", surfer.getId()).findUnique();
      if (team == null) {
        team = new Team(surfer, userInfo);
        team.setTeam(teamVal, userInfo);
        Ebean.save(team);
        surfer.update();
      }  
}