package models.teams;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import models.leagues.League;

@Entity
public class Team {

  @Id
  private long id;

  private String imageUrl;

  private String name;
  
  @ManyToMany(cascade=CascadeType.ALL)
  private List<League> leagues = new ArrayList<>();
  
  public Team(String name, String imageUrl, long id){
    this.imageUrl = imageUrl;
    this.name = name;
    this.id = id;
  }
  
  public String getImageUrl(){
    return imageUrl;
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