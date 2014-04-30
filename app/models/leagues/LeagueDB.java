package models.leagues;

import java.util.List;
import models.teams.Team;
import views.formdata.leagues.LeagueForm;

public class LeagueDB {
  
  public static void addLeague(League newLeague){
    newLeague.save();
  }
  
  public static List<League> getLeagues(){
    return League.find().all();
  }
  
  public static League getLeague(long id){
    return League.find().where().eq("id", id).findUnique();
  }
  
  public static boolean isLeague(long id) {
    League league = getLeague(id);
    return !(league == null);
  }
  
  public static void addLeague(LeagueForm form){
    League league;

    long id = form.id;

    if (!LeagueDB.isLeague(id)) {
      league = new League(form.leagueName, form.startDate, form.endDate, form.pubOrPrivate);
      System.out.println("here");
      addLeague(league);
      league.save();
    }
    else {
      league = LeagueDB.getLeague(id);
      league.setLeagueName(form.leagueName);
      league.setLocation(form.location);
      league.setPubOrPrivate(form.pubOrPrivate);
      league.setNumTeams(form.numTeams);
      league.setStartDate(form.startDate);
      league.setEndDate(form.endDate);
      league.setDescription(form.description);
      league.save();
    }
  }
  
  public static List<Team> getTeamsInLeague(long id){
    return Team.find().where().eq("", id).findList();
  }
}