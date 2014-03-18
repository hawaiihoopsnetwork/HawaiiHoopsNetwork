package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import views.formdata.PlayerFormData;

/**
 *
 */
public class PlayerDB {
  
  private static Map<String, Player> players = new HashMap<>();
  // private static List<Player> searchResults = new ArrayList<Player>();
  
  public static void addPlayer(PlayerFormData formData) {
    String username = formData.username;
    Player player = new Player(formData.name, formData.homeCourt, formData.skill, formData.position, formData.rating);
    players.put(username, player);
  }
  
  public static List<Player> getPlayers() {
    return new ArrayList<>(players.values());
  }
}