package models.leagues;

import java.util.ArrayList;
import java.util.List;

public class LeagueDB {

  private static List<League> leagueList = new ArrayList<League>();
  
  public static void addleague(League newleague){
    leagueList.add(newleague);
  }
  
  public static List<League> getleagues(){
    return leagueList;
  }
  
}