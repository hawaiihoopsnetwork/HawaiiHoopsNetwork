package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A simple representation of a basketball court.
 * @author taylorak
 */
@Entity
@Table(name = "court")
public class Court extends Model{

    @Id
    @Constraints.Required
    private String name;

    @Lob
    @Constraints.Required
    private String description;

    public Court(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
    * The EBean ORM finder method for database queries on Court.
    **/
    public static Finder<String, Court> find = new Finder<String, Court>(
           String.class, Court.class
    );

    /**
    * Adds the specified user to the database.
    * @param name court name.
    * @param description short description of court.
    */
    public static void addCourt(String name, String description) {
        Court court = new Court(name, description);
        court.save();
    }

    /**
     * Deletes the specified user from the database.
     * @param name court name
     */
    public static void deleteCourt(String name) {
        find.ref(name).delete();
    }

    /**
    * Returns the court associated with a name, or null if not found.
    * @param name court name.
    * @return The court info.
    */
    public static Court getCourt(String name) {
        return find.where().eq("name", name).findUnique();
    }

    /**
     * Returns all courts.
     * @return a list of courts.
     */
    public static List<Court> getCourts() {
        return find.all();
    }

    /**
     * Check if email exists.
     * @param name court name.
     * @return true if contains key false if not
     * */
    public static boolean contains(String name) {
        return (getCourt(name) != null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
