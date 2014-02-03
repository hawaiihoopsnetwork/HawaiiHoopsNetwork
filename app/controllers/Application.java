package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.ContactForm;
import views.formdata.LoginForm;
import views.html.Index;
import views.html.TermsOfUse;
import views.html.Contact;
import views.html.AboutUs;

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
    LoginForm data = new LoginForm();
    Form<LoginForm> loginForm = Form.form(LoginForm.class).fill(data);
    return ok(Index.render("Home", loginForm));
  }

  /**
   * Returns the terms of use page.
   * 
   * @return The resulting terms of use page.
   */
  public static Result terms() {
    LoginForm data = new LoginForm();
    Form<LoginForm> loginForm = Form.form(LoginForm.class).fill(data);
    return ok(TermsOfUse.render("Terms of Use", loginForm));
  }

  /**
   * Returns the contact page.
   * 
   * @return the contact page.
   */
  public static Result contact() {
    LoginForm data = new LoginForm();
    Form<LoginForm> loginForm = Form.form(LoginForm.class).fill(data);
    
    ContactForm data2 = new ContactForm();
    Form<ContactForm> contactForm = Form.form(ContactForm.class).fill(data2);
    
    return ok(Contact.render("Contact Us", loginForm, contactForm));
  }
  
  /**
   * Returns the About Us page.
   * 
   * @return the About Us page.
   */
  public static Result about() {
    LoginForm data = new LoginForm();
    Form<LoginForm> loginForm = Form.form(LoginForm.class).fill(data);
    
    return ok(AboutUs.render("About Us", loginForm));
  }
  
 


}
