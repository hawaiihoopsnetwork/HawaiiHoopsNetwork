package models.teams;

public class Team {

  private String imageUrl;
  private String name;
  
  public Team(String name, String imageUrl){
    this.imageUrl = imageUrl;
    this.name = name;
  }
  
  public String getImageUrl(){
    return imageUrl;
  }
  
  public String getName(){
    return name;
  }
}