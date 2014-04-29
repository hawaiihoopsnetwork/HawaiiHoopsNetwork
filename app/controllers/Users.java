package controllers;

import com.typesafe.plugin.MailerAPI;
import com.typesafe.plugin.MailerPlugin;
import models.games.Game;
import org.joda.time.DateTime;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import models.User;
import forms.*;
import views.formdata.PlayerFormData;
import views.html.Index;
import views.html.user.Login;
import views.html.user.Validate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
// import views.html.Registration;

/**
 * Implements the login controller for this application.
 */
public class Users extends Controller
{

    private static final Form<RegistrationForm> registrationForm = Form.form(RegistrationForm.class);
    private static final Form<LoginForm> loginForm = Form.form(LoginForm.class);

    /**
     * Provides the Registration page (only to unauthenticated users).
     * @return The Registration page.
     */
    public static Result register()
    {
        return ok();
    }

    /**
     * Processes a registration form submission from an unauthenticated user.
     * First we bind the HTTP POST data to an instance of RegistrationForm.
     * The binding process will invoke the Registration.validate() method.
     * If errors are found, re-render the page, displaying the error data.
     * If errors not found, render the page with the good data.
     * @return The index page with the results of validation.
     */
    public static Result postRegister()
    {
        Form<RegistrationForm> filledRegistrationForm = registrationForm.bindFromRequest();
        
        PlayerFormData data2 = new PlayerFormData();
        Form<PlayerFormData> dataForm = Form.form(PlayerFormData.class).fill(data2);

        if (filledRegistrationForm.hasErrors())
        {
            List<Game> games = null;
            if (Secured.isLoggedIn(ctx())) {
                games = Game.getGames();
            }

            return badRequest(Index.render("Hawaii Hoops Network", filledRegistrationForm, dataForm, Secured.isLoggedIn(ctx())));
        }
        else
        {
            RegistrationForm data = filledRegistrationForm.get();
            User user = User.addUser(data);

            String validation_key;

            do
            {
                validation_key = UUID.randomUUID().toString();
            // check for already existing user with same validation key
            } while((User.getValidUser(validation_key) != null));

            user.setActivation_key(validation_key);
            user.setTimestamp(new DateTime());
            user.update();


            MailerAPI mail = play.Play.application().plugin(MailerPlugin.class).email();
            mail.setSubject("Validation Email");
            //mail.setRecipient("HiHoops <hawaiihoopsnetwork@gmail.com>", "hawaiihoopsnetwork@gmail.com");
            mail.setRecipient(user.getEmail());
            mail.setFrom("hawaiihoopsnetwork@gmail.com");
            mail.sendHtml("<html><a href='http://hihoopsnetwork.scotthonda.cloudbees.net/validate/" + validation_key + "'>link</a></html>");


            //session().clear();
            //session("email", user.getEmail());
            flash("registered", "Thank you for signing up with Hawaii Hoops Network! Check your email for a verification link before logging in.");
            return redirect(routes.Application.index());
        }
    }

    public static Result validate(String key)
    {
        User user = User.getValidUser(key);
        //DateTime currentDate = new DateTime();

        if (user != null)// && currentDate.getTime() - user.getTimestamp().getTime() > 86400000)
        {
           user.setActivation_key(null);
           user.update();
           session().clear();
           session("email", user.getEmail());
        }
        return ok(Validate.render("validation", Secured.isLoggedIn(ctx())));

    }

    /**
     * Provides the Login page (only to unauthenticated users).
     * @return The Login page.
     */
    public static Result login()
    {
      return ok(Login.render("login", loginForm, Secured.isLoggedIn(ctx())));
    }

    /**
     * Processes a login form submission from an unauthenticated user.
     * First we bind the HTTP POST data to an instance of LoginFormData.
     * The binding process will invoke the LoginFormData.validate() method.
     * If errors are found, re-render the page, displaying the error data.
     * If errors not found, render the page with the good data.
     * @return The index page with the results of validation.
     */
    public static Result postLogin()
    {

        // Get the submitted form data from the request object, and run validation.
        Form<LoginForm> filledLoginForm = loginForm.bindFromRequest();

        if (filledLoginForm.hasErrors())
        {
            flash("error", "Login credentials not valid.");
            return badRequest(Login.render("login", filledLoginForm, Secured.isLoggedIn(ctx())));
        }
        else
        {
            // email/password OK, so now we set the session variable and only go to authenticated pages.
            session().clear();
            session("email", filledLoginForm.get().email);
            return redirect(routes.Application.index());
        }
    }

    /**
     * Logs out (only for authenticated users) and returns them to the Index page.
     * @return A redirect to the Index page.
     */
    @Security.Authenticated(Secured.class)
    public static Result logout()
    {
        session().clear();
        return redirect(routes.Application.index());
    }

    @Security.Authenticated(Secured.class)
    public static Result deleteUser()
    {
        User user = Secured.getUserInfo(ctx());
        user.delete();
        session().clear();
        return redirect(routes.Application.index());
    }

}
