package models;

import javax.persistence.*;
import play.db.ebean.Model;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Scott Honda
 */
@Entity
@Table(name = "players")
public class Player extends Model {
  
  @Id
  @GeneratedValue
  private Long id;
  private String username;
  private String name;
  private String homeCourt;
  private String skill;
  private String position;
  private Long rating;

  @ManyToMany(mappedBy = "players")
  private List<Court> courts = new ArrayList<Court>();

  // @OneToOne
  // private User user;
  
  /**
   * Creates a new player.
   * 
   * @param name = name of player
   * @param homeCourt = home court of player
   * @param skill = skill level of player
   * @param position = position of player
   * 
   */
  public Player(String username, String name, String homeCourt, String skill, String position, long rating) {
    this.username = username;
    this.name = name;
    this.homeCourt = homeCourt;
    this.skill = skill;
    this.position = position;
    this.rating = rating;
  }


   /**
   * The EBean ORM finder method for database queries on PlayerList.
   **/
   public static Finder<Long, Player> find = new Finder<Long, Player>(
          Long.class, Player.class
   );
  
  /**
   * ********************* *
   *  Getters and Setters  *
   * ********************* *
   */
  
  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the homeCourt
   */
  public String getHomeCourt() {
    return homeCourt;
  }

  /**
   * @param homeCourt the homeCourt to set
   */
  public void setHomeCourt(String homeCourt) {
    this.homeCourt = homeCourt;
  }

  /**
   * @return the skill
   */
  public String getSkill() {
    return skill;
  }

  /**
   * @param skill the skill to set
   */
  public void setSkill(String skill) {
    this.skill = skill;
  }

  /**
   * @return the position
   */
  public String getPosition() {
    return position;
  }

  /**
   * @param position the position to set
   */
  public void setPosition(String position) {
    this.position = position;
  }

  /**
   * @return the rating
   */
  public long getRating() {
    return rating;
  }

  /**
   * @param rating the rating to set
   */
  public void setRating(long rating) {
    this.rating = rating;
  }

  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }
}
