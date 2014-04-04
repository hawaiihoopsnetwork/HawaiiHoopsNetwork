package controllers;

import models.Court;
import play.mvc.Controller;
import play.mvc.Security;
import play.mvc.Result;
import views.html.courts.CourtList;
import views.html.courts.ShowCourt;

/**
 * Implements the controller for basketball courts.
 * @author taylorak
 */
public class Courts extends Controller
{

    /**
     * Gets all courts.
     * @return a list of all courts.
     */
    public static Result index()
    {
        return ok(CourtList.render("Courts", Court.getCourts()));
    }

    /**
     * Get a court page.
     * @param id court name.
     * @return court information page.
     */
    public static Result getCourt(Long id)
    {
        return ok(ShowCourt.render(Court.getCourt(id).getName(), Court.getCourt(id)));
    }

    /**
     * Create a new court page.
     * @return new court form.
     */
    @Security.Authenticated(Secured.class)
    public static Result newCourt()
    {
        return ok();
    }

    /**
     * Delete a court page.
     * @return home page.
     */
    @Security.Authenticated(Secured.class)
    public static Result deleteCourt(String name)
    {
        return ok();
    }

    /**
     * Manage a court page
     * @return edit court form
     */
    @Security.Authenticated(Secured.class)
    public static Result manageCourt(String name)
    {
        return ok();
    }

    /**
     * Post a court page
     * @return home page on success, edit form on error
     */
    @Security.Authenticated(Secured.class)
    public static Result postCourt()
    {
        return ok();
    }
}
