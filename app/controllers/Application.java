package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;
import views.html.Index;
import views.html.footer.TermsOfUse;
import views.html.footer.AboutUs;
import views.html.footer.ContactUs;
import views.formdata.ContactUsForm;

/**
 * Implements the controllers for this application.
 */
public class Application extends Controller {

  /**
   * Returns the home page.
   * 
   * @return The resulting home page.
   */
  public static Result index() {
    return ok(Index.render("Your new application is ready."));
  }

  /**
   * Returns the terms of use page.
   * 
   * @return the terms of use page
   */
  public static Result terms() {
    return ok(TermsOfUse.render("Terms Of Use"));
  }

  /**
   * Returns the contact us page.
   * 
   * @return the contact page
   */
  public static Result contact() {

    ContactUsForm contact = new ContactUsForm();
    Form<ContactUsForm> formData = Form.form(ContactUsForm.class).fill(contact);

    return ok(ContactUs.render("Contact Us", formData));
  }

  /**
   * Sends a message to admin.
   * 
   * @return the page if any errors, index otherwise
   */
  public static Result sendMessage() {

    Form<ContactUsForm> formData = Form.form(ContactUsForm.class).bindFromRequest();

    if (formData.hasErrors()) {
      return badRequest(ContactUs.render("Contact Us", formData));
    }
    else {
      return redirect("/");
    }
  }

  /**
   * Returns the about us page.
   * 
   * @return the about us page
   */
  public static Result about() {
    return ok(AboutUs.render("About Us"));
  }

}
