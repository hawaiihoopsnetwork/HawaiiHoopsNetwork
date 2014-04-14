package models.leagues;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import models.teams.Team;

@Entity
public class League {
  
  @Id
  private long id;

  private String name;
  
  @ManyToMany(mappedBy = "teams", cascade=CascadeType.ALL)
  private List<Team> teams = new ArrayList<>();
  
  public League(String name, long id){
    this.name = name;
    this.id = id;
  }
  
  public String getName(){
    return name;
  }
  
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
