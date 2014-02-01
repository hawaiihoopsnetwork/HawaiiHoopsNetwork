package models.db;

import java.util.HashMap;
import java.util.Map;

public class ProfilesDB {

   public static Map<String, String> profiles = new HashMap<>();
   
   public static void addUser(String username, String password) {
     profiles.put(username, password);
   }
   
   public static boolean isUser(String userName) {
     return profiles.containsKey(userName);
   }
   
   public static Map<String, String> getProfiles() {
     return profiles;
   }
}
