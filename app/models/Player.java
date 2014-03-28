package models;

import javax.persistence.*;
import com.avaje.ebean.Page;
import play.db.ebean.Model;
import views.formdata.PlayerFormData;
import java.util.ArrayList;
import java.util.Comparator;
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
  private String name;
  private String nickname;
  private String homeCourt;
  private String skill;
  private String position;
  private Long rating;
  private Long votes;
  private String height;
  private String weight;
  private String bio;
  private String lookingFor;
  

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
  public Player(String name, String nickname, String homeCourt, String skill, String position, 
                long rating, long votes, String height , String weight, String bio, String lookingFor) {
    this.name = name;
    this.nickname = nickname;
    this.homeCourt = homeCourt;
    this.skill = skill;
    this.position = position;
    this.rating = rating;
    this.setVotes(votes);
    this.height = height;
    this.weight = weight;
    this.bio = bio;
    this.lookingFor = lookingFor;
  }
  
  /**
   * Adds a player to the database
   * 
   * @param formData = the PlayerFormData containing the player's info
   * save's the player's info to the DB
   */
  public static void addPlayer(PlayerFormData formData) {
    Player player = new Player(formData.name, formData.nickname, formData.homeCourt, 
                               formData.skill, formData.position, formData.rating, 
                               formData.votes, formData.height, formData.weight, 
                               formData.bio, formData.lookingFor);
    player.save();
  }
  
   /**
   * The EBean ORM finder method for database queries on PlayerList.
   **/
   public static Finder<Long, Player> find() {
     return new Finder<Long, Player>(Long.class, Player.class);
   }
   
   /**
    * The EBean Page finder method to implement pagination for all players. 
    * 
    * @param sortOrder = the order to of the sorting
    * @param page = the current page index
    * @return page object of all players
    */
   public static Page<Player> find(String sortOrder, int page) {
     return find().where().orderBy(sortOrder).findPagingList(10).setFetchAhead(false).getPage(page);
   }
   
   /**
    * The EBean Page finder method to implement pagination, based on a search of players. 
    * 
    * @param sortOrder = the order to of the sorting
    * @param page = the current page index
    * @return page object of all players
    */
   public static Page<Player> find(String field, String search, String sortOrder, int page) {
     return find().where().eq(field, search).orderBy(sortOrder).findPagingList(10).setFetchAhead(false).getPage(page);
   }
   
   public static Player getPlayer(long id) {
     return find().where().eq("id", id).findUnique();
   }
   
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
   * @return the votes
   */
  public Long getVotes() {
    return votes;
  }

  /**
   * @param votes the votes to set
   */
  public void setVotes(Long votes) {
    this.votes = votes;
  }
  
  /**
   * @return the rank
   */
  public int getRank(Long rating, Long votes) {
    return (int) Math.round(this.rating/this.votes);
  }

  /**
   * @return the bio
   */
  public String getBio() {
    return bio;
  }

  /**
   * @param bio the bio to set
   */
  public void setBio(String bio) {
    this.bio = bio;
  }

  /**
   * @return the height
   */
  public String getHeight() {
    return height;
  }

  /**
   * @param height the height to set
   */
  public void setHeight(String height) {
    this.height = height;
  }

  /**
   * @return the weight
   */
  public String getWeight() {
    return weight;
  }

  /**
   * @param weight the weight to set
   */
  public void setWeight(String weight) {
    this.weight = weight;
  }

  /**
   * @return the nickname
   */
  public String getNickname() {
    return nickname;
  }

  /**
   * @param nickname the nickname to set
   */
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  /**
   * @return the lookingFor
   */
  public String getLookingFor() {
    return lookingFor;
  }

  /**
   * @param lookingFor the lookingFor to set
   */
  public void setLookingFor(String lookingFor) {
    this.lookingFor = lookingFor;
  }
  
  
}
