package models;

public class Profile {

  private String userName;
  private String password;
  private String emailAdress;
  
  /**
   * @return the userName
   */
  public String getUserName() {
    return userName;
  }
  /**
   * @param userName the userName to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
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
   * @return the emailAdress
   */
  public String getEmailAdress() {
    return emailAdress;
  }
  /**
   * @param emailAdress the emailAdress to set
   */
  public void setEmailAdress(String emailAdress) {
    this.emailAdress = emailAdress;
  }
}
