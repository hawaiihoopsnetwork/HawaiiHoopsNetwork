package views.formdata;

import java.util.ArrayList;
import java.util.List;
import play.data.validation.ValidationError;

/**
 * Contact Form data.
 * 
 * @author AJ
 * 
 */
public class ContactForm {

  /** User's email address. */
  public String emailAddress;
  /** The message content. */
  public String content;

  /**
   * Default constructor.
   */
  public ContactForm() {
  }

  /**
   * The validation method.
   * 
   * @return errors
   */
  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<>();

    if (emailAddress == null || emailAddress.length() == 0) {
      errors.add(new ValidationError("emailAddress", "No Email Address was entered."));
    }
    if (content == null || content.length() == 0) {
      errors.add(new ValidationError("content", "Message body was empty."));
    }

    return errors.isEmpty() ? null : errors;
  }

}
