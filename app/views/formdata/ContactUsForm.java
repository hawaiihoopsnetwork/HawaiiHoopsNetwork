package views.formdata;

import java.util.ArrayList;
import java.util.List;
import play.data.validation.ValidationError;

/**
 * Creates a contact form.
 * 
 * @author AJ
 * 
 */
public class ContactUsForm {

  /** Email address. */
  public String email = "";
  /** Message. */
  public String message = "";

  /**
   * Default Constructor.
   */
  public ContactUsForm() {
  }

  /**
   * Constructor.
   * 
   * @param email the email address
   * @param message the message
   */
  public ContactUsForm(String email, String message) {
    this.email = email;
    this.message = message;
  }

  /**
   * Validates that all input is valid.
   * 
   * @return the list of errors
   */
  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<>();

    if (email == null || email.length() == 0) {
      errors.add(new ValidationError("email", "Email Address is required."));
    }
    if (!email.endsWith("@gmail.com") && !email.endsWith("@aol.com") && !email.endsWith("@yahoo.com")) {
      errors.add(new ValidationError("email", "Not a valid email address."));
    }
    if (message == null || message.length() == 0) {
      errors.add(new ValidationError("message", "Message cannot be empty."));
    }
    return errors.isEmpty() ? null : errors;
  }

}
