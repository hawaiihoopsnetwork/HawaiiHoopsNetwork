package controllers;

import com.avaje.ebean.Page;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import forms.CourtReviewForm;
import models.Court;
import models.Player;
import models.Review;
import models.User;
import play.Routes;
import play.libs.Json;
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

import java.util.List;


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


    public static Result searchCourts() {

        JsonNode json = request().body().asJson();

        if(json == null) {
            return badRequest();
        }

        String name = json.findPath("name").asText();

        List<Court> courts = (name == null) ? Court.getCourts() : Court.searchCourts(name);

        ObjectNode result = Json.newObject();

        // create array of courts
        ArrayNode node = result.putArray("courts");
        for(Court court: courts) {
            ObjectNode courtNode = Json.newObject();
            courtNode.put("id", court.getId());
            courtNode.put("name", court.getName());
            courtNode.put("slug", Tags.slugify(court.getName()));
            courtNode.put("lat", court.getAddress().getLat());
            courtNode.put("lng", court.getAddress().getLng());
            courtNode.put("description", court.getDescription());
            node.add(courtNode);
        }

        return ok(result);
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
                Review.size(),
                Court.sizePlayers(id), // number of players
                Secured.isLoggedIn(ctx())));
    }

    public static Result getPlayers(Long id, String slug) {
        Court court = Court.getCourt(id);
        String storedSlug = Tags.slugify(court.getName());

        if (court == null || !storedSlug.equals(slug)) {
            return notFound();
        }
        return ok(ShowCourtPlayers.render(
                "Players",
                Court.getCourt(id),
                Court.getNearbyCourts(Court.getCourt(id).getAddress(), .5),
                Player.page(10,0,id),
                Review.size(),
                Court.sizePlayers(id),
                Secured.isLoggedIn(ctx())));
    }

    public static Result getPlayersPage(Long id, String slug, Integer page) {

        Court court = Court.getCourt(id);
        String storedSlug = Tags.slugify(court.getName());

        if (court == null || !storedSlug.equals(slug)) {
            return notFound();
        }

        Page<Player> players = Player.page(10, page, id);
        List<Player> listPlayers = players.getList();

        ObjectNode result = Json.newObject();

        result.put("pageNumber", players.getPageIndex()+1);
        result.put("morePages", players.hasNext());
        result.put("size", players.getTotalPageCount());

        // create array of courts
        ArrayNode node = result.putArray("courts");
        for (Player player: listPlayers) {

            ObjectNode courtNode = Json.newObject();
            courtNode.put("name", player.getUser().getName());
            courtNode.put("id", player.getId());
            courtNode.put("picture", player.getPicUrl());
            node.add(courtNode);
        }
        return ok(result);

    }

    public static Result getReviews(Long id, String slug) {
        Court court = Court.getCourt(id);
        String storedSlug = Tags.slugify(court.getName());

        if (court == null || !storedSlug.equals(slug)) {
            return notFound();
        }

       return ok(ShowCourtReviews.render(
                "Reviews",
                Court.getCourt(id),
                Court.getNearbyCourts(Court.getCourt(id).getAddress(), .5),
                Review.page(10, 0, id),
                Review.size(),
                Court.sizePlayers(id),
                Secured.isLoggedIn(ctx())));

    }

    public static Result getReviewsPage(Long id, String slug, Integer page) {

        Court court = Court.getCourt(id);
        String storedSlug = Tags.slugify(court.getName());

        if (court == null || !storedSlug.equals(slug)) {
            return notFound();
        }

        Page<Review> reviews = Review.page(10, page, id);
        List<Review> listReviews = reviews.getList();

        ObjectNode result = Json.newObject();

        result.put("pageNumber", reviews.getPageIndex()+1);
        result.put("morePages", reviews.hasNext());

        // create array of courts
        ArrayNode node = result.putArray("courts");
        for (Review review: listReviews) {
            String date = review.getTimestamp().getMonthOfYear() + "/" + review.getTimestamp().getDayOfMonth() + "/" + review.getTimestamp().getYear();

            ObjectNode courtNode = Json.newObject();
            courtNode.put("author", review.getAuthor().getName());
            courtNode.put("id", review.getAuthor().getPlayer().getId());
            courtNode.put("picture", review.getAuthor().getPlayer().getPicUrl());
            courtNode.put("date", date);
            courtNode.put("review", review.getReview());
            node.add(courtNode);
        }
        return ok(result);
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

            return ok(ShowCourtReviews.render(
                "Reviews",
                Court.getCourt(id),
                Court.getNearbyCourts(Court.getCourt(id).getAddress(), .5),
                Review.page(10, 0, id),
                Review.size(),
                Court.sizePlayers(id),
                Secured.isLoggedIn(ctx())));
        }
    }

    @Security.Authenticated(Secured.class)
    public static Result postFavorite() {
        JsonNode json = request().body().asJson();

        if(json == null) {
            return badRequest();
        }

        long id = json.findPath("id").asLong();
        String slug = json.findPath("slug").asText();

        Court court = Court.getCourt(id);
        String storedSlug = Tags.slugify(court.getName());

        if (court == null || !storedSlug.equals(slug)) {
            return notFound();
        }

        Player player = User.getUser(Secured.getUser(ctx())).getPlayer();
        court.addPlayer(player);
        ObjectNode result = Json.newObject();
        result.put("num_players", court.getPlayers().size());
        return ok(result);
    }

    public static Result javascriptRoutes() {
        response().setContentType("text/javascript");
        return ok(
                Routes.javascriptRouter("jsRoutes", // appRoutes will be the JS object available in our view
                        //routes.javascript.Courts.getPlayers(),
                        //routes.javascript.Courts.getReviews(),
                        routes.javascript.Courts.getPlayersPage(),
                        routes.javascript.Courts.getReviewsPage(),
                        routes.javascript.Courts.searchCourts(),
                        routes.javascript.Courts.postFavorite()));
    }

}
