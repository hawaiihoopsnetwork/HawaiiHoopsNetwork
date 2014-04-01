package views.formdata;

import java.util.ArrayList;
import java.util.List;

import models.Player;
import play.data.validation.ValidationError;

/**
 * @author scotthonda
 */
public class PlayerFormData {

  public long id;
  public String name;
  public String nickname;
  public String homeCourt;
  public String skill;
  public String position;
  public long rating;
  public long votes;
  public String height;
  public String weight;
  public String bio;
  public String lookingFor;
  public String picUrl;
  
  /**
   * constructor for a new player.
   * 
   * @param name = name of player
   * @param homeCourt = home court of player
   * @param skill = skill level of player
   * @param position = position of player
   * 
   */
  public PlayerFormData(String name, String nickname, String homeCourt, String skill, 
              String position, long rating, long votes, String height, String weight, String bio,
              String lookingFor, String picUrl) {
    super();
    this.name = name;
    this.nickname = nickname;
    this.homeCourt = homeCourt;
    this.skill = skill;
    this.position = position;
    this.rating = rating;
    this.votes = votes;
    this.height = height;
    this.weight = weight;
    this.bio = bio;
    this.lookingFor = lookingFor;
    this.picUrl = picUrl;
  }

  /**
   * default constructor.
   */
  public PlayerFormData() {
    // constructor
  }

  /**
   * creates a new PlayerFormData object.
   * 
   * @param surfer player instance
   */
  public PlayerFormData(Player player) {
    this.name = player.getName();
    this.nickname = player.getNickname();
    this.homeCourt = player.getHomeCourt();
    this.skill = player.getSkill();
    this.position = player.getPosition();
    this.rating = player.getRating();
    this.votes = player.getVotes();
    this.height = player.getHeight();
    this.weight = player.getWeight();
    this.bio = player.getBio();
    this.lookingFor = player.getLookingFor();
    this.picUrl = player.getPicUrl();
  }

  /**
   * Validates form input by the user. All fields cannot be empty telephone field must have 12 chars.
   * 
   * @return null if no errors, error list if errors
   */
  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<>();

    if (name == null || name.length() == 0) {
      errors.add(new ValidationError("name", "Name is required"));
    }

    return errors.isEmpty() ? null : errors;
  }
}
