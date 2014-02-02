package models.db;

import java.util.HashMap;
import java.util.Map;

/**
 * Temporary for storing profile credentials.
 * 
 * @author AJ
 * 
 */
public class ProfilesDB {

  /** Map. */
  public static Map<String, String> profiles = new HashMap<>();

  /**
   * Adds user to the Map.
   * 
   * @param username the username
   * @param password the password
   */
  public static void addUser(String username, String password) {
    profiles.put(username, password);
  }

  /**
   * Checks if entered username is a current user.
   * 
   * @param userName the user name
   * @return true if it is, false otherwise.
   */
  public static boolean isUser(String userName) {
    return profiles.containsKey(userName);
  }

  /**
   * Returns the map containing the profile credentials.
   * 
   * @return profiles
   */
  public static Map<String, String> getProfiles() {
    return profiles;
  }
}
