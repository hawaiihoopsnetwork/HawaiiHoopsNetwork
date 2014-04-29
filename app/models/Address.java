package models;

import play.db.ebean.Model;
import javax.persistence.*;

/**
 * Created by taylorak on 4/24/14.
 */
@Entity
@Table(name="address")
public class Address extends Model {
    @Id
    @GeneratedValue
    private Long id;

    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;

    private Float lat;
    private Float lng;

    @OneToOne(mappedBy="address")
    private Court court;

    public Address(String street, String city, String state, String zip, String country, Float lat, Float lng) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this. country = country;
        this.lat = lat;
        this.lng = lng;
    }

    public static Address addAddress(String street, String city, String state, String zip, String country, Float lat,
                                     Float lng) {
       Address address = new Address(street, city, state, zip, country, lat, lng);
       address.save();
       return address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }
}
