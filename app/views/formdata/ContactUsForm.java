package views.formdata;

import play.data.validation.Constraints;

/**
 * Creates a contact form.
 * 
 * @author AJ
 * 
 */
public class ContactUsForm {

  /** Name. */
  @Constraints.Required(message = "Name is required.")
  public String name = "";
  /** Email address. */
  @Constraints.Required(message = "Email address is required.")
  @Constraints.Email(message = "Not a valid email address.")
  public String email = "";
  /** Message. */
  @Constraints.Required(message = "Message is required.")
  public String message = "";

  /**
   * Default Constructor.
   */
  public ContactUsForm() {
  }

  /**
   * Constructor.
   * 
   * @param name person's name
   * @param email the email address
   * @param message the message
   */
  public ContactUsForm(String name, String email, String message) {
    this.name = name;
    this.email = email;
    this.message = message;
  }

}
