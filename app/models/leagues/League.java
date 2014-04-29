package models.leagues;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
import forms.LeagueForm;
import views.formdata.teams.TeamForm;
import models.teams.Team;

@Entity
public class League extends Model{
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  private long id;
  
  private String leagueName;
  
  private int numTeams;
  
  private String startDate;
  
  private String endDate;
  
  private String description;
  
  //True for public, false for private
  private String pubOrPrivate; 
  
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getPubOrPrivate() {
    return pubOrPrivate;
  }


  private String location;
 
  @ManyToMany(mappedBy = "leagues", cascade=CascadeType.ALL)
  private List<Team> teams = new ArrayList<>();
  
  public League(String name){
    this.leagueName = name;
    this.startDate = "";
    this.endDate = "";
    this.numTeams = 0;
    this.description = "";
    this.pubOrPrivate = "public";
    this.location = "";
  }
  
  public League(String name, String startDate, String endDate, String pubOrPrivate){
    this.leagueName = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.numTeams = 0;
    this.description = "";
    this.pubOrPrivate = pubOrPrivate;
    this.location = "";
  }
  
  public static List<String> typeOfLeague() {
    String[] types = { "public", "private" };
    return java.util.Arrays.asList(types);
  }
  
  public static League getLeague(long id) {
    return League.find().where().eq("id", id).findUnique();
  }

  public String getLeagueName(){
    return leagueName;
  }
  
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
  
  public int getNumTeams() {
    return numTeams;
  }

  public void setNumTeams(int numTeams) {
    this.numTeams = numTeams;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String isPubOrPrivate() {
    return pubOrPrivate;
  }

  public void setPubOrPrivate(String pubOrPrivate) {
    this.pubOrPrivate = pubOrPrivate;
  }

  public List<Team> getTeams() {
    return teams;
  }

  public void setTeams(List<Team> teams) {
    this.teams = teams;
  }

  public void setLeagueName(String leagueName) {
    this.leagueName = leagueName;
  }
  
  public void addTeam(Team team){
    teams.add(team);
  }

  
  /**
  public static void addSchedule(LeagueForm lf) {
    League league;

    long id = lf.id;

    if (!isLeague(id)) {
      league = new League(lf.leagueName, id);
      league.save();
    }
    else {
      league = getLeague(id);
      league.setStartDate(lf.startDate);
      league.setEndDate(lf.endDate);
    }
  }
  */

  /**
  private static boolean isLeague(long id2) {
    // TODO Auto-generated method stub
    return false;
  }

  private static League getLeague(long id2) {
    // TODO Auto-generated method stub
    return null;
  }

  private void setEndDate(int endDate) {
    // TODO Auto-generated method stub
    
  }

  private void setStartDate(int startDate) {
    // TODO Auto-generated method stub
    
  }
  */
  
  public static Finder<Long, League> find() {
    return new Finder<Long, League>(Long.class, League.class);
  }
  
}
