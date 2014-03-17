package controllers;

import play.mvc.Controller;
import play.mvc.Security;
import play.mvc.Result;

/**
 * Implements the controller for basketball courts.
 * @author taylorak
 */
public class Courts extends Controller{

    /**
     * Gets all courts.
     * @return a list of all courts.
     */
    public static Result index() {
        return ok();
    }

    /**
     * Get a court page.
     * @param name court name.
     * @return court information page.
     */
    public static Result getCourt(String name) {
        return ok();
    }

    /**
     * Create a new court page.
     * @return new court form.
     */
    @Security.Authenticated(Secured.class)
    public static Result newCourt() {
        return ok();
    }

    /**
     * Delete a court page.
     * @return home page.
     */
    @Security.Authenticated(Secured.class)
    public static Result deleteCourt(String name) {
        return ok();
    }

    /**
     * Manage a court page
     * @return edit court form
     */
    @Security.Authenticated(Secured.class)
    public static Result manageCourt() {
        return ok();
    }

    /**
     * Post a court page
     * @return home page on success, edit form on error
     */
    @Security.Authenticated(Secured.class)
    public static Result postSurfer() {
        return ok();
    }
}
