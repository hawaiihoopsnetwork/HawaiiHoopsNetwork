package models;

/**
 * A model representing a duration in time
 * Created by taylorak on 5/3/14.
 */

import org.joda.time.DateTime;
import play.db.ebean.Model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "court_hours")
public class Hours extends Model {

    @Id
    @GeneratedValue
    private Long id;

    private int start_day;

    private DateTime start_time;
    private DateTime end_time;

    @ManyToMany
    private List<Court> courts = new ArrayList<>();

    public static Finder<Long, Hours> find = new Finder<Long, Hours>(
           Long.class, Hours.class
    );

    public Hours(DateTime start_time, DateTime end_time) {
       this.start_time = start_time;
       this.end_time = end_time;
       this.start_day = start_time.getDayOfWeek();
    }

    public Hours(int start_day, int start_hour, int start_minutes, int end_day, int end_hour, int end_minutes) {
        this.start_time = new DateTime(0,1,1,0,0,0,0).withDayOfWeek(start_day).withTime(start_hour, start_minutes, 0, 0);
        this.end_time = new DateTime(0,1,1,0,0,0,0).withDayOfWeek(end_day).withTime(end_hour, end_minutes, 0, 0);
        this.start_day = start_day;
    }

    public static Hours addDuration(int start_day, int start_hour, int start_minutes, int end_day, int end_hour, int end_minutes) {
        Hours hours = new Hours(start_day,start_hour, start_minutes, end_day, end_hour, end_minutes);
        hours.save();
        return hours;
    }

    public void addCourt(Court court) {
        this.courts.add(court);
        this.save();
    }

    public void setStartTime(int hour, int minutes) {
        start_time  = start_time.withTime(hour, minutes, 0, 0);
    }

    public void setEndTime(int hour, int minutes) {
        end_time  = end_time.withTime(hour, minutes, 0, 0);
    }

    public int getDay() {
       return start_day;
    }

    public void setDay(int start_day, int end_day) {
        start_time = start_time.withDayOfWeek(start_day);
        end_time = end_time.withDayOfWeek(end_day);
        this.start_day = start_day;
    }

    public static List<Hours> getSchedule(long court_id, int start_day) {
        return find
                .where()
                    .in("courts", Court.getCourt(court_id))
                    .eq("start_day", start_day)
                    .orderBy("start_day asc")
                .findList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(DateTime start_time) {
        this.start_time = start_time;
    }

    public DateTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(DateTime end_time) {
        this.end_time = end_time;
    }
}
