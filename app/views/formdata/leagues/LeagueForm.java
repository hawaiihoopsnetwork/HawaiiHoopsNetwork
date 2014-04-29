package views.formdata.leagues;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

@Entity
public class LeagueForm {

	@Id
	/*the id number of the league*/
	public long id;
	
	/*the name of the league*/
	@Constraints.Required(message = "League name is required")
    public String leagueName;

	/*A short description of the league*/
    //@Constraints.Required(message = "Description is required")
    public String description;
    
    /*the number of teams in the league*/
    //@Constraints.Required(message = "Number of teams is required")
    public int numTeams;
    
    /*The start date of the league*/
    @Constraints.Required(message = "Start date is required")
    public String startDate;
    
    /*The end date of the league*/
    public String endDate;
    
    public String pubOrPrivate;
    
    public String location;
    
    /**
     * no arguments constructor
     */
    public LeagueForm(){
    	
    }

    /**
     * Constructor for defining all the attributes of the league
     * 
     * @param leagueName the name of the league
     * @param description a short description of the league
     * @param numTeams the number of teams in the league
     * @param startDate the starting date of the league
     * @param endDate the ending date of the league
     */
    
    public LeagueForm(String leagueName, String description, int numTeams, String startDate, String endDate, 
        String pubOrPrivate, String location)
    {
        this.leagueName = leagueName;
        this.description = description;
        this.numTeams = numTeams;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pubOrPrivate = pubOrPrivate;
        this.location = location;
    }
    
    public List<ValidationError> validate() {
      List<ValidationError> errors = new ArrayList<>();

      // Milestone 3, check if team being added already exists in the database.
      return errors.isEmpty() ? null : errors;
    }
}
