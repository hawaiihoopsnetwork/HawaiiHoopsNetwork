package controllers;

import com.avaje.ebean.Page;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import forms.CourtReviewForm;
import models.*;
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
import views.html.errors.PageNotFound;

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
     * @param id of court.
     * @param slug slug of court name.
     * @return court information page.
     */
    public static Result getCourt(Long id, String slug) {
        Court court = Court.getCourt(id);

        if (court == null) {
            return notFound(PageNotFound.render());
        }

        if (!Tags.slugify(court.getName()).equals(slug)) {
            return notFound(PageNotFound.render());
        }

        User user = Secured.getUserInfo(ctx());

        return ok(ShowCourt.render(
                court.getName(),
                court,
                Court.getNearbyCourts(Court.getCourt(id).getAddress(), .5),
                Review.size(id),
                Court.sizePlayers(id, user.getPlayer()), // number of players
                Hours.getSchedule(id, 1), // Mon-Sun
                Hours.getSchedule(id, 2),
                Hours.getSchedule(id, 3),
                Hours.getSchedule(id, 4),
                Hours.getSchedule(id, 5),
                Hours.getSchedule(id, 6),
                Hours.getSchedule(id, 7),
                Secured.isLoggedIn(ctx())));
    }

    public static Result getPlayers(Long id, String slug) {
        Court court = Court.getCourt(id);

        if (court == null) {
            return notFound(PageNotFound.render());
        }

        if (!Tags.slugify(court.getName()).equals(slug)) {
            return notFound(PageNotFound.render());
        }

       User user = Secured.getUserInfo(ctx());

        return ok(ShowCourtPlayers.render(
                "Players",
                court,
                Court.getNearbyCourts(Court.getCourt(id).getAddress(), .5),
                Player.page(10,0,id, user.getPlayer().getId()),
                Review.size(id),
                Court.sizePlayers(id, user.getPlayer()),
                Secured.isLoggedIn(ctx())));
    }

    public static Result getPlayersPage(Long id, String slug, Integer page) {

        Court court = Court.getCourt(id);

        if (court == null) {
            return notFound(PageNotFound.render());
        }

        if (!Tags.slugify(court.getName()).equals(slug)) {
            return notFound(PageNotFound.render());
        }

        User user = Secured.getUserInfo(ctx());
        Page<Player> players = Player.page(10, page, id, user.getPlayer().getId());
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

        if (court == null) {
            return notFound(PageNotFound.render());
        }

        if (!Tags.slugify(court.getName()).equals(slug)) {
            return notFound(PageNotFound.render());
        }


        User user = Secured.getUserInfo(ctx());
        return ok(ShowCourtReviews.render(
                "Reviews",
                court,
                Court.getNearbyCourts(Court.getCourt(id).getAddress(), .5),
                Review.page(10, 0, id),
                Review.size(id),
                Court.sizePlayers(id, user.getPlayer()),
                Secured.isLoggedIn(ctx())));

    }

    public static Result getReviewsPage(Long id, String slug, Integer page) {

        Court court = Court.getCourt(id);

        if (court == null) {
            return notFound(PageNotFound.render());
        }

        if (!Tags.slugify(court.getName()).equals(slug)) {
            return notFound(PageNotFound.render());
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

        if (court == null) {
            return notFound(PageNotFound.render());
        }

        if (!Tags.slugify(court.getName()).equals(slug)) {
            return notFound(PageNotFound.render());
        }

        CourtReviewForm review = new CourtReviewForm();
        Form<CourtReviewForm> formData = Form.form(CourtReviewForm.class).fill(review);

        return ok(CreateReview.render(
                    "Review", 
                    court,
                    Review.getRecentReviews(id), 
                    formData, 
                    Secured.isLoggedIn(ctx())));
    }


    @Security.Authenticated(Secured.class)
    public static Result postReview(Long id, String slug) {
        Court court = Court.getCourt(id);

        if (court == null) {
            return notFound(PageNotFound.render());
        }

        if (!Tags.slugify(court.getName()).equals(slug)) {
            return notFound(PageNotFound.render());
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

            User user = Secured.getUserInfo(ctx());
            return redirect(controllers.routes.Courts.getReviews(id, slug));
            /*return ok(ShowCourtReviews.render(
                "Reviews",
                court,
                Court.getNearbyCourts(Court.getCourt(id).getAddress(), .5),
                Review.page(10, 0, id),
                Review.size(id),
                Court.sizePlayers(id, user.getPlayer()),
                Secured.isLoggedIn(ctx())));*/
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

        if (court == null) {
            return notFound(PageNotFound.render());
        }

        if (!Tags.slugify(court.getName()).equals(slug)) {
            return notFound(PageNotFound.render());
        }


        Player player = User.getUser(Secured.getUser(ctx())).getPlayer();
        court.addPlayer(player);
        ObjectNode result = Json.newObject();
        result.put("num_players", court.getPlayers().size());
        return ok(result);
    }

    @Security.Authenticated(Secured.class)
    public static Result postUnfavorite() {
        JsonNode json = request().body().asJson();

        if(json == null) {
            return badRequest();
        }

        long id = json.findPath("id").asLong();
        String slug = json.findPath("slug").asText();

        Court court = Court.getCourt(id);

        if (court == null) {
            return notFound(PageNotFound.render());
        }

        if (!Tags.slugify(court.getName()).equals(slug)) {
            return notFound(PageNotFound.render());
        }


        Player player = User.getUser(Secured.getUser(ctx())).getPlayer();
        court.removePlayer(player);
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
                        routes.javascript.Courts.postUnfavorite(),
                        routes.javascript.Courts.postFavorite()));
    }

}
