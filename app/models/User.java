package models;


import org.mindrot.jbcrypt.BCrypt;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import forms.RegistrationForm;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A simple representation of a user. 
 * @author taylorak
 */
@Entity
@Table(name = "user")
public class User extends Model {

  // private static final long serialVersionUID = 514181764033756576L;
  
  @Id
  @Email
  private String email;
  
  @Required
  private String first;
  
  @Required
  private String last;
  
  @Required
  private String password;
  
  @Required
  private boolean admin;
  
  /**
   * Initializes new User.
   * @param first The name.
   * @param last The name.
   * @param email The email.
   * @param password The password.
   */
  public User(String first, String last, String email, String password) {
    String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
    this.first = first;
    this.last = last;
    this.email = email;
    this.password = passwordHash;
  }
  
  /**
   * The EBean ORM finder method for database queries on User.
   */
  public static Finder<String, User> find = new Finder<String, User>(
      String.class, User.class
    ); 
  
  /**
   * Returns true if there is an admin.
   * @return true if admin
   */
  public static boolean adminDefined() {
    User user = find.where().eq("admin", true).findUnique();
    return user != null;
  }
  
  /**
   * Adds the specified user to the database.
   * @param first Their name.
   * @param last Their name.
   * @param email Their email.
   * @param password Their password. 
   */
  public static void addUser(String first, String last, String email, String password) {
    User user = new User(first, last, email, password);
    user.save();
  }
  
  /**
   * Adds the specified user to the database.
   * @param formData RegistrationForm info. 
   */
  public static User addUser(RegistrationForm formData) {
    User user = new User(formData.first, formData.last, formData.email, formData.password);
    user.save();
    return user;
  }
  
  /**
   * Returns the User associated with the email, or null if not found.
   * @param email The email.
   * @return The UserInfo.
   */
  public static User getUser(String email) {
    return find.where().eq("email", email).findUnique();
  }

  /**
   * Check if email exists.
   * @param email email of user
   * @return true if contains key false if not
   * */
  public static boolean contains(String email) {
    return (getUser(email) != null);
  }
  
  /**
   * Returns true if email and password are valid credentials.
   * @param email The email. 
   * @param password The password. 
   * @return True if email is a valid user email and password is valid for that email.
   */
  public static boolean authenticate(String email, String password) {
    
    User user = find.where().eq("email", email).findUnique();
    return user != null && BCrypt.checkpw(password, user.password);
    /* if (user != null && BCrypt.checkpw(password, user.password)) {
      return true;
    } else {
      return false;
    } */

    /*    return find.where()
            .eq("email", email)
            .eq("password", password)
            .findUnique() != null;
   */

  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }
  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }
  
  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }
  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }
  
  /**
   * @return the admin
   */
  public boolean isAdmin() {
    return admin;
  }

  /**
   * @param admin the admin to set
   */
  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

  /**
   * @return the first
   */
  public String getFirst() {
    return first;
  }

  /**
   * @param first the first to set
   */
  public void setFirst(String first) {
    this.first = first;
  }

  /**
   * @return the last
   */
  public String getLast() {
    return last;
  }

  /**
   * @param last the last to set
   */
  public void setLast(String last) {
    this.last = last;
  }

}