package controllers;

import forms.CourtReviewForm;
import models.Court;
import models.Player;
import models.Review;
import models.User;
import play.Routes;
import play.mvc.Security;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.courts.CourtList;
import views.html.courts.ShowCourt;
import views.html.courts.ShowCourtPlayers;
import views.html.courts.ShowCourtReviews;
import views.html.courts.CreateReview;

import utils.Tags;


/**
 * Implements the controller for basketball courts.
 * @author taylorak
 */
public class Courts extends Controller {

    /**
     * Gets all courts.
     * @return a list of all courts.
     */
    public static Result index() {
        return ok(CourtList.render("Courts", Court.getCourts(), Secured.isLoggedIn(ctx())));
    }

    /**
     * Get a court page.
     * @param id court name.
     * @return court information page.
     */
    public static Result getCourt(Long id, String slug) {
        Court court = Court.getCourt(id);
        String storedSlug = Tags.slugify(court.getName());

        if (court == null || !storedSlug.equals(slug)) {
            return notFound();
        }

        return ok(ShowCourt.render(
                court.getName(),
                court,
                Court.getNearbyCourts(Court.getCourt(id).getAddress(), .5),
                Review.getReviews(id),
                Court.getPlayers(id),
                Secured.isLoggedIn(ctx())));
    }

    public static Result getPlayers(Long id, String slug) {
        Court court = Court.getCourt(id);
        String storedSlug = Tags.slugify(court.getName());

        if (court == null || !storedSlug.equals(slug)) {
            return notFound();
        }
        return ok(ShowCourtPlayers.render(
                Court.getCourt(id).getName(),
                Court.getCourt(id),
                Court.getNearbyCourts(Court.getCourt(id).getAddress(), .5),
                Review.getReviews(id),
                Court.getPlayers(id),
                Secured.isLoggedIn(ctx())));
    }

    public static Result getReviews(Long id, String slug) {
        Court court = Court.getCourt(id);
        String storedSlug = Tags.slugify(court.getName());

        if (court == null || !storedSlug.equals(slug)) {
            return notFound();
        }
       return ok(ShowCourtReviews.render(
                Court.getCourt(id).getName(),
                Court.getCourt(id),
                Court.getNearbyCourts(Court.getCourt(id).getAddress(), .5),
                Review.getReviews(id),
                Court.getPlayers(id),
                Secured.isLoggedIn(ctx())));

    }

    public static Result review(Long id, String slug) {
        Court court = Court.getCourt(id);
        String storedSlug = Tags.slugify(court.getName());

        if (court == null || !storedSlug.equals(slug)) {
            return notFound();
        }

        CourtReviewForm review = new CourtReviewForm();
        Form<CourtReviewForm> formData = Form.form(CourtReviewForm.class).fill(review);

        return ok(CreateReview.render(
                    "Review", 
                    Court.getCourt(id), 
                    Review.getRecentReviews(id), 
                    formData, 
                    Secured.isLoggedIn(ctx())));
    }


    @Security.Authenticated(Secured.class)
    public static Result postReview(Long id, String slug) {
        Court court = Court.getCourt(id);
        String storedSlug = Tags.slugify(court.getName());

        if (court == null || !storedSlug.equals(slug)) {
            return notFound();
        }

        Form<CourtReviewForm> formData = Form.form(CourtReviewForm.class).bindFromRequest();

        if (formData.hasErrors()) {
            return badRequest(CreateReview.render(
                        "Review", 
                        Court.getCourt(id), 
                        Review.getRecentReviews(id), 
                        formData, 
                        Secured.isLoggedIn(ctx())));
        }
        else {
            CourtReviewForm message = formData.get();
            Review.addReview(
                    User.getUser(Secured.getUser(ctx())), 
                    message.review, 
                    message.rating, 
                    Court.getCourt(id));

            return ok(ShowCourt.render(
                Court.getCourt(id).getName(),
                Court.getCourt(id),
                Court.getNearbyCourts(Court.getCourt(id).getAddress(), .5),
                Review.getReviews(id),
                Court.getPlayers(id),
                Secured.isLoggedIn(ctx())));
        }
    }


    @Security.Authenticated(Secured.class)
    public static Result postFavorite(Long id, String slug) {
        Court court = Court.getCourt(id);
        String storedSlug = Tags.slugify(court.getName());

        if (court == null || !storedSlug.equals(slug)) {
            return notFound();
        }

        Player player = User.getUser(Secured.getUser(ctx())).getPlayer();
        Court.addPlayer(id, player);
        return ok(ShowCourt.render(
                Court.getCourt(id).getName(),
                Court.getCourt(id),
                Court.getNearbyCourts(Court.getCourt(id).getAddress(), .5),
                Review.getReviews(id),
                Court.getPlayers(id),
                Secured.isLoggedIn(ctx())));
    }

    public static Result javascriptRoutes() {
        response().setContentType("text/javascript");
        return ok(
                Routes.javascriptRouter("jsRoutes", // appRoutes will be the JS object available in our view
                    routes.javascript.Courts.getCourt(),
                    routes.javascript.Courts.getPlayers(),
                    routes.javascript.Courts.getReviews(),
                    routes.javascript.Courts.postFavorite()));
    }

}
