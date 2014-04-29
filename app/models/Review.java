package models;

import org.joda.time.DateTime;
import play.data.format.Formats;
import play.db.ebean.Model;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "court_review")
public class Review extends Model {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User author;

    @Lob
    private String review;

    private Long rating;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    private DateTime timestamp;

    @ManyToOne
    private Court court;

    public Review() {}

    /**
     * Constructs a comment object.
     *
     * @param author the author of the comment
     */
    public Review(User author, String review, long rating, Court court) {
        this.setAuthor(author);
        this.setReview(review);
        this.setTimestamp(new DateTime());
        this.setCourt(court);
    }

    public static Finder<Long, Review> find = new Finder<Long, Review>(
           Long.class, Review.class
    );

    public static Review addReview(User author, String reviews, long rating, Court court) {
        Review review = new Review(author, reviews, rating, court);
        review.save();
        return review;
    }

    public static List<Review> getReviews(long id) {
        return find.where().eq("court.id", id).findList();
       //return find.where().eq("court.id", id).findList();
    }

    public static List<Review> getRecentReviews(long id) {
        return find.where().eq("court.id", id).orderBy("timestamp desc").setMaxRows(5).findList();
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }
}
