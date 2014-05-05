package models;

import play.db.ebean.Model;
import java.util.ArrayList;
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

    /** general info **/
    private String name;

    private String image = "http://www.mnit.ac.in/new/PortalProfile/images/faculty/noimage.jpg";

    private List<String> pictures;

    private String website;

    private String phone;

    private String email;

    private String fax;

    @Lob
    private String description;

    /** court specifics **/

    private String type; // public or private

    private String indoor;

    private Long num_courts;

    private String court_size;

    private String court_surface;

    private String court_quality;

    private boolean lighted;

    @OneToOne
    private Address address;

    //@OneToMany
    //private List<Game> games = new ArrayList<Game>();

     /** player info **/
    @OneToMany(mappedBy="homeCourt")
    private List<Player> regular_players;

    @ManyToMany
    private List<Player> players = new ArrayList<>();

    @OneToMany(mappedBy="court")
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy="courts")
    private List<Hours> open_hours = new ArrayList<>();


    public Court(String name, String image, String type, String indoor, Long num_courts, String court_size,
                 String court_surface, String court_quality, boolean lighted, Address address, String description)
    {
        this.name = name;
        if(image != null) {
            this.image = image;
        }
        this.type = type;
        this.indoor = indoor;
        this.num_courts = num_courts;
        this.court_size = court_size;
        this.court_surface = court_surface;
        this.court_quality = court_quality;
        this.lighted = lighted;
        this.address = address;
        this.description = description;
    }

    /**
    * The EBean ORM finder method for database queries on Court.
    **/
    public static Finder<Long, Court> find = new Finder<Long, Court>(
           Long.class, Court.class
    );

    /**
    * Adds the specified user to the database.
    * @param name court name.
    * @param description short description of court.
    */
    public static Court addCourt(String name, String image, String type, String indoor, Long num_courts,
                                String court_size, String court_surface, String court_quality, boolean lighted,
                                Address address, String description)
    {
        Court court = new Court(name, image, type, indoor, num_courts, court_size, court_surface, court_quality,
                lighted,address, description);
        court.save();
        return court;
    }

    public static int sizePlayers(Long id, Player user) {
        Court court = find
                .where()
                    .eq("id", id)
                .findUnique();
        List<Player> players = court.getPlayers();
        players.remove(user);
        return players.size();
    }


    /**
    * Returns the court associated with a name, or null if not found.
    * @param id court id.
    * @return The court info.
    */
    public static Court getCourt(Long id)
    {
        return find.where().eq("id", id).findUnique();
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


    public static List<Player> getPlayers(Long id) {
        Court court = find
                .where()
                    .eq("id", id)
                .findUnique();

        return court.getPlayers();
    }

    public void addPlayer(Player player) {
        /**Court court = find.where().eq("id",id).findUnique();
        if (!court.getPlayers().contains(player)) {
            court.getPlayers().add(player);
            court.update();
        }**/

        if (!this.getPlayers().contains(player)) {
           this.players.add(player);
           this.update();
        }
    }

    public void removePlayer(Player player) {
        if (this.getPlayers().contains(player)) {
            this.players.remove(player);
            this.update();
        }
    }


    public static List<Court> searchCourts(String name) {
        return find
                .where()
                    .icontains("name", name)
                .findList();
    }

    public static List<Court> getNearbyCourts(Address address, double distance) {
        return find
                .where()
                    .between("address.lat",address.getLat()-distance,address.getLat()+distance)
                    .between("address.lng",address.getLng()-distance,address.getLng()+distance)
                    .ne("address.zip",address.getZip())
                    .orderBy("address.lat, lat desc")
                    .setMaxRows(5)
                .findList();
    }

    /**
     * Check if court exists.
     * @param name court name.
     * @return true if contains key false if not
     * */
    public static boolean contains(String name) {
        return (getCourt(name) != null);
    }

    public boolean containsPlayer(String email) {
        return (this.players.contains(User.getUser(email).getPlayer()));
    }

    /**
     * Returns the list of types.
     * @return the list of types
     */
/**    public static List<String> getTypes() {
        return Arrays.asList("public", "private");
    }
**/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndoor() {
        return indoor;
    }

    public void setIndoor(String indoor) {
        this.indoor = indoor;
    }

    public Long getNum_courts() {
        return num_courts;
    }

    public void setNum_courts(Long num_courts) {
        this.num_courts = num_courts;
    }

    public String getCourt_size() {
        return court_size;
    }

    public void setCourt_size(String court_size) {
        this.court_size = court_size;
    }

    public String getCourt_surface() {
        return court_surface;
    }

    public void setCourt_surface(String court_surface) {
        this.court_surface = court_surface;
    }

    public String getCourt_quality() {
        return court_quality;
    }

    public void setCourt_quality(String court_quality) {
        this.court_quality = court_quality;
    }

    public boolean isLighted() {
        return lighted;
    }

    public void setLighted(boolean lighted) {
        this.lighted = lighted;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
