package models;

import play.db.ebean.Model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.*;

/**
 * A simple representation of a basketball court.
 * @author taylorak
 */
@Entity
@Table(name = "courts")
public class Court extends Model
{

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String type;

    private String address;

    private Float lat;

    private Float lng;

    @Lob
    private String description;

    @ManyToMany
    private List<Player> players = new ArrayList<Player>();

    public Court(String name, String type, String address, Float lat, Float lng, String description)
    {
        this.name = name;
        this.type = type;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
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
    public static void addCourt(String name, String type, String address, Float lat, Float lng, String description)
    {
        Court court = new Court(name, type, address, lat, lng, description);
        court.save();
    }

    /**
     * Deletes the specified user from the database.
     * @param name court name
     */
    public static void deleteCourt(String name)
    {
        find.ref(name).delete();
    }

    /**
    * Returns the court associated with a name, or null if not found.
    * @param name court name.
    * @return The court info.
    */
    public static Court getCourt(String name)
    {
        return find.where().eq("name", name).findUnique();
    }

    /**
     * Returns all courts.
     * @return a list of courts.
     */
    public static List<Court> getCourts()
    {
        return find.all();
    }

    /**
     * Check if court exists.
     * @param name court name.
     * @return true if contains key false if not
     * */
    public static boolean contains(String name)
    {
        return (getCourt(name) != null);
    }

    /**
     * Returns the list of types.
     *
     * @return the list of types
     */
    public static List<String> getTypes() {
        return Arrays.asList("public", "private");
    }


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public Float getLat()
    {
        return lat;
    }

    public void setLat(Float lat)
    {
        this.lat = lat;
    }

    public Float getLng()
    {
        return lng;
    }

    public void setLng(Float lng)
    {
        this.lng = lng;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

}
